INSERT INTO customers (name, email, phone) VALUES
('Anders Hansen', 'anders@email.dk', '+45 12 34 56 78'),
('Maria Nielsen', 'maria@email.dk', '+45 87 65 43 21'),
('Lars Pedersen', 'lars@email.dk', '+45 11 22 33 44');

INSERT INTO cars (license_plate, chassis_number, brand, model, color, status) VALUES
('AB12345', 'WBA3A5C50DF596843', 'Toyota', 'Yaris', 'White', 'Rented'),
('CD67890', 'WBA3A5C50DF596844', 'Citroën', 'C3', 'Blue', 'Available'),
('EF11223', 'WBA3A5C50DF596845', 'DS', 'DS3', 'Black', 'Awaiting inspection');

INSERT INTO rental_agreements (car_id, customer_id, start_date, end_date, monthly_price, pickup_location, status) VALUES
(1, 1, '2026-01-01', '2026-06-01', 3199.00, 'Bilabonnement A/S, København', 'Active'),
(3, 3, '2025-11-01', '2026-04-30', 3499.00, 'DS Salon, København', 'Ended');

INSERT INTO damage_reports (agreement_id, report_date, inspector_name) VALUES
(2, '2026-05-01', 'John Smith');

INSERT INTO damages (report_id, description, price) VALUES
(1, 'Scratched rear bumper', 1500.00),
(1, 'Cracked windshield', 3000.00);