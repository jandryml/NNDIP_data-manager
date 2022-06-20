--liquibase formatted sql
--changeset drymlj:4

UPDATE plan SET priority = 50 WHERE id = 16;
UPDATE plan SET priority = 100 WHERE id = 17;
UPDATE plan SET priority = 10 WHERE id = 20;

