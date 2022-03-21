package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.TimePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimePlanRepository extends JpaRepository<TimePlan, Long> {
}
