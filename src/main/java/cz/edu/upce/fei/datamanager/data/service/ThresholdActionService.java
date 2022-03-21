package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;

import java.util.List;

public interface ThresholdActionService {

    List<ThresholdAction> findAllThresholdActions();

    List<ThresholdAction> findAllThresholdActions(String stringFilter);

    void saveThresholdAction(ThresholdAction thresholdAction);

    void deleteThresholdAction(ThresholdAction thresholdAction);
}
