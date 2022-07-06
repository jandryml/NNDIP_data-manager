package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.ControlledDeviceAddressConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;
import cz.edu.upce.fei.datamanager.data.repository.ControlledDeviceAddressConfigRepository;
import cz.edu.upce.fei.datamanager.data.service.ControlledDeviceStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControlledDeviceStatusServiceImpl implements ControlledDeviceStatusService {

    private final ControlledDeviceAddressConfigRepository addressConfigRepository;

    @Override
    public List<ControlledDeviceAddressConfig> findAllAddressConfig() {
        return addressConfigRepository.findAll();
    }

    @Override
    public Optional<ControlledDeviceAddressConfig> findByAddressConfig(ControlledDeviceType deviceType) {
        return addressConfigRepository.findByControlledDeviceType(deviceType);
    }

    @Override
    public void saveAddressConfig(ControlledDeviceAddressConfig addressConfig) {
        // remove if same plan already exists
        deleteAddressConfig(addressConfig);
        addressConfigRepository.save(addressConfig);
    }

    @Override
    public void deleteAddressConfig(ControlledDeviceAddressConfig addressConfig) {
        addressConfigRepository.deleteByControlledDeviceType(addressConfig.getControlledDeviceType());
    }
}
