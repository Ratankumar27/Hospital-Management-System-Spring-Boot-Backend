INSERT INTO patient (name, birth_date, email, gender, blood_group)
VALUES ('Alice Johnson', '1990-05-14', 'alice.johnson@example.com', 'Female', 'O+'),
('Michael Smith', '1985-11-23', 'michael.smith@example.com', 'Male', 'A-'),
 ('Priya Kumar', '1998-07-08', 'priya.kumar@example.com', 'Female', 'B+'),
 ('James Lee', '1972-02-19', 'james.lee@example.com', 'Male', 'AB+'),
('Sara Connor', '2001-09-30', 'sara.oconnor@example.com', 'Female', 'O-');


INSERT INTO Doctor (name, specialization, email) VALUES
('Dr. Alice Johnson', 'Cardiology', 'alice.johnson@example.com'),
('Dr. Brian Smith', 'Neurology', 'brian.smith@example.com'),
('Dr. Clara Lee', 'Pediatrics', 'clara.lee@example.com');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id) VALUES
('2025-10-01 09:00:00', 'Routine checkup', 1, 2),
('2025-10-02 14:30:00', 'Follow-up consultation', 2, 1),
('2025-10-03 11:00:00', 'Headache assessment', 3, 2),
('2025-10-04 16:00:00', 'Pediatric vaccination', 3, 3),
('2025-10-05 10:15:00', 'Blood pressure check', 1, 5),
('2025-10-06 13:45:00', 'Skin rash consultation', 2, 4);
