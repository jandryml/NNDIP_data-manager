package cz.edu.upce.fei.datamanager.views.actionconfig;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.RegisterType;
import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;

import java.util.Objects;

public class ActionConfigForm extends FormLayout {
    private ThresholdAction thresholdAction;

    TextField name = new TextField("Name");
    // TODO switch to NumberField
    TextField pin = new TextField("Pin");
    TextField value = new TextField("Value");
    ComboBox<RegisterType> registerType = new ComboBox<>("Register type");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<ThresholdAction> binder = new BeanValidationBinder<>(ThresholdAction.class);

    public ActionConfigForm() {
        addClassName("contact-form");

        ///TODO refactor - split to methods
        name.setAutocomplete(Autocomplete.OFF);
        name.setRequired(true);

        binder.forField(name)
                .withValidator(s -> !s.isEmpty(), "Required!")
                .bind(ThresholdAction::getName, ThresholdAction::setName);

        pin.setPattern("^\\d+$");
        pin.setRequired(true);
        pin.setAutocomplete(Autocomplete.OFF);

        binder.forField(pin)
                .withValidator(s -> !s.isEmpty() && s.matches("^\\d+$"), "Required, has to be integer!")
                .bind(ThresholdAction::getPin, ThresholdAction::setPin);

        value.setPattern("^\\d+$");
        value.setRequired(true);
        value.setAutocomplete(Autocomplete.OFF);

        binder.forField(value)
                .withValidator(s -> !s.isEmpty() && s.matches("^\\d+$"), "Required, has to be integer!")
                .bind(ThresholdAction::getValue, ThresholdAction::setValue);

        registerType.setAllowCustomValue(false);
        registerType.setRequired(true);
        registerType.setItems(RegisterType.values());
        registerType.setItemLabelGenerator(RegisterType::name);

        // TODO better validator -> for Boolean values allow only 1 or 0
        binder.forField(registerType)
                .withValidator(Objects::nonNull, "Required!")
                .bind(ThresholdAction::getRegisterType, ThresholdAction::setRegisterType);

        binder.bindInstanceFields(this);

        add(name, pin, value, registerType, createButtonsLayout());
    }

    public void setThresholdAction(ThresholdAction thresholdAction) {
        this.thresholdAction = thresholdAction;
        binder.readBean(thresholdAction);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, thresholdAction)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(thresholdAction);
                fireEvent(new SaveEvent(this, thresholdAction));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ThresholdActionFormEvent extends ComponentEvent<ActionConfigForm> {
        private ThresholdAction thresholdAction;

        protected ThresholdActionFormEvent(ActionConfigForm source, ThresholdAction thresholdAction) {
            super(source, false);
            this.thresholdAction = thresholdAction;
        }

        public ThresholdAction getThresholdAction() {
            return thresholdAction;
        }
    }

    public static class SaveEvent extends ThresholdActionFormEvent {
        SaveEvent(ActionConfigForm source, ThresholdAction thresholdAction) {
            super(source, thresholdAction);
        }
    }

    public static class DeleteEvent extends ThresholdActionFormEvent {
        DeleteEvent(ActionConfigForm source, ThresholdAction thresholdAction) {
            super(source, thresholdAction);
        }

    }

    public static class CloseEvent extends ThresholdActionFormEvent {
        CloseEvent(ActionConfigForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
