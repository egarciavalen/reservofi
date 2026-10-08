-- SCRIPT

-- ROL
insert into ROL(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Administrador', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');
insert into ROL(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Empleado', 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');
insert into ROL(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Rol vacio', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

-- USUARIO
insert into USUARIO(nombre, primer_apellido, segundo_apellido, correo_corporativo, contrasenia_hash, activo, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Admin', 'Admin primer apellido', 'Admin segundo apellido', 'admin@gmail.com', '123hash', true, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

insert into USUARIO(nombre, primer_apellido, segundo_apellido, correo_corporativo, contrasenia_hash, activo, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Empleado', 'Empleado primer apellido', 'Empleado segundo apellido', 'empleado@gmail.com', '456hash', true, 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');

insert into USUARIO(nombre, primer_apellido, segundo_apellido, correo_corporativo, contrasenia_hash, activo, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha)
values ('Extra', 'Extra primer apellido', 'Extra segundo apellido', 'extra@gmail.com', '789hash', true, 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');


-- USUARIO ROL
insert into USUARIO_ROL(id_usuario, id_rol) values (1, 1);
insert into USUARIO_ROL(id_usuario, id_rol) values (2, 2);


-- SALA
insert into SALA(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Sala 1', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');
insert into SALA(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Sala 2', 'admin', timestamp '2026-10-05 00:00:00', 'admin_update', timestamp '2026-10-06 00:00:00');
insert into SALA(nombre, creacion_usuario, creacion_fecha, actualizacion_usuario, actualizacion_fecha) values ('Sala 3', 'admin', timestamp '2026-10-05 00:00:00', 'admin', timestamp '2026-10-05 00:00:00');

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

