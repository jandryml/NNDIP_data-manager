package cz.edu.upce.fei.datamanager.views.settings;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import cz.edu.upce.fei.datamanager.views.MainLayout;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * A Designer generated component for the settings-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
@PermitAll
@Tag("settings-view")
@JsModule("./src/views/settings/settings-view.ts")
public class SettingsView extends LitTemplate {

    private final SensorService sensorService;
    private final DashboardService dashboardService;
    @Id("temperatureSensorGrid")
    private Grid<Sensor> temperatureSensorGrid;
    @Id("humiditySensorGrid")
    private Grid<Sensor> humiditySensorGrid;
    @Id("co2SensorGrid")
    private Grid<Sensor> co2SensorGrid;
    @Id("saveSensors")
    private Button saveSensors;

    /**
     * Creates a new SettingsView.
     */
    public SettingsView(SensorService sensorService, DashboardService dashboardService) {
        this.sensorService = sensorService;
        this.dashboardService = dashboardService;

        // You can initialise any data required for the connected UI components here.
        initGrid(temperatureSensorGrid, MeasuredValueType.TEMPERATURE);
        initGrid(humiditySensorGrid, MeasuredValueType.HUMIDITY);
        initGrid(co2SensorGrid, MeasuredValueType.CO2);

        saveSensors.addClickListener(it ->
                Notification.show("Sensors will be viewed in dashboard!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS)
        );
    }

    private void initGrid(Grid<Sensor> grid, MeasuredValueType measuredValueType) {
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.setItems(sensorService.findBySensorTypes(List.of(measuredValueType.getSensorType(), SensorType.MIXED)));

        grid.asMultiSelect().select(dashboardService.getSensorsViewableInDashboard(measuredValueType));
        grid.addColumn(Sensor::getName).setHeader("Name");

        saveSensors.addClickListener(clickEvent -> dashboardService.setSensorsViewableInDashboard(
                measuredValueType,
                List.copyOf(grid.asMultiSelect().getSelectedItems())
        ));
    }
}
