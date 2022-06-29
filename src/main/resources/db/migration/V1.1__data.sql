INSERT INTO tb_user(email, first_name, last_name, mobile_number, password, username)
VALUES ('chan@gmail.com','Chandra','aja','094727437','chan123','chan');

INSERT INTO tb_agency(code, details, name, owner_user_id)
VALUES ('BDL','PT Angkasa Muda','Angkot Karang',1);

INSERT INTO tb_bus(capacity, code, make, agency_id)
VALUES (20,'BDL01','20',1);

INSERT INTO tb_bus(capacity, code, make, agency_id)
VALUES (15,'BDL02','15',1);

INSERT INTO tb_role (name) VALUES ('ROLE_USER');
INSERT INTO tb_role (name) VALUES ('ROLE_ADMIN');

insert into tb_stop values
(1, '1-2', 'Teluk', 'Karang'),
(2, '1-8', 'Cimeng', 'Sukaraja 1-8'),
(3, '1-3', 'Raja BasaI', 'Kedaton 1-3'),
(4, '1-2', 'Sukabumi', 'Sukarame 1-2'),
(5, '1-10', 'Tanjung Senang', 'Jati Agung 1-10');

INSERT INTO tb_trip VALUES
(1, 15000, 15, 1, 1, 1, 3),
(2, 15000, 2, 1, 1, 5, 2),
(3, 15000, 3, 1, 1, 2, 3),
(4, 15000, 5, 1, 1, 4, 4),
(5, 15000, 4, 1, 1, 2, 5),
(6, 15000, 7, 1, 1, 3, 5);