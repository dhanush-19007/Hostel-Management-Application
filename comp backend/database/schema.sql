CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS room (
    id BIGSERIAL PRIMARY KEY,
    room_number VARCHAR(50),
    capacity INT,
    occupancy INT,
    room_type VARCHAR(50),
    block_name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS complaint (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    category VARCHAR(100),
    photo_url VARCHAR(255),
    priority VARCHAR(20),
    status VARCHAR(30),
    escalated BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP,
    assigned_staff VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS payment_record (
    id BIGSERIAL PRIMARY KEY,
    student_name VARCHAR(255),
    amount NUMERIC(10,2),
    due_date DATE,
    status VARCHAR(30)
);
