package cz.edu.upce.fei.datamanager.views.sensor.legacy;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;

public class SensorFormLegacy extends FormLayout {
    private Sensor sensor;

    TextField id = new TextField("ID");
    TextField name = new TextField("Name");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Sensor> binder = new BeanValidationBinder<>(Sensor.class);

    public SensorFormLegacy() {
        addClassName("contact-form");

        id.setReadOnly(true);
        id.setLabel("Sensor id");

        binder.forField(id)
                .bindReadOnly(s -> s.getId().toString());

        name.setAutocomplete(Autocomplete.OFF);
        name.setRequired(true);

        binder.forField(name)
                .withValidator(s -> !s.isEmpty(), "Required!")
                .bind(Sensor::getName, Sensor::setName);

        binder.bindInstanceFields(this);

        add(id, name, createButtonsLayout());
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
        binder.readBean(sensor);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, sensor)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(sensor);
                fireEvent(new SaveEvent(this, sensor));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class SensorFormEvent extends ComponentEvent<SensorFormLegacy> {
        private Sensor sensor;

        protected SensorFormEvent(SensorFormLegacy source, Sensor sensor) {
            super(source, false);
            this.sensor = sensor;
        }

        public Sensor getSensor() {
            return sensor;
        }
    }

    public static class SaveEvent extends SensorFormEvent {
        SaveEvent(SensorFormLegacy source, Sensor sensor) {
            super(source, sensor);
        }
    }

    public static class DeleteEvent extends SensorFormEvent {
        DeleteEvent(SensorFormLegacy source, Sensor sensor) {
            super(source, sensor);
        }

    }

    public static class CloseEvent extends SensorFormEvent {
        CloseEvent(SensorFormLegacy source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
