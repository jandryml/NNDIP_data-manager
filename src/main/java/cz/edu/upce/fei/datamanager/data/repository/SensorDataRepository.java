package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    @Query(value = "SELECT * FROM data WHERE sensor_id = ?1 ORDER BY data_timestamp DESC LIMIT 1", nativeQuery = true)
    Optional<SensorData> findLastValueBySensorId(long sensorId);

    List<SensorData> findBySensorIdAndTimestampBetween(Long sensorId, Timestamp from, Timestamp to);
}