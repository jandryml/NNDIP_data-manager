package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.data.repository.ActionRepository;
import cz.edu.upce.fei.datamanager.data.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void saveAction(Action action) {
        actionRepository.save(action);
    }

    @Override
    public void deleteAction(Action action) {
        actionRepository.delete(action);
    }
}
