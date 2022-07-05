INSERT INTO tb_user(id, email, first_name, last_name, mobile_number, password, username)
VALUES (1, 'chan@gmail.com','Chandra','aja','094727437','chan123','chan');

INSERT INTO tb_agency(id, code, details, name, owner_user_id) VALUES 
(1, 'BDL','PT Angkasa Muda','Angkot Karang',1),
(2, 'JML','PT Merpati tua','Bis Cengkel',1),
(3, 'LKM','PT Gagak aja','Becak Codel',1);

INSERT INTO tb_bus(id, capacity, code, make, agency_id) VALUES 
(1, 20,'BDL01','20',1),
(2, 15,'BDL02','15',1);

INSERT INTO tb_role (id, name) VALUES 
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO tb_user_roles (user_id, role_id) VALUES
(1, 1);

INSERT INTO tb_stop (id, code, detail, name) VALUES
(1, '1-2', 'Teluk', 'Karang'),
(2, '1-8', 'Cimeng', 'Sukaraja 1-8'),
(3, '1-3', 'Raja BasaI', 'Kedaton 1-3'),
(4, '1-2', 'Sukabumi', 'Sukarame 1-2'),
(5, '1-10', 'Tanjung Senang', 'Jati Agung 1-10');

INSERT INTO tb_trip (id, fare, journey_time, agency_id, bus_id, dest_stop_id, source_stop_id) VALUES
(1, 15000, 15, 1, 1, 1, 3),
(2, 15000, 2, 2, 2, 5, 2),
(3, 15000, 3, 3, 2, 2, 3),
(4, 15000, 5, 2, 1, 4, 4),
(5, 15000, 4, 2, 2, 2, 5),
(6, 15000, 7, 3, 1, 3, 5);