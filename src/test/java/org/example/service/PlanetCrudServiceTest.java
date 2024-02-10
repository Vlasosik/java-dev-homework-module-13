package org.example.service;

import org.example.dao.GenericDao;
import org.example.entity.Planet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlanetCrudServiceTest {
    private static GenericDao<Planet> planetGenericDao;

    @BeforeAll
    public static void setup() {
        planetGenericDao = new GenericDao<>();
    }


    @ParameterizedTest
    @MethodSource("planetCreate")
    void whenWeCreateNewPlanet(Planet planet) {
        planetGenericDao.create(planet);
        Planet readById = planetGenericDao.read(planet.getId(), Planet.class);
        assertEquals(planet, readById);
    }

    @ParameterizedTest
    @MethodSource("readPlanet")
    void whenWeReadPlanet(Planet planet) {
        Planet readPlanet = planetGenericDao.read(planet.getId(), Planet.class);
        assertNotNull(readPlanet);
        assertEquals(planet.getId(), readPlanet.getId());
    }

    @ParameterizedTest
    @MethodSource("updatedPlanet")
    void whenWeUpdatedClient(List<Planet> planetToUpdate) {
        for (Planet planet : planetToUpdate) {
            Planet updatedPlanet = planetGenericDao.read(planet.getId(), Planet.class);
            updatedPlanet.setName(planet.getName());
            planetGenericDao.update(updatedPlanet);
        }
        for (Planet planet : planetToUpdate) {
            Planet updatePlanet = planetGenericDao.read(planet.getId(), Planet.class);
            assertEquals(planet.getName(), updatePlanet.getName());
        }
    }

    @ParameterizedTest
    @MethodSource("deletePlanet")
    void whenWeDeletePlanet(Planet planet) {
        Planet planetToDelete = planetGenericDao.read(planet.getId(), Planet.class);
        assertNotNull(planetToDelete);
        planetGenericDao.delete(planetToDelete);
    }

    private static Stream<Arguments> planetCreate() {
        Planet planet = new Planet();
        planet.setId("HORRI");
        planet.setName("Hebarus");
        Planet planet1 = new Planet();
        planet1.setId("GERASUS");
        planet1.setName("Nynys1B");
        Planet planet2 = new Planet();
        planet2.setId("KENY");
        planet2.setName("Horyy");
        return Stream.of(
                Arguments.of(planet),
                Arguments.of(planet1),
                Arguments.of(planet2)
        );
    }


    private static Stream<Arguments> readPlanet() {
        Planet planet = new Planet();
        planet.setId("EARTH");
        Planet planet1 = new Planet();
        planet1.setId("MARS");
        Planet planet2 = new Planet();
        planet2.setId("VENUS");
        return Stream.of(
                Arguments.of(planet),
                Arguments.of(planet1),
                Arguments.of(planet2)
        );
    }

    public static Stream<List<Planet>> updatedPlanet() {
        List<Planet> planetToUpdated = new ArrayList<>();
        Planet planet1 = new Planet();
        planet1.setId("MARS");
        planet1.setName("Glize");
        planetToUpdated.add(planet1);

        Planet planet2 = new Planet();
        planet2.setId("VENUS");
        planet2.setName("HDBERN");
        planetToUpdated.add(planet2);

        Planet planet3 = new Planet();
        planet3.setId("EARTH");
        planet3.setName("Derasios");

        planetToUpdated.add(planet3);
        return Stream.of(planetToUpdated);
    }

    private static Stream<Arguments> deletePlanet() {
        Planet planet1 = new Planet();
        planet1.setId("SATURN");
        return Stream.of(
                Arguments.of(planet1)
        );
    }

}

