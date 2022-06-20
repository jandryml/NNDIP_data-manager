package cz.edu.upce.fei.datamanager.data.repository.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.TimePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimePlanRepository extends JpaRepository<TimePlan, Long> {

    @Query("select tp from TimePlan tp " +
            "where lower(tp.name) like lower(concat('%', :searchTerm, '%'))")
    List<TimePlan> findByName(@Param("searchTerm") String searchTerm);
}
