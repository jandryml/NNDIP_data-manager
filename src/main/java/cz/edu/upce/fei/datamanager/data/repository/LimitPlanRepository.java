package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.LimitPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitPlanRepository extends JpaRepository<LimitPlan, Long> {
}
