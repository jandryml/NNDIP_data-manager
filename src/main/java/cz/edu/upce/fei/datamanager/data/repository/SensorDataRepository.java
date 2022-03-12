package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.entity.SensorDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, SensorDataId> {

    @Query(value = "SELECT * FROM data WHERE sensor_id = ?1 GROUP BY data_time ORDER BY data_time DESC LIMIT 1 ", nativeQuery = true)
    Optional<SensorData> findLastValueBySensorId(long sensorId);

    @Query(value = "SELECT * FROM data WHERE data_time >= ?1 AND data_time <= ?2", nativeQuery = true)
    List<SensorData> findValuesBetween(Timestamp from, Timestamp to);
}