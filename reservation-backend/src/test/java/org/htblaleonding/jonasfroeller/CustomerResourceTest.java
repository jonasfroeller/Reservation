package org.htblaleonding.jonasfroeller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import org.htblaleonding.jonasfroeller.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestProfile(DefaultMockTestProfile.class)
public class CustomerResourceTest {
    @Test
    public void testHealthCheck() {
        given()
                .when().get("/customers/health")
                .then()
                .statusCode(200)
                .body(equalTo("CustomerResource is alive!"));
    }

    @Test
    public void testGetCustomerList() {
        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Ensure the response is JSON
                .body("$", isA(List.class)) // Check if the response is a collection
                .body("", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void testGetCustomerById() {
        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("SecureP@ssw0rd");

        Long customerId = (Long) given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post("/customers")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Now fetch the customer by ID
        given()
                .when().get("/customers/{id}", customerId)
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Jane"))
                .body("lastName", equalTo("Doe"))
                .body("email", equalTo("jane.doe@example.com"));
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPassword("SecureP@ssw0rd");

        given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post("/customers")
                .then()
                .statusCode(201)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    public void testUpdateCustomer() {
        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("Alice");
        customer.setLastName("Smith");
        customer.setEmail("alice.smith@example.com");
        customer.setPassword("SecureP@ssw0rd");

        Long customerId = (Long) given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post("/customers")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Update the customer
        customer.setFirstName("Alice Updated");
        customer.setLastName("Smith Updated");

        given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().put("/customers")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Alice Updated"))
                .body("lastName", equalTo("Smith Updated"));
    }

    @Test
    public void testPatchCustomer() {
        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Johnson");
        customer.setEmail("bob.johnson@example.com");
        customer.setPassword("SecureP@ssw0rd");

        Long customerId = (Long) given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post("/customers")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Partially update the customer
        Customer partialUpdate = new Customer();
        partialUpdate.setFirstName("Bob Updated");
        partialUpdate.setPassword("NewP@ssw0rd!");

        given()
                .contentType(ContentType.JSON)
                .body(partialUpdate)
                .when().patch("/customers/{id}", customerId)
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Bob Updated"))
                .body("lastName", equalTo("Johnson"))
                .body("email", equalTo("bob.johnson@example.com"));
    }

    @Test
    public void testDeleteCustomer() {
        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("Charlie");
        customer.setLastName("Brown");
        customer.setEmail("charlie.brown@example.com");
        customer.setPassword("SecureP@ssw0rd");

        Long customerId = (Long) given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post("/customers")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Delete the customer
        given()
                .when().delete("/customers/{id}", customerId)
                .then()
                .statusCode(204);

        // Verify the customer is no longer available
        given()
                .when().get("/customers/{id}", customerId)
                .then()
                .statusCode(404);
    }
}
