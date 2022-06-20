package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.LimitPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitPlanRepository extends JpaRepository<LimitPlan, Long> {

    int deleteByName(String name);

    Optional<LimitPlan> findByName(String name);
}
