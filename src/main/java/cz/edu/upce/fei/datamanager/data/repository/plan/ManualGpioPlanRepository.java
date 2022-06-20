package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.ManualGpioPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManualGpioPlanRepository  extends JpaRepository<ManualGpioPlan, Long> {
    @Query("select mgp from manual_gpio_plan mgp " +
            "where lower(mgp.name) like lower(concat('%', :searchTerm, '%'))")
    List<ManualGpioPlan> findByName(@Param("searchTerm") String searchTerm);
}
