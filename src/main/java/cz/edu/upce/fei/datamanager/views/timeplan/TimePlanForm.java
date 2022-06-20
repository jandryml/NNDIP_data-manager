package cz.edu.upce.fei.datamanager.views.timeplan;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.Event;
import cz.edu.upce.fei.datamanager.data.entity.plan.TimePlan;
import cz.edu.upce.fei.datamanager.data.service.EventService;

/**
 * A Designer generated component for the time-plan-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("time-plan-form")
@JsModule("./src/views/timeplan/time-plan-form.ts")
public class TimePlanForm extends LitTemplate {
    private TimePlan timePlan;

    @Id("name")
    private TextField name;
    @Id("priority")
    private IntegerField priority;
    @Id("event")
    private ComboBox<Event> event;
    @Id("fromTime")
    private TimePicker fromTime;
    @Id("toTime")
    private TimePicker toTime;
    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    Binder<TimePlan> binder = new BeanValidationBinder<>(TimePlan.class);

    private final EventService eventService;

    /**
     * Creates a new TimePlanForm.
     */
    public TimePlanForm(EventService eventService) {
        this.eventService = eventService;
        configureComponents();
        configureBinder();
    }


    private void configureBinder() {
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void configureComponents() {
        event.setItems(eventService.findAllEvents());
        event.setItemLabelGenerator(Event::getName);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, timePlan)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
    }

    public void setTimePlan(TimePlan timePlan) {
        this.timePlan = timePlan;
        binder.readBean(timePlan);
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(timePlan);
                fireEvent(new SaveEvent(this, timePlan));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class TimePlanFormEvent extends ComponentEvent<TimePlanForm> {
        private TimePlan timePlan;

        protected TimePlanFormEvent(TimePlanForm source, TimePlan timePlan) {
            super(source, false);
            this.timePlan = timePlan;
        }

        public TimePlan getTimePlan() {
            return timePlan;
        }
    }

    public static class SaveEvent extends TimePlanFormEvent {
        SaveEvent(TimePlanForm source, TimePlan timePlan) {
            super(source, timePlan);
        }
    }

    public static class DeleteEvent extends TimePlanFormEvent {
        DeleteEvent(TimePlanForm source, TimePlan timePlan) {
            super(source, timePlan);
        }
    }

    public static class CloseEvent extends TimePlanFormEvent {
        CloseEvent(TimePlanForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
