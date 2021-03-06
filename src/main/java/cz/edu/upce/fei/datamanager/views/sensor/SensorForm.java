package cz.edu.upce.fei.datamanager.views.sensor;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import cz.edu.upce.fei.datamanager.data.service.SensorDataService;

/**
 * A Designer generated component for the sensor-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("sensor-form")
@JsModule("./src/views/sensor/sensor-form.ts")
public class SensorForm extends LitTemplate {
    private Sensor sensor;
    @Id("id")
    private TextField id;
    @Id("name")
    private TextField name;
    @Id("sensorType")
    private ComboBox<SensorType> sensorType;

    @Id("timestampTextField")
    private TextField timestampTextField;
    @Id("hitsTextField")
    private TextField hitsTextField;
    @Id("temperatureTextField")
    private TextField temperatureTextField;
    @Id("humidityTextField")
    private TextField humidityTextField;
    @Id("co2TextField")
    private TextField co2TextField;

    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    Binder<Sensor> binder = new BeanValidationBinder<>(Sensor.class);

    private final SensorDataService sensorDataService;

    /**
     * Creates a new SensorForm.
     */
    public SensorForm(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;

        sensorType.setItems(SensorType.values());
        sensorType.setItemLabelGenerator(SensorType::name);

        configureButtons();
        configureBinder();
    }

    private void loadSensorData() {
        if (sensor.getId() != null) {
            String timestamp;
            String hits;
            String temperature;
            String humidity;
            String co2;

            try {
                SensorData sensorData = sensorDataService.getLatestData(sensor.getId());
                timestamp = sensorData.getTimestamp().toString();
                hits = sensorData.getHits().toString();
                temperature = sensorData.getTemperature() != null ? sensorData.getTemperature().toString() : "Unknown";
                humidity = sensorData.getHumidity() != null ? sensorData.getHumidity().toString() : "Unknown";
                co2 = sensorData.getCo2() != null ? sensorData.getCo2().toString() : "Unknown";

            } catch (NotFoundException e) {
                timestamp = "No data found";
                hits = "No data found";
                temperature = "No data found";
                humidity = "No data found";
                co2 = "No data found";
            }

            timestampTextField.setValue(timestamp);
            hitsTextField.setValue(hits);
            temperatureTextField.setValue(temperature);
            humidityTextField.setValue(humidity);
            co2TextField.setValue(co2);
        }
    }

    private void configureBinder() {
        binder.forField(id)
                .bindReadOnly(s -> s.getId() != null ? s.getId().toString() : "New sensor");

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void configureButtons() {
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, sensor)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
        binder.readBean(sensor);
        if (sensor != null) {
            loadSensorData();
        }
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(sensor);
                fireEvent(new SaveEvent(this, sensor));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class SensorFormEvent extends ComponentEvent<SensorForm> {
        private Sensor sensor;

        protected SensorFormEvent(SensorForm source, Sensor sensor) {
            super(source, false);
            this.sensor = sensor;
        }

        public Sensor getSensor() {
            return sensor;
        }
    }

    public static class SaveEvent extends SensorFormEvent {
        SaveEvent(SensorForm source, Sensor sensor) {
            super(source, sensor);
        }
    }

    public static class DeleteEvent extends SensorFormEvent {
        DeleteEvent(SensorForm source, Sensor sensor) {
            super(source, sensor);
        }
    }

    public static class CloseEvent extends SensorFormEvent {
        CloseEvent(SensorForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}