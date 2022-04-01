package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.DashboardSensorConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardSensorConfigRepository extends JpaRepository<DashboardSensorConfig, Long> {

    List<DashboardSensorConfig> findByMeasuredValueType(MeasuredValueType measuredValueType);
}
