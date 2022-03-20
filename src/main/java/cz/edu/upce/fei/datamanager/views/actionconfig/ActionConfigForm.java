package cz.edu.upce.fei.datamanager.views.actionconfig;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
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
import cz.edu.upce.fei.datamanager.data.entity.OutputType;
import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;

/**
 * A Designer generated component for the action-config-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("action-config-form")
@JsModule("./src/views/actionconfig/action-config-form.ts")
public class ActionConfigForm extends LitTemplate {
    private ThresholdAction thresholdAction;

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

    Binder<ThresholdAction> binder = new BeanValidationBinder<>(ThresholdAction.class);
    /**
     * Creates a new ActionConfigForm.
     */
    public ActionConfigForm() {
        // You can initialise any data required for the connected UI components here.
        outputType.setItems(OutputType.values());
        outputType.setItemLabelGenerator(OutputType::name);



        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, thresholdAction)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    public void setThresholdAction(ThresholdAction thresholdAction) {
        this.thresholdAction = thresholdAction;
        binder.readBean(thresholdAction);
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(thresholdAction);
                fireEvent(new ActionConfigForm.SaveEvent(this, thresholdAction));
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
