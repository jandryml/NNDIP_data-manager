--liquibase formatted sql
--changeset drymlj:2

insert into action (id, address, name, output_type, value)
values  (1, '0', 'AC unit - ON', 'MODBUS_VALUE', '1'),
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

insert into controlled_device_address_config (controlled_device_type, address, output_type)
values  ('AC_FAN', '2', 'MODBUS_VALUE'),
        ('AC_MODE', '1', 'MODBUS_VALUE'),
        ('AC_STATUS', '0', 'MODBUS_VALUE'),
        ('RECUPERATION', '11', 'RASPBERRY_PIN');

insert into event (id, name)
values  (1, 'Provětrávání'),
        (2, 'Topení'),
        (3, 'Chlazení');

insert into event_actions (event_id, action_id)
values  (1, 1),
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

insert into plan (id, enabled, name, event_id)
values  (16, true, 'Polední větrání', 1),
        (17, false, 'Manual - Provětrávání', 1),
        (18, true, 'TEMPERATURE_LOW', 2),
        (19, true, 'TEMPERATURE_HIGH', 3),
        (20, true, 'CO2', 1);

insert into limit_plan (id, optimal_value, threshold_value, value_type)
values  (18, 21.5, 20, 'TEMPERATURE_LOW'),
        (19, 21.5, 23, 'TEMPERATURE_HIGH'),
        (20, 450, 850, 'CO2');

insert into manual_plan (id)
values  (17);

insert into sensor (id, name, sensor_type)
values  (1, 'Obyvak', 'MIXED'),
        (2, 'Pokoj', 'CO2'),
        (3, 'Kuchyn', 'CO2');

insert into dashboard_sensor_config (id, measured_value_type, sensor_id)
values  (1, 'TEMPERATURE', 1),
        (2, 'HUMIDITY', 1),
        (3, 'CO2', 1),
        (4, 'CO2', 2),
        (5, 'CO2', 3);

insert into time_plan (id, from_time, to_time)
values  (16, '11:00:00', '15:00:00');

insert into user (id, hashed_password, name, profile_picture_url, username)
values  (1, '$2a$10$GqxXZehtn/RnJGCQNQQAgusv4YUXJkfmdR1jJjNNT.Qc.dAUsIsRC', 'John Normal', 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80', 'user'),
        (2, '$2a$10$ZEghMxwsCOz7sdqwgtrPM.BSdUzBWc5BAwmV6fT2vyuO5AGIyb04q', 'Emma Powerful', 'https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80', 'admin');

insert into user_roles (user_id, roles)
values  (1, 0),
        (2, 0),
        (2, 1);