package cz.edu.upce.fei.datamanager.views.action;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.data.entity.OutputType;

/**
 * A Designer generated component for the action-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("action-form")
@JsModule("./src/views/action/action-form.ts")
public class ActionForm extends LitTemplate {
    private Action action;

    @Id("name")
    private TextField name;
    @Id("address")
    private TextField address;
    @Id("value")
    private TextField value;
    @Id("outputType")
    private ComboBox<OutputType> outputType;
    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    Binder<Action> binder = new BeanValidationBinder<>(Action.class);

    /**
     * Creates a new ActionForm.
     */
    public ActionForm() {
        configureFields();
        configureButtons();
        configureBinder();
    }

    private void configureFields() {
        outputType.setItems(OutputType.values());
        outputType.setItemLabelGenerator(OutputType::name);
    }

    private void configureBinder() {
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void configureButtons() {
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, action)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
    }

    public void setAction(Action action) {
        this.action = action;
        binder.readBean(action);
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(action);
                fireEvent(new ActionForm.SaveEvent(this, action));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ActionFormEvent extends ComponentEvent<ActionForm> {
        private Action action;

        protected ActionFormEvent(ActionForm source, Action action) {
            super(source, false);
            this.action = action;
        }

        public Action getAction() {
            return action;
        }
    }

    public static class SaveEvent extends ActionFormEvent {
        SaveEvent(ActionForm source, Action action) {
            super(source, action);
        }
    }

    public static class DeleteEvent extends ActionFormEvent {
        DeleteEvent(ActionForm source, Action action) {
            super(source, action);
        }
    }

    public static class CloseEvent extends ActionFormEvent {
        CloseEvent(ActionForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
