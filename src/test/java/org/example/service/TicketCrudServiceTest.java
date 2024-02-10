package org.example.service;

import org.example.dao.GenericDao;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TicketCrudServiceTest {
    private static GenericDao<Ticket> ticketGenericDao;

    @BeforeAll
    public static void setup() {
        ticketGenericDao = new GenericDao<>();
    }

    @ParameterizedTest
    @MethodSource("createTicket")
    void whenWeCreateNewTicket(Ticket ticket) {
        ticketGenericDao.create(ticket);
        Ticket readId = ticketGenericDao.read(ticket.getId(), Ticket.class);
        assertEquals(ticket, readId);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4})
    void whenWeReadTicket(Long id) {
        Ticket ticket = ticketGenericDao.read(id, Ticket.class);
        assertNotNull(ticket);
    }

    @ParameterizedTest
    @MethodSource("updateTicket")
    void whenWeUpdateTicket(Ticket ticket) {
        Ticket updateTicket = ticketGenericDao.read(ticket.getId(), Ticket.class);
        assertNotNull(updateTicket);
        ticketGenericDao.update(ticket);
        assertEquals(ticket.getId(), updateTicket.getId());
    }


    @ParameterizedTest
    @MethodSource("deleteTicket")
    void whenWeDeleteTicket(Ticket ticket) {
        Ticket ticketToDelete = ticketGenericDao.read(ticket.getId(), Ticket.class);
        assertNotNull(ticketToDelete);
        ticketGenericDao.delete(ticketToDelete);
    }

    private static Stream<Arguments> createTicket() {
        Client client1 = new Client();
        client1.setId(1L);

        Ticket ticket1 = new Ticket();
        ticket1.setLocalDateTime(LocalDateTime.now());
        ticket1.setClientId(client1);

        Planet fromPlanetId = new Planet();
        fromPlanetId.setId("JUPITER");
        ticket1.setFromPlanetId(fromPlanetId);

        Planet toPlanetId = new Planet();
        toPlanetId.setId("EARTH");
        ticket1.setToPlanetId(toPlanetId);

        return Stream.of(Arguments.of(ticket1));
    }

    public static Stream<Ticket> updateTicket() {
        Client client1 = new Client();
        client1.setId(4L);

        Planet fromPlanet = new Planet();
        fromPlanet.setId("EARTH");

        Planet toPlanet = new Planet();
        toPlanet.setId("JUPITER");

        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(4L);
        ticketToUpdate.setLocalDateTime(LocalDateTime.now());
        ticketToUpdate.setClientId(client1);
        ticketToUpdate.setFromPlanetId(fromPlanet);
        ticketToUpdate.setToPlanetId(toPlanet);

        return Stream.of(ticketToUpdate);
    }

    public static Stream<Arguments> deleteTicket() {
        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        Ticket ticket2 = new Ticket();
        ticket2.setId(2L);
        Ticket ticket3 = new Ticket();
        ticket3.setId(3L);
        return Stream.of(
                Arguments.of(ticket1),
                Arguments.of(ticket2),
                Arguments.of(ticket3)
        );
    }
}

