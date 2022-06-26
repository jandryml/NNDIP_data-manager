package cz.edu.upce.fei.datamanager.data.entity.plan.gpio;

import lombok.Getter;

@Getter
public enum RaspiPin {
    GPIO_00("GPIO 0", 0),
    GPIO_01("GPIO 1", 1),
    GPIO_02("GPIO 2", 2),
    GPIO_03("GPIO 3", 3),
    GPIO_04("GPIO 4", 4),
    GPIO_05("GPIO 5", 5),
    GPIO_06("GPIO 6", 6),
    GPIO_07("GPIO 7", 7),
    GPIO_08("GPIO 8", 8),
    GPIO_09("GPIO 9", 9),
    GPIO_10("GPIO 10", 10),
    GPIO_11("GPIO 11", 11),
    GPIO_12("GPIO 12", 12),
    GPIO_13("GPIO 13", 13),
    GPIO_14("GPIO 14", 14),
    GPIO_15("GPIO 15", 15),
    GPIO_16("GPIO 16", 16),
    GPIO_17("GPIO 17", 17),
    GPIO_18("GPIO 18", 18),
    GPIO_19("GPIO 19", 19),
    GPIO_20("GPIO 20", 20),
    GPIO_21("GPIO 21", 21),
    GPIO_22("GPIO 22", 22),
    GPIO_23("GPIO 23", 23),
    GPIO_24("GPIO 24", 24),
    GPIO_25("GPIO 25", 25),
    GPIO_26("GPIO 26", 26),
    GPIO_27("GPIO 27", 27),
    GPIO_28("GPIO 28", 28),
    GPIO_29("GPIO 29", 29),
    GPIO_30("GPIO 30", 30),
    GPIO_31("GPIO 31", 31);

    private final String name;
    private final int address;

    RaspiPin(String name, int address) {
        this.name = name;
        this.address = address;
    }
}
