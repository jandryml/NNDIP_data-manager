package cz.edu.upce.fei.datamanager.views.sensor;

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
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import static cz.edu.upce.fei.datamanager.views.sensor.SensorForm.*;

/**
 * A Designer generated component for the sensor-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Sensors")
@Route(value = "sensors", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("sensor-view")
@JsModule("./src/views/sensor/sensor-view.ts")
public class SensorView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addSensorButton")
    private Button addSensorButton;
    @Id("grid")
    private Grid<Sensor> grid;
    @Id("sensorForm")
    private SensorForm sensorForm;

    private final SensorService sensorService;

    /**
     * Creates a new SensorView.
     */
    public SensorView(SensorService sensorService) {
        this.sensorService = sensorService;

        configureGrid();
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
        closeEditor();
    }

    private void configureGrid() {
        grid.addColumn(Sensor::getId).setHeader("ID");
        grid.addColumn(Sensor::getName).setHeader("Name");
        grid.addColumn(Sensor::getSensorType).setHeader("Sensor type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editSensor(event.getValue()));
    }

    private void configureBinding() {
        addSensorButton.addClickListener(click -> addSensor());

        sensorForm.addListener(SaveEvent.class, this::saveSensor);
        sensorForm.addListener(DeleteEvent.class, this::deleteSensor);
        sensorForm.addListener(CloseEvent.class, e -> closeEditor());
    }

    private void addSensor() {
        grid.asSingleSelect().clear();
        editSensor(new Sensor());
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

    public void editSensor(Sensor sensor) {
        if (sensor == null) {
            closeEditor();
        } else {
            sensorForm.setSensor(sensor);
            sensorForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        sensorForm.setSensor(null);
        sensorForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(sensorService.findByName(filterText.getValue()));
    }
}
