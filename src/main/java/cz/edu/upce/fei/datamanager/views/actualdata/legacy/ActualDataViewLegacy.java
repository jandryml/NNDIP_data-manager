package cz.edu.upce.fei.datamanager.views.actualdata.legacy;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.service.SensorDataService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

@PageTitle("Actual Data")
@Route(value = "actual-data", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class ActualDataViewLegacy extends VerticalLayout {

    private final SensorDataService sensorDataService;

    private final TextField hitsTextField = new TextField();
    private final TextField timestampTextField = new TextField();
    private final TextField temperature1TextField = new TextField();
    private final TextField humidityTextField = new TextField();
    private final TextField co2_1TextField = new TextField();
    private final TextField co2_2TextField = new TextField();
    private final TextField temperature2TextField = new TextField();

    public ActualDataViewLegacy(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;

        setMargin(true);

        initLabel("Hits", hitsTextField);
        initLabel("Timestamp", timestampTextField);
        initLabel("Temperature 1", temperature1TextField);
        initLabel("Humidity", humidityTextField);
        initLabel("Co2 1", co2_1TextField);
        initLabel("Co2 2", co2_2TextField);
        initLabel("Temperature 2", temperature2TextField);

        updateValues();
    }

    private void initLabel(String name, TextField textField) {
        textField.setReadOnly(true);
        textField.setLabel(name);
        add(textField);
    }

    public void updateValues() {
        SensorData sensorData = sensorDataService.getLatestData(1);
        hitsTextField.setValue(String.valueOf(sensorData.getHits()));
        timestampTextField.setValue(sensorData.getTimestamp().toLocalDateTime().toString());
        temperature1TextField.setValue(String.valueOf(sensorData.getTemperature()));
        humidityTextField.setValue(String.valueOf(sensorData.getHumidity()));
        co2_1TextField.setValue(String.valueOf(sensorData.getCo2()));
    }
}