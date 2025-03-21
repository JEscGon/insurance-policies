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
    iban VARCHAR(34) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    policy_type_id BIGINT REFERENCES policy_type(id),
    active BOOLEAN NOT NULL,
    premium_amount BIGINT NOT NULL
);

-- Crear tabla Part
CREATE TABLE parts (
    id SERIAL PRIMARY KEY,
    policy_id BIGINT NOT NULL REFERENCES policies(id),
    third_party_id BIGINT NOT NULL,
    third_party_vehicle_id BIGINT NOT NULL,
    place_event VARCHAR(255) NOT NULL,
    description TEXT,
    accident_date TIMESTAMP NOT NULL,
    date_of_registration TIMESTAMP NOT NULL,
    date_of_last_update TIMESTAMP NOT NULL,
    state_id BIGINT REFERENCES state(id)
);