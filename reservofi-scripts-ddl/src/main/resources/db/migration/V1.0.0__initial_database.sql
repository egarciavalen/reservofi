-- Tabla rol
create sequence rol_seq start with 1 increment by 1;
create table rol (
	id bigint default nextval('rol_seq'),
	nombre varchar(100) not null,
	creacion_usuario varchar(100) not null,
	creacion_fecha timestamp not null,
	actualizacion_usuario varchar(100) null,
	actualizacion_fecha timestamp null,
	constraint pk_rol primary key (id),
	constraint uq_rol_nombre unique (nombre)
);
-- Index
create index idx_rol_nombre on rol(nombre);

-- Tabla usuario
create sequence usuario_seq start with 1 increment by 1;
create table usuario (
	id bigint default nextval('usuario_seq'),
	nombre varchar(100) not null,
	primer_apellido varchar(100) not null,
	segundo_apellido varchar(100) not null,
	correo_corporativo varchar(100) not null,
	contrasenia varchar(255) not null,
	activo boolean not null,
	creacion_usuario varchar(100) not null,
	creacion_fecha timestamp not null,
	actualizacion_usuario varchar(100) null,
	actualizacion_fecha timestamp null,
	constraint pk_usuario primary key (id),
	constraint uq_usuario_correo_corporativo unique (correo_corporativo)
);
-- Index
create index idx_usuario_nombre on usuario(nombre);
create index idx_usuario_primer_apellido on usuario(primer_apellido);
create index idx_usuario_segundo_apellido on usuario(segundo_apellido);
create index idx_usuario_correo_corporativo on usuario(correo_corporativo);
create index idx_usuario_activo on usuario(activo);

-- Tabla usuario rol
create table usuario_rol (
	id_usuario bigint not null,
	id_rol bigint not null,
	constraint pk_usuario_rol primary key (id_usuario, id_rol),
	constraint fk_usuario_rol_usuario foreign key(id_usuario) references usuario(id),
	constraint fk_usuario_rol_rol foreign key(id_rol) references rol(id)
);
create index idx_usuario_rol_usuario on usuario_rol(id_usuario);
create index idx_usuario_rol_rol on usuario_rol(id_rol);


-- Tabla sala
create sequence sala_seq start with 1 increment by 1;
create table sala (
	id bigint default nextval('sala_seq'),
	nombre varchar(100) not null,
	ruta_imagen varchar(255) not null,
	nombre_imagen varchar(100) not null,
	creacion_usuario varchar(100) not null,
	creacion_fecha timestamp not null,
	actualizacion_usuario varchar(100) null,
	actualizacion_fecha timestamp null,
	constraint pk_sala primary key (id),
	constraint uq_sala_nombre unique (nombre)
);
-- Index
create index idx_sala_nombre on sala(nombre);


-- Tabla puesto
create sequence puesto_seq start with 1 increment by 1;
create table puesto (
	id bigint default nextval('puesto_seq'),
	id_sala bigint not null,
	nombre varchar(100) not null,
	coordenada_x numeric(8,2) not null,
	coordenada_y numeric (8,2) not null,
	ancho numeric (8,2) not null,
	alto numeric (8,2) not null,
	creacion_usuario varchar(100) not null,
	creacion_fecha timestamp not null,
	actualizacion_usuario varchar(100) null,
	actualizacion_fecha timestamp null,
	constraint pk_puesto primary key (id),
	constraint uq_puesto_nombre unique (nombre),
	constraint fk_puesto_sala foreign key(id_sala) references sala(id)
);
create index idx_puesto_nombre on puesto(nombre);
create index idx_puesto_sala on puesto(id_sala);

-- Tabla reserva
create sequence reserva_seq start with 1 increment by 1;
create table reserva (
	id bigint default nextval('puesto_seq'),
	id_usuario bigint not null,
	id_puesto bigint not null,
	fecha_inicio timestamp not null,
	fecha_fin timestamp not null,
	creacion_usuario varchar(100) not null,
	creacion_fecha timestamp not null,
	actualizacion_usuario varchar(100) null,
	actualizacion_fecha timestamp null,
	constraint pk_reserva primary key (id),
	constraint fk_reserva_usuario foreign key(id_usuario) references usuario(id),
	constraint fk_reserva_puesto foreign key(id_puesto) references usuario(id)

);
create index idx_reserva_fecha_inicio on reserva(fecha_inicio);
create index idx_reserva_fecha_fin on reserva(fecha_fin);