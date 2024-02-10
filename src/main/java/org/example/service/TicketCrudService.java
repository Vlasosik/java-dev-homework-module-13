package org.example.service;

import org.example.dao.GenericDao;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;

public class TicketCrudService {
    GenericDao<Ticket> ticketGenericDao = new GenericDao<>();
    GenericDao<Client> clientGenericDao = new GenericDao<>();
    GenericDao<Planet> planetGenericDao = new GenericDao<>();

    public void create(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Квиток не може бути null");
        }
        Client client = clientGenericDao.read(ticket.getClientId().getId(), Client.class);
        if (client == null) {
            throw new IllegalArgumentException("Клієнт з заданим ідентифікатором не існує");
        }
        Planet fromPlanet = planetGenericDao.read(ticket.getFromPlanetId().getId(), Planet.class);
        if (fromPlanet == null) {
            throw new IllegalArgumentException("Початкова планета з заданим ідентифікатором не існує");
        }
        Planet toPlanet = planetGenericDao.read(ticket.getToPlanetId().getId(), Planet.class);
        if (toPlanet == null) {
            throw new IllegalArgumentException("Кінцева планета з заданим ідентифікатором не існує");
        }
        ticketGenericDao.create(ticket);
    }

    public Ticket read(Long id) {
        return ticketGenericDao.read(id, Ticket.class);
    }

    public void update(Ticket ticket) {
        ticketGenericDao.update(ticket);
    }

    public void delete(Ticket ticket) {
        ticketGenericDao.delete(ticket);
    }
}
