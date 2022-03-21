package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.ManualPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualPlanRepository extends JpaRepository<ManualPlan, Long> {
}
