insert into USUARIO values (nextval('usuario_seq'), 'Admin', 'Admin', 'Admin', 'admin@gmail.com', '$2a$10$kW36WV8QKh1Hyj//dNnds.z2Yxh/5Yo1MnYA5pkxg1XT.dftgpQwq', true, 'admin', timestamp '2026-11-12 00:00:00', 'admin', timestamp '2026-11-12 00:00:00');

insert into usuario_rol values ((select id from usuario where nombre = 'Admin'), (select id from rol where nombre = 'Administrador'));