--liquibase formatted sql
--changeset samuel.junnior:1.0 context:prod
INSERT INTO tb_roles VALUES (1, 'ADMIN');
INSERT INTO tb_roles VALUES (2, 'BASIC');