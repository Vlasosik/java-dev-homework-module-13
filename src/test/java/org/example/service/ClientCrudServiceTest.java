package org.example.service;

import org.example.dao.GenericDao;
import org.example.entity.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ClientCrudServiceTest {
    private static GenericDao<Client> clientGenericDao;

    @BeforeAll
    public static void setup() {
        clientGenericDao = new GenericDao<>();
    }


    @ParameterizedTest
    @MethodSource("clientCreate")
    void whenWeCreateNewClient(Client client) {
        clientGenericDao.create(client);
        Client readById = clientGenericDao.read(client.getId(), Client.class);
        assertEquals(client, readById);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5})
    void whenWeReadClientById(Long id) {
        Client client = clientGenericDao.read(id, Client.class);
        assertNotNull(client);
    }

    @ParameterizedTest
    @MethodSource("clientToUpdate")
    void whenWeUpdateClient(List<Client> clientsToUpdate) {
        for (Client client : clientsToUpdate) {
            Client updatedClient = clientGenericDao.read(client.getId(), Client.class);
            updatedClient.setName(client.getName());
            clientGenericDao.update(updatedClient);
        }
        for (Client client : clientsToUpdate) {
            Client updatedClient = clientGenericDao.read(client.getId(), Client.class);
            assertEquals(client.getName(), updatedClient.getName());
        }
    }

    @ParameterizedTest
    @MethodSource("clientCreate")
    void whenWeDeleteClient(Client client) {
        clientGenericDao.create(client);
        Long clientId = client.getId();
        Client clientToDelete = clientGenericDao.read(clientId, Client.class);
        clientGenericDao.delete(clientToDelete);
        assertNull(clientGenericDao.read(clientId, Client.class));
    }

    private static Stream<Arguments> clientCreate() {
        Client client1 = new Client();
        client1.setName("Denys");
        Client client2 = new Client();
        client2.setName("Vlad");
        Client client3 = new Client();
        client3.setName("Boris");

        return Stream.of(
                Arguments.of(client1),
                Arguments.of(client2),
                Arguments.of(client3)
        );
    }

    private static Stream<List<Client>> clientToUpdate() {
        List<Client> clientsToUpdate = new ArrayList<>();

        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Bommy");
        clientsToUpdate.add(client1);

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Gorry");
        clientsToUpdate.add(client2);

        Client client3 = new Client();
        client3.setId(3L);
        client3.setName("Marry");

        clientsToUpdate.add(client3);

        return Stream.of(clientsToUpdate);
    }
}
