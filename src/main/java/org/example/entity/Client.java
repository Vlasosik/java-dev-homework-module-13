package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
@Data
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL)
    private List<Ticket> tickets;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
