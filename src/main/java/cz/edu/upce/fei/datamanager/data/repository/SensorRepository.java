package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("select s from Sensor s " +
            "where lower(s.name) like lower(concat('%', :searchTerm, '%'))")
    List<Sensor> findByName(@Param("searchTerm") String searchTerm);

    List<Sensor> findBySensorTypeIn(List<SensorType> sensorTypes);
}
