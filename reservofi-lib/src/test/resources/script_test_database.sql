-- Limpieza de tablas
delete from usuario_rol;
delete from reserva;
delete from puesto;
delete from sala;
delete from usuario;
delete from rol;

-- Se reinician las secuencias
ALTER TABLE reserva ALTER COLUMN id RESTART WITH 1;
ALTER TABLE puesto ALTER COLUMN id RESTART WITH 1;
ALTER TABLE sala ALTER COLUMN id RESTART WITH 1;
ALTER TABLE usuario ALTER COLUMN id RESTART WITH 1;
ALTER TABLE rol ALTER COLUMN id RESTART WITH 1;

-- Inserciones

-- ROL
insert into ROL(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Administrador', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');
insert into ROL(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Empleado', 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');
insert into ROL(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Rol vacio', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

-- USUARIO
insert into USUARIO(nombre, primer_apellido, segundo_apellido, correo_corporativo, contrasenia, activo, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Admin', 'Admin primer apellido', 'Admin segundo apellido', 'admin@gmail.com', '$2a$10$31NgYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD', true, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

insert into USUARIO(nombre, primer_apellido, segundo_apellido, correo_corporativo, contrasenia, activo, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Empleado', 'Empleado primer apellido', 'Empleado segundo apellido', 'empleado@gmail.com', '$2a$10$78lGYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD', true, 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');

insert into USUARIO(nombre, primer_apellido, segundo_apellido, correo_corporativo, contrasenia, activo, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Extra', 'Extra primer apellido', 'Extra segundo apellido', 'extra@gmail.com', '$2a$10$572WYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD', true, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

-- USUARIO ROL
insert into USUARIO_ROL(id_usuario, id_rol) values (1, 1);
insert into USUARIO_ROL(id_usuario, id_rol) values (2, 2);

-- SALA
insert into SALA(nombre, ruta_imagen, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Sala 1', 'src/main/resources/static/images/img1.png', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');
insert into SALA(nombre, ruta_imagen, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Sala 2', 'src/main/resources/static/images/img2.png', 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');
insert into SALA(nombre, ruta_imagen, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Sala 3', 'src/main/resources/static/images/img3.png', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

-- PUESTO
insert into PUESTO(nombre, coordenada_x, coordenada_y, id_sala, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Puesto 1', 55.55, 43.22, 1, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

insert into PUESTO(nombre, coordenada_x, coordenada_y, id_sala, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Puesto 2', 32.12, 87.93, 2, 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');

insert into PUESTO(nombre, coordenada_x, coordenada_y, id_sala, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Puesto 3', 78.98, 15.32, 1, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');


-- RESERVA
insert into RESERVA(fecha_inicio, fecha_fin, id_usuario, id_puesto, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values (timestamp '2026-10-06 00:00:00', timestamp '2026-10-07 00:00:00', 1, 1, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

insert into RESERVA(fecha_inicio, fecha_fin, id_usuario, id_puesto, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values (timestamp '2026-10-08 00:00:00', timestamp '2026-10-09 00:00:00', 2, 2, 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');