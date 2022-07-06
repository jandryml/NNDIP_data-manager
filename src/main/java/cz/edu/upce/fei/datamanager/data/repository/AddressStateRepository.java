package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.AddressState;
import cz.edu.upce.fei.datamanager.data.entity.AddressStateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressStateRepository extends JpaRepository<AddressState, AddressStateId> {
}
