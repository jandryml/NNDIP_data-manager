package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.exception.DefaultActionAlreadySetException;
import cz.edu.upce.fei.datamanager.exception.EntityRemovalException;

import java.util.List;

public interface ActionService {

    List<Action> findAllActions();

    List<Action> findAllActions(String stringFilter);

    void saveAction(Action action) throws DefaultActionAlreadySetException;

    void deleteAction(Action action) throws EntityRemovalException;
}
