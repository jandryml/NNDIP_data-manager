package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.Action;

import java.util.List;

public interface ActionService {

    List<Action> findAllActions();

    List<Action> findAllActions(String stringFilter);

    void saveAction(Action action);

    void deleteAction(Action action);
}
