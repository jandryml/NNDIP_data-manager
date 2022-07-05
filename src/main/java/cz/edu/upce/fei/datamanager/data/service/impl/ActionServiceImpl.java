package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.data.repository.ActionRepository;
import cz.edu.upce.fei.datamanager.data.service.ActionService;
import cz.edu.upce.fei.datamanager.exception.DefaultActionAlreadySetException;
import cz.edu.upce.fei.datamanager.exception.EntityRemovalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    @Override
    public List<Action> findAllActions() {
        return actionRepository.findAll();
    }

    @Override
    public List<Action> findAllActions(String stringFilter) {
        return actionRepository.searchByName(stringFilter);
    }

    @Override
    public void saveAction(Action action) throws DefaultActionAlreadySetException {
        if (action.getIsDefault() && isDefaultActionAlreadySet(action))
            throw new DefaultActionAlreadySetException("Default action already exists!");
        actionRepository.save(action);
    }

    private boolean isDefaultActionAlreadySet(Action action) {
        return actionRepository.countOfDefaultValuesByAddressAndOutputType(action.getAddress(), action.getOutputType().name()) > 0;
    }

    @Override
    public void deleteAction(Action action) throws EntityRemovalException {
        try {
            actionRepository.delete(action);

        } catch (Exception e) {
            throw new EntityRemovalException("Action '" + action.getName() + "' cannot be removed. " +
                    "Another entities are probably using this");
        }
    }
}
