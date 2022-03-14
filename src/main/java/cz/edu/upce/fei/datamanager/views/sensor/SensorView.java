package cz.edu.upce.fei.datamanager.views.sensor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

import static cz.edu.upce.fei.datamanager.views.sensor.SensorForm.*;


@PageTitle("Sensors")
@Route(value = "sensors", layout = MainLayout.class)
@PermitAll
public class SensorView extends VerticalLayout {
    Grid<Sensor> grid = new Grid<>(Sensor.class);
    TextField filterText = new TextField();
    SensorForm form;

    private final SensorService sensorService;

    public SensorView(SensorService sensorService) {
        this.sensorService = sensorService;
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
        form = new SensorForm();
        form.setWidth("25em");
        form.addListener(SaveEvent.class, this::saveSensor);
        form.addListener(DeleteEvent.class, this::deleteSensor);
        form.addListener(CloseEvent.class, e -> closeEditor());
    }

    private void saveSensor(SaveEvent event) {
        sensorService.saveSensor(event.getSensor());
        updateList();
        closeEditor();
    }

    private void deleteSensor(DeleteEvent event) {
        sensorService.deleteSensor(event.getSensor());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editSensor(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addSensor() {
        grid.asSingleSelect().clear();
        editSensor(new Sensor());
    }

    public void editSensor(Sensor action) {
        if (action == null) {
            closeEditor();
        } else {
            form.setSensor(action);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setSensor(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(sensorService.findAllSensors(filterText.getValue()));
    }
}
