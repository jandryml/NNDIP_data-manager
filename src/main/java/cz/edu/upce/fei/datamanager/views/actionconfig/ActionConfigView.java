package cz.edu.upce.fei.datamanager.views.actionconfig;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.data.entity.ThresholdAction;
import cz.edu.upce.fei.datamanager.data.service.ThresholdActionService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

import static cz.edu.upce.fei.datamanager.views.actionconfig.ActionConfigForm.*;

@PageTitle("Action config")
@Route(value = "actions", layout = MainLayout.class)
@PermitAll
public class ActionConfigView extends VerticalLayout {
    Grid<ThresholdAction> grid = new Grid<>(ThresholdAction.class);
    TextField filterText = new TextField();
    ActionConfigForm form;
    private ThresholdActionService thresholdActionService;

    public ActionConfigView(ThresholdActionService thresholdActionService) {
        this.thresholdActionService = thresholdActionService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new ActionConfigForm();
        form.setWidth("25em");
        form.addListener(SaveEvent.class, this::saveThresholdAction);
        form.addListener(DeleteEvent.class, this::deleteThresholdAction);
        form.addListener(CloseEvent.class, e -> closeEditor());
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

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "pin", "value");
        grid.addColumn(contact -> contact.getRegisterType().name()).setHeader("Register type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editThresholdAction(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add action");
        addContactButton.addClickListener(click -> addThresholdAction());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addThresholdAction() {
        grid.asSingleSelect().clear();
        editThresholdAction(new ThresholdAction());
    }

    public void editThresholdAction(ThresholdAction action) {
        if (action == null) {
            closeEditor();
        } else {
            form.setThresholdAction(action);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setThresholdAction(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(thresholdActionService.findAllThresholdActions(filterText.getValue()));
    }
}
