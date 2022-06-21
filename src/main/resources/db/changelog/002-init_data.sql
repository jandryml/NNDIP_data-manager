--liquibase formatted sql
--changeset drymlj:2

INSERT INTO action (id, address, name, output_type, value)
VALUES  (1, '0', 'AC unit - ON', 'MODBUS_VALUE', '1'),
        (2, '0', 'AC unit - OFF', 'MODBUS_VALUE', '0'),
        (3, '1', 'AC unit Mode - Auto', 'MODBUS_VALUE', '0'),
        (4, '1', 'AC unit Mode - Heat', 'MODBUS_VALUE', '1'),
        (5, '1', 'AC unit Mode - Dry', 'MODBUS_VALUE', '2'),
        (6, '1', 'AC unit Mode - Fan', 'MODBUS_VALUE', '3'),
        (7, '1', 'AC unit Mode - Cool', 'MODBUS_VALUE', '4'),
        (8, '2', 'AC unit Fan Speed - Low', 'MODBUS_VALUE', '1'),
        (9, '2', 'AC unit Fan Speed - Mid', 'MODBUS_VALUE', '2'),
        (10, '2', 'AC unit Fan Speed - High', 'MODBUS_VALUE', '3'),
        (11, '2', 'AC unit Fan Speed - Powerful', 'MODBUS_VALUE', '4'),
        (12, '11', 'Recuperation - OFF', 'RASPBERRY_PIN', '0'),
        (13, '11', 'Recuperation - ON', 'RASPBERRY_PIN', '1');

INSERT INTO controlled_device_address_config (controlled_device_type, address, output_type)
VALUES  ('AC_FAN', '2', 'MODBUS_VALUE'),
        ('AC_MODE', '1', 'MODBUS_VALUE'),
        ('AC_STATUS', '0', 'MODBUS_VALUE'),
        ('RECUPERATION', '11', 'RASPBERRY_PIN');

INSERT INTO event (id, name)
VALUES  (1, 'Provětrávání'),
        (2, 'Topení'),
        (3, 'Chlazení');

INSERT INTO event_actions (event_id, action_id)
VALUES  (1, 1),
        (1, 6),
        (1, 11),
        (1, 13),
        (2, 1),
        (2, 4),
        (2, 11),
        (2, 12),
        (3, 1),
        (3, 7),
        (3, 11),
        (3, 12);

INSERT INTO sensor (id, name, sensor_type)
VALUES  (1, 'Obyvak', 'MIXED'),
        (2, 'Pokoj', 'CO2'),
        (3, 'Kuchyn', 'CO2');

INSERT INTO year_period (id, name, active) 
VALUES (1, 'SUMMER', true),
       (2, 'WINTER', false);

INSERT INTO plan (id, enabled, name, event_id, priority, plan_type)
VALUES  (1, true, 'Polední větrání', 1, 50, 'TIME_PLAN'),
        (2, false, 'Manual - Provětrávání', 1, 100, 'MANUAL_PLAN'),
        (3, true, 'TEMPERATURE_LOW', 2, 0, 'LIMIT_PLAN'),
        (4, true, 'TEMPERATURE_HIGH', 3, 0, 'LIMIT_PLAN'),
        (5, true, 'CO2', 1, 10, 'LIMIT_PLAN'),
        (6, true, 'TEMPERATURE_LOW', 2, 0, 'LIMIT_PLAN'),
        (7, true, 'TEMPERATURE_HIGH', 3, 0, 'LIMIT_PLAN'),
        (8, true, 'CO2', 1, 10, 'LIMIT_PLAN'),
        (9, true, 'Manual GPIO trigger', 1, 100, 'MANUAL_GPIO_PLAN'),
        (10, true, 'Time GPIO trigger', 1, 100, 'TIME_GPIO_PLAN');

INSERT INTO limit_plan (id, optimal_value, threshold_value, value_type, year_period_id)
VALUES  (3, 21.5, 20, 'TEMPERATURE_LOW',1),
        (4, 21.5, 23, 'TEMPERATURE_HIGH',1),
        (5, 450, 850, 'CO2',1),
        (6, 20, 19, 'TEMPERATURE_LOW',2),
        (7, 20, 21, 'TEMPERATURE_HIGH',2),
        (8, 500, 900, 'CO2',2);

INSERT INTO time_plan (id, from_time, to_time)
VALUES  (1, '11:00:00', '15:00:00');

INSERT INTO manual_plan (id)
VALUES  (2);

INSERT INTO gpio_plan (id, address, default_state)
VALUES  (9, 'GPIO_04', 'HIGH'),
        (10, 'GPIO_08', 'LOW');

INSERT INTO manual_gpio_plan (id, turned_on)
VALUES  (9, false);

INSERT INTO time_gpio_plan (id, duration, last_triggered)
VALUES  (10, 30 , '2022-06-20 20:53:06');

INSERT INTO dashboard_sensor_config (id, measured_value_type, sensor_id)
VALUES  (1, 'TEMPERATURE', 1),
        (2, 'HUMIDITY', 1),
        (3, 'CO2', 1),
        (4, 'CO2', 2),
        (5, 'CO2', 3);

INSERT INTO user (id, hashed_password, name, profile_picture_url, username)
VALUES  (1, '$2a$10$GqxXZehtn/RnJGCQNQQAgusv4YUXJkfmdR1jJjNNT.Qc.dAUsIsRC', 'John Normal', 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80', 'user'),
        (2, '$2a$10$ZEghMxwsCOz7sdqwgtrPM.BSdUzBWc5BAwmV6fT2vyuO5AGIyb04q', 'Emma Powerful', 'https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80', 'admin');

INSERT INTO user_roles (user_id, roles)
VALUES  (1, 0),
        (2, 0),
        (2, 1);