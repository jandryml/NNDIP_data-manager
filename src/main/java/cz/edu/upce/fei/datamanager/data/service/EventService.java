package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.Event;
import java.util.List;

public interface EventService {
    List<Event> findAllEvents();

    List<Event> findAllEvents(String nameFilter);

    void saveEvent(Event manualPlan);

    void deleteEvent(Event manualPlan);
}
