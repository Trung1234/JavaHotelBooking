# Hotel Booking System

## Project Overview

This project is a "Hotel Booking System" built using Spring Boot. It includes basic hotel management functionalities allowing users to search and book rooms, manage bookings, and manage rooms.

## Functional Requirements

### Users
1. **Registration and Login**
   - Users can register a new account.
   - Registered users can log in to the system.

2. **Search and Book Rooms**
   - Users can search for hotel rooms based on criteria such as city, check-in date, check-out date, and number of guests.
   - View detailed room information including images, descriptions, amenities, price, and room availability.
   - Users can book rooms and receive booking confirmations.

3. **Booking Management**
   - Users can view and manage their bookings.
   - Users can cancel bookings before a specified deadline.

4. **Reviews and Comments**
   - Users can leave reviews and comments about the hotel after their stay.

### Administrators
1. **Hotel Management**
   - Administrators can add, edit, and delete hotel information.
   - Manage hotel amenities and services.

2. **Room Management**
   - Administrators can add, edit, and delete room information.
   - Manage room status (available, booked, under maintenance).

3. **Booking Management**
   - Administrators can view all bookings and change booking statuses.

4. **User Management**
   - Administrators can view user lists, delete, or block users.

## Non-Functional Requirements

1. **Security**
   - Use Interceptor protect endpoints.
   - Sensitive data (such as passwords) must be encrypted.

2. **Performance**
   - The system must respond quickly and handle multiple concurrent requests.

3. **Scalability**
   - Design the system to be easily scalable and to add new features.

4. **Availability**
   - The system must quickly recover from failures.

## Technologies and Tools

### Back-end
- **Spring Boot:** Main framework for the application.
- **MyBatis:** Manages database interactions.
- **Security:** Manages security and user authentication.
- **MySQL :** Main database.
- **REST API:** Provides API services for the front-end.

### Front-end
- **Angular/React/Vue.js:** Front-end framework for the user interface.
- **Bootstrap:** CSS library for responsive design.

### Others
- **Swagger:** Create and manage API documentation.
- **Docker:** Package application and development environment.
- **Jenkins:** CI/CD automation for build and deploy processes.

## Basic Project Structure

- **Model:** Contains classes representing data and interactions with the database.
- **Repository:** Interacts with the database.
- **Service:** Contains business logic.
- **Controller:** Handles HTTP requests and responses.
- **DTOs (Data Transfer Objects):** Transfer data between layers in the application.

## Implementation Steps

1. **Database Design:** Define tables and relationships between them.
2. **Implement Back-end Functions:** Implement RESTful APIs for user and admin functions.
3. **Implement Front-end:** Build the user interface and connect to the back-end APIs.
4. **Security:** Set up Spring Security and manage permissions.
5. **Testing:** Conduct unit testing, integration testing, and system testing.
6. **Deployment:** Deploy the application to a server (using Docker and CI/CD if possible).

## Additional Requirements
1. **Exception Handling:** Ensure the system handles errors well and provides useful information to users.
2. **Logging and Monitoring:** Record actions and errors occurring in the system for easy tracking and troubleshooting.

   
### Install lombok
we can run the jar via java -jar command : java -jar lombok.jar, 
and an installer UI will open. This tries to automatically detect all available Eclipse installations, but it’s also possible to specify the location manually.
Once we’ve selected the installations, we press the Install/Update button:

![install-lombok-1](https://github.com/user-attachments/assets/02c276ab-6b39-4c63-86cd-99866c127801)



