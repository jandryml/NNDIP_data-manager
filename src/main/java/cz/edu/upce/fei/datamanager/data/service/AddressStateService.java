package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.AddressState;
import cz.edu.upce.fei.datamanager.data.entity.AddressStateId;

import java.util.List;
import java.util.Optional;

public interface AddressStateService {
    List<AddressState> getAllAddressesState();

    Optional<AddressState> getById(AddressStateId id);
}
