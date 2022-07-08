package cz.edu.upce.fei.datamanager.views.dashboard;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UIDetachedException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.charts.model.style.Style;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.data.provider.Query;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * A Designer generated component for the dashboard-graph template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@Tag("dashboard-graph")
@JsModule("./src/views/dashboard/dashboard-graph.ts")
public class DashboardGraph extends LitTemplate {
    @Id("sensorComboBox")
    private ComboBox<Sensor> sensorComboBox;
    @Id("measuredValueComboBox")
    private ComboBox<MeasuredValueType> measuredValueComboBox;
    @Id("selectedDate")
    private DatePicker selectedDate;
    @Id("decrementDate")
    private Button decrementDate;
    @Id("incrementDate")
    private Button incrementDate;
    @Id("graphComponent")
    private Chart graphComponent;

    private final SensorService sensorService;
    private final DashboardService dashboardService;
    private final Style style;

    /**
     * Creates a new DashboardGraph.
     */
    public DashboardGraph(SensorService sensorService, DashboardService dashboardService) {
        this.sensorService = sensorService;
        this.dashboardService = dashboardService;

        style = new Style();
        style.setColor(SolidColor.WHITE);

        configComboBox();
        configDateComponents();
        configGraph();
        updateGraphData();
    }

    private void configComboBox() {
        sensorComboBox.setItems(sensorService.findAllSensors());
        sensorComboBox.setItemLabelGenerator(Sensor::getName);
        if (sensorComboBox.getDataProvider() != null) {
            Optional<Sensor> comboValues = sensorComboBox.getDataProvider().fetch(new Query<>()).findFirst();
            comboValues.ifPresent(sensor -> sensorComboBox.setValue(sensor));
        }
        sensorComboBox.addValueChangeListener(event -> updateGraphData());

        measuredValueComboBox.setItems(MeasuredValueType.values());
        measuredValueComboBox.setItemLabelGenerator(MeasuredValueType::name);
        if (measuredValueComboBox.getDataProvider() != null) {
            Optional<MeasuredValueType> comboValues = measuredValueComboBox.getDataProvider().fetch(new Query<>()).findFirst();
            comboValues.ifPresent(measuredValueType -> measuredValueComboBox.setValue(measuredValueType));
        }
        measuredValueComboBox.addValueChangeListener(event -> updateGraphData());
    }

    private void configDateComponents() {
        selectedDate.setValue(LocalDate.now());
        selectedDate.setLocale(Locale.forLanguageTag("cs_CZ"));

        selectedDate.addValueChangeListener(event -> updateGraphData());

        decrementDate.addClickListener(event -> {
            selectedDate.setValue(selectedDate.getValue().minusDays(1));
            updateGraphData();
        });

        incrementDate.addClickListener(event -> {
            selectedDate.setValue(selectedDate.getValue().plusDays(1));
            updateGraphData();
        });
    }

    private void configGraph() {
        Configuration configuration = graphComponent.getConfiguration();

        configuration.getChart().setBackgroundColor(new SolidColor(255, 255, 255, 0));

        configuration.getChart().setType(ChartType.SPLINE);
        configuration.getxAxis().setType(AxisType.DATETIME);

        configuration.getxAxis().getTitle().setStyle(style);
        configuration.getxAxis().getLabels().setStyle(style);

        configuration.getLegend().setEnabled(false);
        configuration.getTooltip().setXDateFormat("%H:%M");

        graphComponent.setConfiguration(configuration);
        graphComponent.drawChart();
    }

    @Scheduled(fixedRateString = "${dashboard.refreshRate.milis:15000}")
    public void refreshData() {
        try {
            getUI().ifPresent(ui -> ui.access(() ->
                    {
                        updateGraphData();
                        ui.push();
                    }
            ));
        } catch (UIDetachedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateGraphData() {
        List<SensorData> dataList = dashboardService.getSensorData(sensorComboBox.getValue(), measuredValueComboBox.getValue(), selectedDate.getValue());
        setGraphData(dataList, measuredValueComboBox.getValue());
        log.info("Updating view");
    }

    private void setGraphData(List<SensorData> dataList, MeasuredValueType valueType) {
        Configuration configuration = graphComponent.getConfiguration();

        configuration.setTitle(dataList.isEmpty() ? "No data present" : "Measured " + valueType.getName());
        configuration.getTitle().setStyle(style);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle(valueType.getName() + " [ " + valueType.getUnits() + "]"));
        yAxis.getTitle().setStyle(style);
        yAxis.getLabels().setStyle(style);
        yAxis.setGridLineColor(SolidColor.GRAY);

        DataSeries dataSeries = new DataSeries();

        dataList.forEach(sensorData -> {
            DataSeriesItem item = new DataSeriesItem(sensorData.getTimestamp(), extractData(sensorData, valueType));
            dataSeries.add(item);
        });


        PlotOptionsLine options = new PlotOptionsLine();
        options.setColor(SolidColor.YELLOW);
        dataSeries.setPlotOptions(options);

        configuration.setSeries(dataSeries);

        graphComponent.setConfiguration(configuration);
        graphComponent.drawChart();
    }

    private Number extractData(SensorData data, MeasuredValueType valueType) {
        return switch (valueType) {
            case TEMPERATURE -> data.getTemperature();
            case HUMIDITY -> data.getHumidity();
            case CO2 -> data.getCo2();
        };
    }
}
