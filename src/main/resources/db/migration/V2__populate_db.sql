INSERT INTO client (name)
  VALUES
        ('Anna'),
        ('Vitaliy'),
        ('Alexandra'),
        ('Michael'),
        ('Julia'),
        ('Artem'),
        ('Olga'),
        ('Sergey'),
        ('Natalia'),
        ('Ivan'),
        ('Dmytro'),
        ('Oleksandr');

INSERT INTO Planet (id, name)
  VALUES
        ('MARS', 'Mars'),
        ('MERCURY', 'Mercury'),
        ('VENUS', 'Venus'),
        ('EARTH', 'Earth'),
        ('JUPITER', 'Jupiter'),
        ('SATURN', 'Saturn'),
        ('URANUS', 'Uranus'),
        ('NEPTUNE', 'Neptune');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id)
  VALUES
        ('2024-01-28 12:00:00', 1, 'MARS', 'VENUS'),
        ('2024-01-28 12:15:00', 2, 'MERCURY', 'EARTH'),
        ('2024-01-29 10:30:00', 3, 'VENUS', 'MARS'),
        ('2024-01-29 11:45:00', 4, 'EARTH', 'JUPITER'),
        ('2024-01-30 13:00:00', 5, 'JUPITER', 'SATURN'),
        ('2024-01-30 14:15:00', 6, 'SATURN', 'URANUS'),
        ('2024-01-30 15:30:00', 7, 'URANUS', 'NEPTUNE'),
        ('2024-01-31 13:45:00', 8, 'NEPTUNE', 'MARS'),
        ('2024-01-31 18:00:00', 9, 'MARS', 'EARTH'),
        ('2024-01-31 19:15:00', 10, 'EARTH', 'JUPITER'),
        ('2024-02-01 22:15:00', 11, 'JUPITER', 'MARS');
