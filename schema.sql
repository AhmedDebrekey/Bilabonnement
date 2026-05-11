USE bilabonnement;

CREATE TABLE customers (
                           customer_id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           phone VARCHAR(20) NOT NULL
);

CREATE TABLE cars (
                      car_id INT AUTO_INCREMENT PRIMARY KEY,
                      license_plate VARCHAR(16) NOT NULL,
                      chassis_number VARCHAR(17) NOT NULL,
                      brand VARCHAR(255) NOT NULL,
                      model VARCHAR(255) NOT NULL,
                      color VARCHAR(255) NOT NULL,
                      status VARCHAR(255) NOT NULL
);

CREATE TABLE rental_agreements (
                                   agreement_id INT AUTO_INCREMENT PRIMARY KEY,
                                   car_id INT NOT NULL,
                                   customer_id INT NOT NULL,
                                   start_date DATE NOT NULL,
                                   end_date DATE NOT NULL,
                                   monthly_price DECIMAL(10,2) NOT NULL,
                                   pickup_location VARCHAR(255) NOT NULL,
                                   status VARCHAR(255) NOT NULL,
                                   FOREIGN KEY (car_id) REFERENCES cars(car_id),
                                   FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE damage_reports (
                                report_id INT AUTO_INCREMENT PRIMARY KEY,
                                agreement_id INT NOT NULL,
                                report_date DATE NOT NULL,
                                inspector_name VARCHAR(255) NOT NULL,
                                FOREIGN KEY (agreement_id) REFERENCES rental_agreements(agreement_id)
);

CREATE TABLE damages (
                         damage_id INT AUTO_INCREMENT PRIMARY KEY,
                         report_id INT NOT NULL,
                         description VARCHAR(255) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         FOREIGN KEY (report_id) REFERENCES damage_reports(report_id)
);