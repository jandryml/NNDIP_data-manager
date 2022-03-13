package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThresholdActionRepository extends JpaRepository<ThresholdAction, UUID> {

    @Query("select ta from ThresholdAction ta " +
            "where lower(ta.name) like lower(concat('%', :searchTerm, '%'))")
    List<ThresholdAction> searchByName(@Param("searchTerm") String searchTerm);

}
