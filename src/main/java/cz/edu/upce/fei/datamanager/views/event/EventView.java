package cz.edu.upce.fei.datamanager.views.event;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.Event;
import cz.edu.upce.fei.datamanager.data.service.EventService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

/**
 * A Designer generated component for the event-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */



@PageTitle("Event config")
@Route(value = "events", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("event-view")
@JsModule("./src/views/event/event-view.ts")
public class EventView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addEventButton")
    private Button addEventButton;
    @Id("grid")
    private Grid<Event> grid;
    @Id("eventForm")
    private EventForm eventForm;

    private final EventService eventService;
    
    /**
     * Creates a new EventView.
     */
    public EventView(EventService eventService) {
        this.eventService = eventService;
        configureGrid();
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
        closeEditor();
    }

    private void configureGrid() {

        grid.addColumn(Event::getName).setHeader("Name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editEvent(event.getValue()));
    }

    private void configureBinding() {
        addEventButton.addClickListener(click -> addEvent());

        eventForm.addListener(EventForm.SaveEvent.class, this::saveEvent);
        eventForm.addListener(EventForm.DeleteEvent.class, this::deleteEvent);
        eventForm.addListener(EventForm.CloseEvent.class, e -> closeEditor());
    }

    private void addEvent() {
        grid.asSingleSelect().clear();
        editEvent(new Event());
    }

    private void saveEvent(EventForm.SaveEvent event) {
        eventService.saveEvent(event.getEvent());
        updateList();
        closeEditor();
    }

    private void deleteEvent(EventForm.DeleteEvent event) {
        eventService.deleteEvent(event.getEvent());
        updateList();
        closeEditor();
    }

    public void editEvent(Event manualPlan) {
        if (manualPlan == null) {
            closeEditor();
        } else {
            eventForm.setActualEvent(manualPlan);
            eventForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        eventForm.setActualEvent(null);
        eventForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(eventService.findAllEvents());
    }

}
