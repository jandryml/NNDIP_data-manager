package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.TimeGpioPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeGpioPlanRepository extends JpaRepository<TimeGpioPlan, Long> {
    @Query("select tgp from time_gpio_plan tgp " +
            "where lower(tgp.name) like lower(concat('%', :searchTerm, '%'))")
    List<TimeGpioPlan> findByName(@Param("searchTerm") String searchTerm);
}
