-- SET search_path TO public;

-- Crear tabla PolicyType
CREATE TABLE policy_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL
);

-- Insertar valores en PolicyType
INSERT INTO policy_type (name, description) VALUES
('Básico', 'Cobertura a terceros'),
('Completo', 'Cobertura a todo riesgo');

-- Crear tabla State
CREATE TABLE state (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL
);

-- Insertar valores en State
INSERT INTO state (name, description) VALUES
('Reportado', 'Parte reportado'),
('En proceso', 'Parte en proceso'),
('Rechazado', 'Parte rechazado'),
('Cerrado', 'Parte cerrado');

-- Crear tabla Policy
CREATE TABLE policies (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    beneficiary_id BIGINT NOT NULL,
    iban VARCHAR(34) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    registration_date DATE NOT NULL,
    last_update_date DATE NULL,
    policy_type_id BIGINT REFERENCES policy_type(id),
    active BOOLEAN NOT NULL,
    premium_amount BIGINT NOT NULL
);

-- Crear tabla Parts
CREATE TABLE parts (
    id SERIAL PRIMARY KEY,
    policy_id BIGINT NOT NULL REFERENCES policies(id),
    place_event VARCHAR(255) NOT NULL,
    description TEXT,
    accident_date TIMESTAMP NOT NULL,
    date_of_registration TIMESTAMP NOT NULL,
    date_of_last_update TIMESTAMP NULL,
    state_id BIGINT REFERENCES state(id)
);

-- Crear tabla para third_party_id
CREATE TABLE part_third_party (
    part_id BIGINT NOT NULL REFERENCES parts(id),
    third_party_id BIGINT NOT NULL,
    PRIMARY KEY (part_id, third_party_id)
);

-- Crear tabla para third_party_vehicle_id
CREATE TABLE part_third_party_vehicle (
    part_id BIGINT NOT NULL REFERENCES parts(id),
    third_party_vehicle_id BIGINT NOT NULL,
    PRIMARY KEY (part_id, third_party_vehicle_id)
);