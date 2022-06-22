package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("select a from Action a " +
            "where lower(a.name) like lower(concat('%', :searchTerm, '%'))")
    List<Action> searchByName(@Param("searchTerm") String searchTerm);

    @Query("select count(a) from Action a where a.isDefault = true and a.address like :address")
    int countOfDefaultValuesByAddress(String address);
}
