package cz.edu.upce.fei.datamanager.views.settings;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import cz.edu.upce.fei.datamanager.data.entity.ControlledDeviceAddressConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;
import cz.edu.upce.fei.datamanager.data.entity.enums.OutputType;
import cz.edu.upce.fei.datamanager.data.service.ControlledDeviceAddressConfigService;

/**
 * A Designer generated component for the setting-device-config template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("setting-device-config")
@JsModule("./src/views/settings/setting-device-config.ts")
public class SettingDeviceConfig extends LitTemplate {

    @Id("acStatusAddress")
    private TextField acStatusAddress;
    @Id("acStatusType")
    private ComboBox<OutputType> acStatusType;
    @Id("acModeAddress")
    private TextField acModeAddress;
    @Id("acModeType")
    private ComboBox<OutputType> acModeType;
    @Id("acFanAddress")
    private TextField acFanAddress;
    @Id("acFanType")
    private ComboBox<OutputType> acFanType;
    @Id("recuperationAddress")
    private TextField recuperationAddress;
    @Id("recuperationType")
    private ComboBox<OutputType> recuperationType;
    @Id("saveDeviceConfig")
    private Button saveDeviceConfig;

    private final ControlledDeviceAddressConfigService configService;

    /**
     * Creates a new SettingDeviceConfig.
     */
    public SettingDeviceConfig(ControlledDeviceAddressConfigService configService) {
        this.configService = configService;

        configureComboBox(acStatusType);
        configureComboBox(acModeType);
        configureComboBox(acFanType);
        configureComboBox(recuperationType);

        saveDeviceConfig.addClickListener(event -> {
            Notification.show("Devices address config saved!")
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            // TODO add functionality
            saveConfigurations();
        });

        loadConfigurations();
    }

    private void saveConfigurations() {
        saveConfiguration(ControlledDeviceType.AC_STATUS, acStatusType.getValue(), acStatusAddress.getValue());
        saveConfiguration(ControlledDeviceType.AC_MODE, acModeType.getValue(), acModeAddress.getValue());
        saveConfiguration(ControlledDeviceType.AC_FAN, acFanType.getValue(), acFanAddress.getValue());
        saveConfiguration(ControlledDeviceType.RECUPERATION, recuperationType.getValue(), recuperationAddress.getValue());
    }

    private void saveConfiguration(ControlledDeviceType deviceType, OutputType outputType, String address) {
        configService.saveAddressConfig(new ControlledDeviceAddressConfig(deviceType, outputType, address));
    }

    private void configureComboBox(ComboBox<OutputType> comboBox) {
        comboBox.setItems(OutputType.values());
        comboBox.setItemLabelGenerator(OutputType::name);
        comboBox.setAllowCustomValue(false);
    }

    private void loadConfigurations() {
        loadConfiguration(ControlledDeviceType.AC_STATUS, acStatusAddress, acStatusType);
        loadConfiguration(ControlledDeviceType.AC_MODE, acModeAddress, acModeType);
        loadConfiguration(ControlledDeviceType.AC_FAN, acFanAddress, acFanType);
        loadConfiguration(ControlledDeviceType.RECUPERATION, recuperationAddress, recuperationType);
    }

    private void loadConfiguration(ControlledDeviceType deviceType, TextField addressField, ComboBox<OutputType> typeComboBox) {
        ControlledDeviceAddressConfig addressConfig = configService.findByAddressConfig(deviceType)
                .orElse(new ControlledDeviceAddressConfig(deviceType, OutputType.MODBUS_VALUE, "0"));

        addressField.setValue(addressConfig.getAddress());
        typeComboBox.setValue(addressConfig.getOutputType());
    }

}
