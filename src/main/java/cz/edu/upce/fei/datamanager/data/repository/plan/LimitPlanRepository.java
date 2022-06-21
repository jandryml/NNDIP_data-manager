package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.limit.LimitPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitPlanRepository extends JpaRepository<LimitPlan, Long> {

    int deleteByNameAndYearPeriod(String name, YearPeriod yearPeriod);

    Optional<LimitPlan> findByNameAndYearPeriod(String name, YearPeriod yearPeriod);
}
