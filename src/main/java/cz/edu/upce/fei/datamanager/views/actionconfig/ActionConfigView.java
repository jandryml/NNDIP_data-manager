package cz.edu.upce.fei.datamanager.views.actionconfig;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;
import cz.edu.upce.fei.datamanager.data.service.ThresholdActionService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

/**
 * A Designer generated component for the action-config-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */

@PageTitle("Action config")
@Route(value = "actions", layout = MainLayout.class)
@PermitAll
@Tag("action-config-view")
@JsModule("./src/views/actionconfig/action-config-view.ts")
public class ActionConfigView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addActionButton")
    private Button addContactButton;
    @Id("grid")
    private Grid<ThresholdAction> grid;
    @Id("contactForm")
    private ActionConfigForm contactForm;

    private ThresholdAction currentThresholdAction;
    private BeanValidationBinder<ThresholdAction> binder;

    private final ThresholdActionService thresholdActionService;

    /**
     * Creates a new ActionConfigView.
     */
    public ActionConfigView(ThresholdActionService thresholdActionService) {
        this.thresholdActionService = thresholdActionService;

        grid.addColumn(ThresholdAction::getName).setHeader("First name");
        grid.addColumn(ThresholdAction::getAddress).setHeader("Address");
        grid.addColumn(ThresholdAction::getValue).setHeader("Email");
        grid.addColumn(ThresholdAction::getOutputType).setHeader("Output type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
    }

    private void configureBinding() {
        grid.asSingleSelect().addValueChangeListener(event -> {
            ThresholdAction thresholdAction = event.getValue();
            if (thresholdAction != null) {
                populateForm(thresholdAction);
            } else {
                clearForm();
            }
        });

        binder = new BeanValidationBinder<>(ThresholdAction.class);
        binder.bindInstanceFields(contactForm);

        contactForm.getDelete().addClickListener(e -> {
            if (this.currentThresholdAction != null) {
                thresholdActionService.deleteThresholdAction(this.currentThresholdAction);
                updateList();
                clearForm();
                Notification.show("Action deleted.");
            }
        });

        contactForm.getClose().addClickListener(e -> {
            clearForm();
        });

        contactForm.getSave().addClickListener(e -> {
            try {
                if (this.currentThresholdAction == null) {
                    this.currentThresholdAction = new ThresholdAction();
                }
                binder.writeBean(this.currentThresholdAction);
                thresholdActionService.saveThresholdAction(this.currentThresholdAction);
                updateList();
                clearForm();
                Notification.show("Contact details stored.");
            } catch (ValidationException validationException) {
                Notification.show("Please enter a valid contact details.");
            }
        });
    }

    void clearForm() {
        populateForm(null);
    }

    void populateForm(ThresholdAction thresholdAction) {
        this.currentThresholdAction = thresholdAction;
        binder.readBean(this.currentThresholdAction);
    }

    private void updateList() {
        String filterValue = filterText.getValue();
        if (filterValue == null || filterValue.isBlank()) {
            grid.setItems(thresholdActionService.findAllThresholdActions());
        } else {
            grid.setItems(thresholdActionService.findAllThresholdActions(filterValue));
        }
    }
}
