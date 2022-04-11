package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.data.entity.ControlledDeviceAddressConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;
import cz.edu.upce.fei.datamanager.data.entity.plan.LimitPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ControlledDeviceAddressConfigRepository extends JpaRepository<ControlledDeviceAddressConfig, Long> {

    int deleteByControlledDeviceType(ControlledDeviceType deviceType);

    Optional<ControlledDeviceAddressConfig> findByControlledDeviceType(ControlledDeviceType deviceType);
}
