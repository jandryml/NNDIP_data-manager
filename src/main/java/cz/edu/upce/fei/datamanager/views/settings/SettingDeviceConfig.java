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
import cz.edu.upce.fei.datamanager.data.entity.enums.OutputType;

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


    /**
     * Creates a new SettingDeviceConfig.
     */
    public SettingDeviceConfig() {
        // You can initialise any data required for the connected UI components here.
        configureComboBox(acStatusType);
        configureComboBox(acModeType);
        configureComboBox(acFanType);
        configureComboBox(recuperationType);

        saveDeviceConfig.addClickListener(event ->{
                Notification.show("Devices address config saved!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                // TODO add functionality
        });
    }


    private void configureComboBox(ComboBox<OutputType> comboBox) {
        comboBox.setItems(OutputType.values());
        comboBox.setItemLabelGenerator(OutputType::name);
        comboBox.setAllowCustomValue(false);
    }

}
