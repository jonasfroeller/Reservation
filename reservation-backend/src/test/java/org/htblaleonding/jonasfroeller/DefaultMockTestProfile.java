package org.htblaleonding.jonasfroeller;

import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

public class DefaultMockTestProfile implements QuarkusTestProfile  {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.db-kind", "h2",
                "quarkus.datasource.jdbc.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", // In-memory H2 database, instead of the real one
                "quarkus.hibernate-orm.log.sql", "true",
                "quarkus.hibernate-orm.database.generation", "drop-and-create",
                "quarkus.hibernate-orm.sql-load-script", "import.sql",
                "quarkus.http.cors", "true",
                "quarkus.http.cors.origins", "http://localhost:4200",
                "quarkus.http.root-path", "/api"
        );
    }
}
