package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.AddressState;
import cz.edu.upce.fei.datamanager.data.entity.AddressStateId;
import cz.edu.upce.fei.datamanager.data.repository.AddressStateRepository;
import cz.edu.upce.fei.datamanager.data.service.AddressStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressStateServiceImpl implements AddressStateService {

    private final AddressStateRepository addressStateRepository;

    @Override
    public List<AddressState> getAllAddressesState() {
        return addressStateRepository.findAll();
    }

    @Override
    public Optional<AddressState> getById(AddressStateId id) {
        return addressStateRepository.findById(id);
    }
}
