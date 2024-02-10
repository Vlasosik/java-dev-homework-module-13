package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime localDateTime;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;
    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanetId;
    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanetId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
