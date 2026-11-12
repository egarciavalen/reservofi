-- Tabla rol
CREATE SEQUENCE rol_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE rol (
	id bigint DEFAULT nextval('rol_seq'),
	nombre varchar(100) NOT NULL,
	creacion_usuario varchar(100) NOT NULL,
	creacion_fecha timestamp NOT NULL,
	actualizacion_usuario varchar(100) NULL,
	actualizacion_fecha timestamp NULL,
	CONSTRAINT pk_rol PRIMARY KEY (id),
	CONSTRAINT uq_rol_nombre UNIQUE (nombre)
);
-- Index
CREATE INDEX idx_rol_nombre ON rol(nombre);

-- Tabla usuario
CREATE SEQUENCE usuario_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE usuario (
	id bigint DEFAULT nextval('usuario_seq'),
	nombre varchar(100) not null,
	primer_apellido varchar(100) not null,
	segundo_apellido varchar(100) not null,
	correo_corporativo varchar(100) not null,
	contrasenia varchar(255) not null,
	activo boolean not null,
	creacion_usuario varchar(100) NOT NULL,
	creacion_fecha timestamp NOT NULL,
	actualizacion_usuario varchar(100) NULL,
	actualizacion_fecha timestamp NULL,
	CONSTRAINT pk_usuario PRIMARY KEY (id),
	CONSTRAINT uq_usuario_correo_corporativo UNIQUE (correo_corporativo)
);
-- Index
CREATE INDEX idx_usuario_nombre ON usuario(nombre);
CREATE INDEX idx_usuario_primer_apellido ON usuario(primer_apellido);
CREATE INDEX idx_usuario_segundo_apellido ON usuario(segundo_apellido);
CREATE INDEX idx_usuario_correo_corporativo ON usuario(correo_corporativo);
CREATE INDEX idx_usuario_activo ON usuario(activo);

-- Tabla usuario rol
CREATE TABLE usuario_rol (
	id_usuario bigint NOT NULL,
	id_rol bigint NOT NULL,
	CONSTRAINT pk_usuario_rol PRIMARY KEY (id_usuario, id_rol),
	CONSTRAINT fk_usuario_rol_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id),
	CONSTRAINT fk_usuario_rol_rol FOREIGN KEY(id_rol) REFERENCES rol(id)
);
CREATE INDEX idx_usuario_rol_usuario ON usuario_rol(id_usuario);
CREATE INDEX idx_usuario_rol_rol ON usuario_rol(id_rol);


-- Tabla sala
CREATE SEQUENCE sala_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE sala (
	id bigint DEFAULT nextval('sala_seq'),
	nombre varchar(100) NOT NULL,
	ruta_imagen varchar(255) NOT NULL,
	nombre_imagen varchar(100) NOT NULL,
	creacion_usuario varchar(100) NOT NULL,
	creacion_fecha timestamp NOT NULL,
	actualizacion_usuario varchar(100) NULL,
	actualizacion_fecha timestamp NULL,
	CONSTRAINT pk_sala PRIMARY KEY (id),
	CONSTRAINT uq_sala_nombre UNIQUE (nombre)
);
-- Index
CREATE INDEX idx_sala_nombre ON sala(nombre);


-- Tabla puesto
CREATE SEQUENCE puesto_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE puesto (
	id bigint DEFAULT nextval('puesto_seq'),
	id_sala bigint NOT NULL,
	nombre varchar(100) NOT NULL,
	coordenada_x numeric(8,2) NOT NULL,
	coordenada_y numeric (8,2) NOT NULL,
	creacion_usuario varchar(100) NOT NULL,
	creacion_fecha timestamp NOT NULL,
	actualizacion_usuario varchar(100) NULL,
	actualizacion_fecha timestamp NULL,
	CONSTRAINT pk_puesto PRIMARY KEY (id),
	CONSTRAINT uq_puesto_nombre UNIQUE (nombre),
	CONSTRAINT fk_puesto_sala FOREIGN KEY(id_sala) REFERENCES sala(id)
);
CREATE INDEX idx_puesto_nombre ON puesto(nombre);
CREATE INDEX idx_puesto_sala ON puesto(id_sala);

-- Tabla reserva
CREATE SEQUENCE reserva_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE reserva (
	id bigint DEFAULT nextval('puesto_seq'),
	id_usuario bigint NOT NULL,
	id_puesto bigint NOT NULL,
	fecha_inicio timestamp NOT NULL,
	fecha_fin timestamp NOT NULL,
	creacion_usuario varchar(100) NOT NULL,
	creacion_fecha timestamp NOT NULL,
	actualizacion_usuario varchar(100) NULL,
	actualizacion_fecha timestamp NULL,
	CONSTRAINT pk_reserva PRIMARY KEY (id),
	CONSTRAINT fk_reserva_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id),
	CONSTRAINT fk_reserva_puesto FOREIGN KEY(id_puesto) REFERENCES usuario(id)

);
CREATE INDEX idx_reserva_fecha_inicio ON reserva(fecha_inicio);
CREATE INDEX idx_reserva_fecha_fin ON reserva(fecha_fin);