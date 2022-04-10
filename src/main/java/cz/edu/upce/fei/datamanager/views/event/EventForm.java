package cz.edu.upce.fei.datamanager.views.event;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.Event;
import cz.edu.upce.fei.datamanager.views.generic.DynamicActionComponent;

/**
 * A Designer generated component for the event-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("event-form")
@JsModule("./src/views/event/event-form.ts")
public class EventForm extends LitTemplate {
    private Event actualEvent;

    @Id("name")
    private TextField name;
    @Id("dynamicActionComponent")
    private DynamicActionComponent dynamicActionComponent;
    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    Binder<Event> binder = new BeanValidationBinder<>(Event.class);

    /**
     * Creates a new EventForm.
     */
    public EventForm() {
        configureButtons();
        configureBinder();
    }


    private void configureBinder() {
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void configureButtons() {
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, actualEvent)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
    }

    public void setActualEvent(Event actualEvent) {
        this.actualEvent = actualEvent;
        binder.readBean(actualEvent);
        if (actualEvent != null) {
            dynamicActionComponent.setSelectedActions(actualEvent.getActionList());
        }
    }

    private void validateAndSave() {
        try {
            actualEvent.setActionList(dynamicActionComponent.getSelectedActions());
            if (binder.isValid()) {
                binder.writeBean(actualEvent);
                fireEvent(new SaveEvent(this, actualEvent));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class EventFormEvent extends ComponentEvent<EventForm> {
        private Event event;

        protected EventFormEvent(EventForm source, Event event) {
            super(source, false);
            this.event = event;
        }

        public Event getEvent() {
            return event;
        }
    }

    public static class SaveEvent extends EventFormEvent {
        SaveEvent(EventForm source, Event event) {
            super(source, event);
        }
    }

    public static class DeleteEvent extends EventFormEvent {
        DeleteEvent(EventForm source, Event event) {
            super(source, event);
        }
    }

    public static class CloseEvent extends EventFormEvent {
        CloseEvent(EventForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
