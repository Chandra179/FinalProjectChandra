INSERT INTO tb_user(id, email, first_name, last_name, mobile_number, password, username)
VALUES (DEFAULT, 'chan@gmail.com','Chandra','aja','094727437','chan123','chan');

INSERT INTO tb_agency(id, code, details, name, owner_user_id)
VALUES (DEFAULT, 'BDL','PT Angkasa Muda','Angkot Karang',1);

INSERT INTO tb_bus(id, capacity, code, make, agency_id) VALUES 
(DEFAULT, 20,'BDL01','20',1),
(DEFAULT, 15,'BDL02','15',1);

INSERT INTO tb_role (id, name) VALUES 
(DEFAULT, 'ROLE_USER'),
(DEFAULT, 'ROLE_ADMIN');

INSERT INTO tb_stop values
(DEFAULT, '1-2', 'Teluk', 'Karang'),
(DEFAULT, '1-8', 'Cimeng', 'Sukaraja 1-8'),
(DEFAULT, '1-3', 'Raja BasaI', 'Kedaton 1-3'),
(DEFAULT, '1-2', 'Sukabumi', 'Sukarame 1-2'),
(DEFAULT, '1-10', 'Tanjung Senang', 'Jati Agung 1-10');

INSERT INTO tb_trip VALUES
(DEFAULT, 15000, 15, 1, 1, 1, 3),
(DEFAULT, 15000, 2, 1, 1, 5, 2),
(DEFAULT, 15000, 3, 1, 1, 2, 3),
(DEFAULT, 15000, 5, 1, 1, 4, 4),
(DEFAULT, 15000, 4, 1, 1, 2, 5),
(DEFAULT, 15000, 7, 1, 1, 3, 5);