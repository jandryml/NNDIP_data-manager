package cz.edu.upce.fei.datamanager.views.settings;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * A Designer generated component for the settings-sensors-config template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@Tag("settings-sensors-config")
@JsModule("./src/views/settings/settings-sensors-config.ts")
public class SettingsSensorsConfig extends LitTemplate {

    private final SensorService sensorService;
    private final DashboardService dashboardService;
    @Id("temperatureSensorGrid")
    private Grid<Sensor> temperatureSensorGrid;
    @Id("humiditySensorGrid")
    private Grid<Sensor> humiditySensorGrid;
    @Id("co2SensorGrid")
    private Grid<Sensor> co2SensorGrid;

    /**
     * Creates a new SettingsView.
     */
    public SettingsSensorsConfig(SensorService sensorService, DashboardService dashboardService) {
        this.sensorService = sensorService;
        this.dashboardService = dashboardService;

        // You can initialise any data required for the connected UI components here.
        initGrid(temperatureSensorGrid, MeasuredValueType.TEMPERATURE);
        initGrid(humiditySensorGrid, MeasuredValueType.HUMIDITY);
        initGrid(co2SensorGrid, MeasuredValueType.CO2);
    }

    private void initGrid(Grid<Sensor> grid, MeasuredValueType measuredValueType) {
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.setItems(sensorService.findBySensorTypes(List.of(measuredValueType.getSensorType(), SensorType.MIXED)));

        grid.asMultiSelect().select(dashboardService.getSensorsViewableInDashboard(measuredValueType));
        grid.addColumn(Sensor::getName).setHeader("Name");

        grid.addSelectionListener(selectionEvent -> dashboardService.setSensorsViewableInDashboard(
                measuredValueType,
                List.copyOf(grid.asMultiSelect().getSelectedItems())
        ));
    }
}
