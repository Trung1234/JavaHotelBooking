-- Create database
CREATE DATABASE IF NOT EXISTS HotelBookingDB;

-- Use database
USE HotelBookingDB;

--CREATE TABLE users (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
 --   email VARCHAR(255) NOT NULL,
 --   name VARCHAR(255) NOT NULL,
 --   password VARCHAR(255) NOT NULL,
 --   username VARCHAR(255) NOT NULL
--);

--CREATE TABLE roles (
 --   id BIGINT AUTO_INCREMENT PRIMARY KEY,
 --   name VARCHAR(60) NOT NULL
--);

--CREATE TABLE user_roles (
 --   user_id BIGINT NOT NULL,
 --   role_id BIGINT NOT NULL,
 --   PRIMARY KEY (user_id, role_id),
 --   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  --  FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
--);

USE HotelBookingDB;

CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL
);
ALTER TABLE users
ADD COLUMN insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;


CREATE TABLE roles (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(60) NOT NULL
);

CREATE TABLE user_roles (
    user_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Create rooms table
CREATE TABLE IF NOT EXISTS rooms (
  id INT AUTO_INCREMENT,
  room_number INT NOT NULL,
  room_type ENUM('single', 'double', 'suite') NOT NULL,
  capacity INT NOT NULL,
  rate DECIMAL(10, 2) NOT NULL,
  description TEXT,
  PRIMARY KEY (id)
);

-- Create guests table
CREATE TABLE IF NOT EXISTS guests (
  id INT AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  phone VARCHAR(20),
  address VARCHAR(255),
  PRIMARY KEY (id)
);

-- Create bookings table
CREATE TABLE IF NOT EXISTS bookings (
  id INT AUTO_INCREMENT,
  room_id INT NOT NULL,
  guest_id INT NOT NULL,
  checkin DATE NOT NULL,
  checkout DATE NOT NULL,
  status ENUM('pending', 'checkedin', 'checkedout', 'cancelled') NOT NULL DEFAULT 'pending',
  PRIMARY KEY (id),
  FOREIGN KEY (room_id) REFERENCES rooms(id),
  FOREIGN KEY (guest_id) REFERENCES guests(id)
);

-- Create payments table
CREATE TABLE IF NOT EXISTS payments (
  id INT AUTO_INCREMENT,
  booking_id INT NOT NULL,
  payment_method ENUM('cash', 'card', 'online') NOT NULL,
  payment_date DATE NOT NULL,
  amount DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (booking_id) REFERENCES bookings(id)
);

-- Create amenities table
CREATE TABLE IF NOT EXISTS amenities (
  id INT AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  PRIMARY KEY (id)
);

-- Create room_amenities table
CREATE TABLE IF NOT EXISTS room_amenities (
  room_id INT NOT NULL,
  amenity_id INT NOT NULL,
  PRIMARY KEY (room_id, amenity_id),
  FOREIGN KEY (room_id) REFERENCES rooms(id),
  FOREIGN KEY (amenity_id) REFERENCES amenities(id)
);

-- Insert sample data

-- Insert rooms
INSERT INTO rooms (room_number, room_type, capacity, rate, description) VALUES
(101, 'single', 1, 100.00, 'Single room with one bed'),
(102, 'double', 2, 150.00, 'Double room with two beds'),
(103, 'suite', 4, 250.00, 'Suite room with two bedrooms and a living room');

-- Insert guests
INSERT INTO guests (first_name, last_name, email, phone, address) VALUES
('John', 'Doe', 'johndoe@example.com', '123-456-7890', '123 Main St'),
('Jane', 'Smith', 'janesmith@example.com', '098-765-4321', '456 Elm St'),
('Bob', 'Johnson', 'bobjohnson@example.com', '555-123-4567', '789 Oak St');

-- Insert bookings
INSERT INTO bookings (room_id, guest_id, checkin, checkout, status) VALUES
(1, 1, '2022-01-01', '2022-01-03', 'pending'),
(2, 2, '2022-01-05', '2022-01-07', 'pending'),
(3, 3, '2022-01-10', '2022-01-12', 'pending');

-- Insert payments
INSERT INTO payments (booking_id, payment_method, payment_date, amount) VALUES
(1, 'cash', '2022-01-01', 200.00),
(2, 'card', '2022-01-05', 300.00),
(3, 'online', '2022-01-10', 400.00);

-- Insert amenities
INSERT INTO amenities (name, description) VALUES
('Wi-Fi', 'Free Wi-Fi access'),
('Breakfast', 'Complimentary breakfast'),
('Gym', '24-hour fitness center');

-- Insert room amenities
INSERT INTO room_amenities (room_id, amenity_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 1),
(3, 2),
(3, 3);