-- Use database
USE HotelBookingDB;
DELIMITER //

CREATE PROCEDURE InsertUsers()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 5000 DO
        INSERT INTO users (id, email, name, password, username) 
        VALUES (
			UUID(),
            CONCAT('user', i, '@example.com'), 
            CONCAT('User ', i), 
            'password123', 
            CONCAT('username', i)
        );
        SET i = i + 1;
    END WHILE;
END

 //

DELIMITER ;

CALL InsertUsers();
