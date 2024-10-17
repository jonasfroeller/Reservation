package org.htblaleonding.jonasfroeller.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PlaceType {

    @Id
    private Long id;

    @NotBlank
    private String title;
    @Size(min = 1)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
