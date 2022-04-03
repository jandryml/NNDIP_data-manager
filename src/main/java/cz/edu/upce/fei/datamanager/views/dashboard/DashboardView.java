package cz.edu.upce.fei.datamanager.views.dashboard;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import java.util.List;

/**
 * A Designer generated component for the dashboard-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("dashboard-view")
@JsModule("./src/views/dashboard/dashboard-view.ts")
public class DashboardView extends LitTemplate {

    @Id("temperatureContainer")
    private VerticalLayout temperatureContainer;
    @Id("humidityContainer")
    private VerticalLayout humidityContainer;
    @Id("co2Container")
    private VerticalLayout co2Container;
    @Id("sensorComboBox")
    private ComboBox<Sensor> sensorComboBox;
    @Id("measuredValueComboBox")
    private ComboBox<MeasuredValueType> measuredValueComboBox;

    private final DashboardService dashboardService;
    private final SensorService sensorService;

    /**
     * Creates a new DashboardView.
     */
    public DashboardView(DashboardService dashboardService, SensorService sensorService) {
        this.dashboardService = dashboardService;
        this.sensorService = sensorService;
        initSensorStatus();
        initComboBox();
    }

    private void initComboBox() {
        sensorComboBox.setItems(sensorService.findAllSensors());
        sensorComboBox.setItemLabelGenerator(Sensor::getName);

        measuredValueComboBox.setItems(MeasuredValueType.values());
        measuredValueComboBox.setItemLabelGenerator(MeasuredValueType::name);
    }

    private void initSensorStatus() {
        initTextFields(dashboardService.getViewableTemperatureData(), temperatureContainer);
        initTextFields(dashboardService.getViewableHumidityData(), humidityContainer);
        initTextFields(dashboardService.getViewableCo2Data(), co2Container);
    }

    private void initTextFields(List<DashboardSensorDataDto> data, VerticalLayout layout) {
        data.forEach(it -> {
            TextField textField = new TextField();
            textField.setLabel(it.name());
            textField.setValue(it.value());
            textField.setReadOnly(true);
            layout.add(textField);
        });
    }

}
