package org.example.service;

import org.example.dao.GenericDao;
import org.example.entity.Client;

public class ClientCrudService {
    GenericDao<Client> clientGenericDao = new GenericDao<>();

    public void create(Client client) {
        clientGenericDao.create(client);
    }

    public Client read(Long id) {
        return clientGenericDao.read(id, Client.class);
    }

    public void update(Client client) {
        clientGenericDao.update(client);
    }

    public void delete(Client client) {
        clientGenericDao.delete(client);
    }
}
