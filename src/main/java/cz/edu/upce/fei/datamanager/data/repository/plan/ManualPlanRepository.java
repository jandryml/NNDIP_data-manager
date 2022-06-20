package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.ManualPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManualPlanRepository extends JpaRepository<ManualPlan, Long> {

    @Query("select mp from ManualPlan mp " +
            "where lower(mp.name) like lower(concat('%', :searchTerm, '%'))")
    List<ManualPlan> findByName(@Param("searchTerm") String searchTerm);
}
