package org.htblaleonding.jonasfroeller.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PlaceType extends PanacheEntity {
    @NotBlank
    private String title;
    @Size(min = 1)
    private String description;

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public @Size(min = 1) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 1) String description) {
        this.description = description;
    }
}
