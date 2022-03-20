package cz.edu.upce.fei.datamanager.views.actionconfig;

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
import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;
import cz.edu.upce.fei.datamanager.data.service.ThresholdActionService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

import static cz.edu.upce.fei.datamanager.views.actionconfig.ActionConfigForm.*;

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

    private final ThresholdActionService thresholdActionService;

    /**
     * Creates a new ActionConfigView.
     */
    public ActionConfigView(ThresholdActionService thresholdActionService) {
        this.thresholdActionService = thresholdActionService;

        configureGrid();
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
        closeEditor();
    }

    private void configureGrid() {
        grid.addColumn(ThresholdAction::getName).setHeader("First name");
        grid.addColumn(ThresholdAction::getAddress).setHeader("Address");
        grid.addColumn(ThresholdAction::getValue).setHeader("Email");
        grid.addColumn(ThresholdAction::getOutputType).setHeader("Output type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editThresholdAction(event.getValue()));
    }

    private void configureBinding() {
        addContactButton.addClickListener(click -> addThresholdAction());

        contactForm.addListener(SaveEvent.class, this::saveThresholdAction);
        contactForm.addListener(DeleteEvent.class, this::deleteThresholdAction);
        contactForm.addListener(CloseEvent.class, e -> closeEditor());
    }

    private void addThresholdAction() {
        grid.asSingleSelect().clear();
        editThresholdAction(new ThresholdAction());
    }

    private void saveThresholdAction(SaveEvent event) {
        thresholdActionService.saveThresholdAction(event.getThresholdAction());
        updateList();
        closeEditor();
    }

    private void deleteThresholdAction(DeleteEvent event) {
        thresholdActionService.deleteThresholdAction(event.getThresholdAction());
        updateList();
        closeEditor();
    }

    public void editThresholdAction(ThresholdAction action) {
        if (action == null) {
            closeEditor();
        } else {
            contactForm.setThresholdAction(action);
            contactForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        contactForm.setThresholdAction(null);
        contactForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(thresholdActionService.findAllThresholdActions(filterText.getValue()));
    }
}
