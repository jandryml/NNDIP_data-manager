package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;
import cz.edu.upce.fei.datamanager.data.repository.ThresholdActionRepository;
import cz.edu.upce.fei.datamanager.data.service.ThresholdActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThresholdActionServiceImpl implements ThresholdActionService {

    private final ThresholdActionRepository thresholdActionRepository;

    @Override
    public List<ThresholdAction> findAllThresholdActions() {
        return thresholdActionRepository.findAll();
    }

    @Override
    public List<ThresholdAction> findAllThresholdActions(String stringFilter) {
        return thresholdActionRepository.searchByName(stringFilter);
    }

    @Override
    public void saveThresholdAction(ThresholdAction thresholdAction) {
        thresholdActionRepository.save(thresholdAction);
    }

    @Override
    public void deleteThresholdAction(ThresholdAction thresholdAction) {
        thresholdActionRepository.delete(thresholdAction);
    }
}
