package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.PrimarySensorConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimarySensorConfigRepository extends JpaRepository<PrimarySensorConfig, String> {
}
