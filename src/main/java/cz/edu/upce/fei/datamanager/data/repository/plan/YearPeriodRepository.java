package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriod;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface YearPeriodRepository extends JpaRepository<YearPeriod, Long> {

    YearPeriod findByName(YearPeriodType periodType);

    @Query("select yp from YearPeriod yp where yp.active = true")
    YearPeriod getActive();

    @Modifying
    @Query("update YearPeriod yp set yp.active = false")
    int setAllToNotActive();

    @Modifying
    @Query("update YearPeriod yp set yp.active = true where yp.name = :periodType")
    int setOneToActive(YearPeriodType periodType);
}