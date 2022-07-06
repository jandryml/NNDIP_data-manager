package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.ControlledDeviceAddressConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;

import java.util.List;
import java.util.Optional;

public interface ControlledDeviceStatusService {

    List<ControlledDeviceAddressConfig> findAllAddressConfig();

    Optional<ControlledDeviceAddressConfig> findByAddressConfig(ControlledDeviceType deviceType);

    void saveAddressConfig(ControlledDeviceAddressConfig addressConfig);

    void deleteAddressConfig(ControlledDeviceAddressConfig addressConfig);
}
