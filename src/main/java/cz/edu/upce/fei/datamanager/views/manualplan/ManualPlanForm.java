package cz.edu.upce.fei.datamanager.views.manualplan;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.plan.ManualPlan;
import cz.edu.upce.fei.datamanager.views.generic.DynamicActionComponent;

/**
 * A Designer generated component for the manual-plan-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("manual-plan-form")
@JsModule("./src/views/manualplan/manual-plan-form.ts")
public class ManualPlanForm extends LitTemplate {
    private ManualPlan manualPlan;

    @Id("name")
    private TextField name;
    @Id("enabled")
    private Checkbox enabled;
    @Id("dynamicActionComponent")
    private DynamicActionComponent dynamicActionComponent;
    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    Binder<ManualPlan> binder = new BeanValidationBinder<>(ManualPlan.class);

    /**
     * Creates a new ManualPlanForm.
     */
    public ManualPlanForm() {
        configureButtons();
        configureBinder();
    }


    private void configureBinder() {
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void configureButtons() {
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, manualPlan)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
    }

    public void setManualPlan(ManualPlan manualPlan) {
        this.manualPlan = manualPlan;
        binder.readBean(manualPlan);
        if(manualPlan != null) {
            dynamicActionComponent.setSelectedActions(manualPlan.getActionList());
        }
    }

    private void validateAndSave() {
        try {
            manualPlan.setActionList(dynamicActionComponent.getSelectedActions());
            if (binder.isValid()) {
                binder.writeBean(manualPlan);
                fireEvent(new SaveEvent(this, manualPlan));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ManualPlanFormEvent extends ComponentEvent<ManualPlanForm> {
        private ManualPlan manualPlan;

        protected ManualPlanFormEvent(ManualPlanForm source, ManualPlan manualPlan) {
            super(source, false);
            this.manualPlan = manualPlan;
        }

        public ManualPlan getManualPlan() {
            return manualPlan;
        }
    }

    public static class SaveEvent extends ManualPlanFormEvent {
        SaveEvent(ManualPlanForm source, ManualPlan manualPlan) {
            super(source, manualPlan);
        }
    }

    public static class DeleteEvent extends ManualPlanFormEvent {
        DeleteEvent(ManualPlanForm source, ManualPlan manualPlan) {
            super(source, manualPlan);
        }
    }

    public static class CloseEvent extends ManualPlanFormEvent {
        CloseEvent(ManualPlanForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
