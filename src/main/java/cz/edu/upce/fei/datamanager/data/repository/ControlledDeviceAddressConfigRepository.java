package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.ControlledDeviceAddressConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ControlledDeviceAddressConfigRepository extends JpaRepository<ControlledDeviceAddressConfig, Long> {

    int deleteByControlledDeviceType(ControlledDeviceType deviceType);

    Optional<ControlledDeviceAddressConfig> findByControlledDeviceType(ControlledDeviceType deviceType);
}
