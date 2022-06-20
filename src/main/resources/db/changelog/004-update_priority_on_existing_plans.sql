--liquibase formatted sql
--changeset drymlj:4

UPDATE plan SET priority = 50, plan_type = 'TIME_PLAN'  WHERE id = 16;
UPDATE plan SET priority = 100, plan_type = 'MANUAL_PLAN' WHERE id = 17;
UPDATE plan SET plan_type = 'LIMIT_PLAN' WHERE id = 18;
UPDATE plan SET plan_type = 'LIMIT_PLAN' WHERE id = 19;
UPDATE plan SET priority = 10, plan_type = 'LIMIT_PLAN' WHERE id = 20;

