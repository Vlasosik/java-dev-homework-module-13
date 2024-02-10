package org.example.service;

import org.example.dao.GenericDao;
import org.example.entity.Planet;


public class PlanetCrudService {
    GenericDao<Planet> planetGenericDao = new GenericDao<>();

    public void create(Planet planet) {
        planetGenericDao.create(planet);
    }

    public Planet read(String id) {
        return planetGenericDao.read(id, Planet.class);
    }

    public void update(Planet planet) {
        planetGenericDao.update(planet);
    }

    public void delete(Planet planet) {
        planetGenericDao.delete(planet);
    }
}

