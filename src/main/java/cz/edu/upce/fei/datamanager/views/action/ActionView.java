package cz.edu.upce.fei.datamanager.views.action;

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
import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.data.service.ActionService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

import static cz.edu.upce.fei.datamanager.views.action.ActionForm.*;

/**
 * A Designer generated component for the action-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */

@PageTitle("Action config")
@Route(value = "actions", layout = MainLayout.class)
@PermitAll
@Tag("action-view")
@JsModule("./src/views/action/action-view.ts")
public class ActionView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addActionButton")
    private Button addContactButton;
    @Id("grid")
    private Grid<Action> grid;
    @Id("contactForm")
    private ActionForm contactForm;

    private final ActionService actionService;

    /**
     * Creates a new ActionView.
     */
    public ActionView(ActionService actionService) {
        this.actionService = actionService;

        configureGrid();
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
        closeEditor();
    }

    private void configureGrid() {
        grid.addColumn(Action::getName).setHeader("First name");
        grid.addColumn(Action::getAddress).setHeader("Address");
        grid.addColumn(Action::getValue).setHeader("Email");
        grid.addColumn(Action::getOutputType).setHeader("Output type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editAction(event.getValue()));
    }

    private void configureBinding() {
        addContactButton.addClickListener(click -> addAction());

        contactForm.addListener(SaveEvent.class, this::saveAction);
        contactForm.addListener(DeleteEvent.class, this::deleteAction);
        contactForm.addListener(CloseEvent.class, e -> closeEditor());
    }

    private void addAction() {
        grid.asSingleSelect().clear();
        editAction(new Action());
    }

    private void saveAction(SaveEvent event) {
        actionService.saveAction(event.getAction());
        updateList();
        closeEditor();
    }

    private void deleteAction(DeleteEvent event) {
        actionService.deleteAction(event.getAction());
        updateList();
        closeEditor();
    }

    public void editAction(Action action) {
        if (action == null) {
            closeEditor();
        } else {
            contactForm.setAction(action);
            contactForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        contactForm.setAction(null);
        contactForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(actionService.findAllActions(filterText.getValue()));
    }
}
