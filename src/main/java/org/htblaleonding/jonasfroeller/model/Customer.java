package org.htblaleonding.jonasfroeller.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.htblaleonding.jonasfroeller.utility.PasswordUtils;
import org.htblaleonding.jonasfroeller.validator.Password;

@Entity
public class Customer extends PanacheEntity {
    @Size(min = 1)
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 1)
    @Column(name = "last_name")
    private String lastName;
    @Email
    private String email;
    @Password
    private String password;
    @Size(min = 3, max = 100)
    private String username;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 1) String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 1) String lastName) {
        this.lastName = lastName;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@Password String password) {
        this.password = PasswordUtils.hashPassword(password);
    }

    public Boolean isPasswordValid(@Password String password) {
        return PasswordUtils.checkPassword(password, getPassword());
    }

    public @Size(min = 3, max = 100) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 100) String username) {
        this.username = username;
    }
}
