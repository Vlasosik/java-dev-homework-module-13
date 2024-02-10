package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "planet")
@Data
public class Planet {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fromPlanetId", cascade = CascadeType.ALL)
    private List<Ticket> fromPlanetId;
    @OneToMany(mappedBy = "toPlanetId", cascade = CascadeType.ALL)
    private List<Ticket> toPlanetId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id) && Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
