-- Dienstverbanden
INSERT INTO dienstverband (
    id,
    werkgever,
    fulltime_salaris,
    parttime_percentage,
    franchise,
    premie_percentage,
    is_indienst
)
VALUES
(1, 'BeFrank', 60000, 80, 15599, 5, TRUE),
(2, 'Acme Nederland', 52000, 100, 17545, 5.5, TRUE),
(3, 'Tech Solutions', 78000, 90, 17545, 4.5, TRUE);

-- Pensioenrekeningen
INSERT INTO pensioenrekening (
    id,
    rekeningnummer
)
VALUES
(1, 'NL01PENS000001'),
(2, 'NL01PENS000002'),
(3, 'NL01PENS000003');

-- Deelnemers
INSERT INTO deelnemer (
    id,
    voornaam,
    achternaam,
    emailadres,
    geboortedatum,
    dienstverband_id,
    pensioenrekening_id
)
VALUES
(
    1,
    'Jan',
    'Jansen',
    'jan.jansen@example.nl',
    '1966-03-15',
    1,
    1
),
(
    2,
    'Piet',
    'Pieters',
    'piet.pieters@example.nl',
    '1990-01-22',
    2,
    2
),
(
    3,
    'Karin',
    'De Vries',
    'karin.devries@example.nl',
    '1978-02-07',
    3,
    3
);