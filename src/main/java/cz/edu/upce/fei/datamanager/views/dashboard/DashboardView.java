package cz.edu.upce.fei.datamanager.views.dashboard;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UIDetachedException;
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
import cz.edu.upce.fei.datamanager.data.entity.AddressState;
import cz.edu.upce.fei.datamanager.data.entity.AddressStateId;
import cz.edu.upce.fei.datamanager.data.entity.ControlledDeviceAddressConfig;
import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;
import cz.edu.upce.fei.datamanager.data.service.AddressStateService;
import cz.edu.upce.fei.datamanager.data.service.ControlledDeviceStatusService;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import cz.edu.upce.fei.datamanager.data.service.plan.LimitPlanService;
import cz.edu.upce.fei.datamanager.exception.AddressStateNotHandledException;
import cz.edu.upce.fei.datamanager.views.MainLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

/**
 * A Designer generated component for the dashboard-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("dashboard-view")
@JsModule("./src/views/dashboard/dashboard-view.ts")
public class DashboardView extends LitTemplate {

    @Id("activeTimePlan")
    private TextField activeTimePlan;

    @Id("temperatureContainer")
    private VerticalLayout temperatureContainer;
    @Id("humidityContainer")
    private VerticalLayout humidityContainer;
    @Id("co2Container")
    private VerticalLayout co2Container;
    @Id("graphComponent")
    private DashboardGraph graphComponent;

    @Id("acStatus")
    private TextField acStatus;
    @Id("recuperationStatus")
    private TextField recuperationStatus;
    @Id("ventStatus")
    private TextField ventStatus;

    private final DashboardService dashboardService;
    private final AddressStateService addressStateService;
    private final ControlledDeviceStatusService controlledDeviceStatusService;
    private final LimitPlanService limitPlanService;


    /**
     * Creates a new DashboardView.
     */
    public DashboardView(DashboardService dashboardService, AddressStateService addressStateService, LimitPlanService limitPlanService, ControlledDeviceStatusService controlledDeviceStatusService) {
        this.dashboardService = dashboardService;
        this.addressStateService = addressStateService;
        this.controlledDeviceStatusService = controlledDeviceStatusService;
        this.limitPlanService = limitPlanService;

        updateSensorData();
        updateControlledDeviceStatus();
    }

    @Scheduled(fixedRateString = "${dashboard.refreshRate.milis:15000}")
    public void refreshData() {
        try {
            getUI().ifPresent(ui -> ui.access(() ->
                    {
                        updateControlledDeviceStatus();
                        updateSensorData();
                        ui.push();
                    }
            ));
        } catch (UIDetachedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateSensorData() {
        activeTimePlan.setValue(limitPlanService.getActiveYearPeriod().getPrettyName());

        temperatureContainer.removeAll();
        updateSensorDataLayout(dashboardService.getViewableTemperatureData(), temperatureContainer);
        humidityContainer.removeAll();
        updateSensorDataLayout(dashboardService.getViewableHumidityData(), humidityContainer);
        co2Container.removeAll();
        updateSensorDataLayout(dashboardService.getViewableCo2Data(), co2Container);
    }

    private void updateSensorDataLayout(List<DashboardSensorDataDto> data, VerticalLayout layout) {
        data.forEach(it -> {
            TextField textField = new TextField();
            textField.setLabel(it.name());
            textField.setValue(it.value());
            textField.setReadOnly(true);
            layout.add(textField);
        });
    }

    private void updateControlledDeviceStatus() {
        acStatus.setValue(resolveAcStatus());
        recuperationStatus.setValue(resolveRecuperationStatus());
        ventStatus.setValue(resolveVentStatus());
    }

    private String resolveVentStatus() {
        String result;
        try {
            int status = getValueForDeviceType(ControlledDeviceType.AC_STATUS);

            if (status == 2) {
                int mode = getValueForDeviceType(ControlledDeviceType.AC_FAN);

                switch (mode) {
                    case 2 -> result = "\uD83D\uDFE2 - 1️⃣"; // quiet
                    case 3 -> result = "\uD83D\uDFE2 - 2️⃣"; // low
                    case 4 -> result = "\uD83D\uDFE2 - 3️⃣"; // medium
                    case 5 -> result = "\uD83D\uDFE2 - 4️⃣"; // high
                    default -> result = "\uD83D\uDFE2 - \uD83E\uDD37\u200D♂️"; // running but unknown? - auto might
                }
            } else {
                result = "\uD83D\uDD34";
            }
        } catch (AddressStateNotHandledException e) {
            result = "Unavailable";
        }
        return result;
    }

    private String resolveRecuperationStatus() {
        String result;
        try {
            int status = getValueForDeviceType(ControlledDeviceType.RECUPERATION);
            if (status == 0) {
                result = "\uD83D\uDD34";
            } else {
                result = "\uD83D\uDFE2";
            }
        } catch (AddressStateNotHandledException e) {
            result = "Unavailable";
        }
        return result;
    }

    private String resolveAcStatus() {
        String result;
        try {
            int status = getValueForDeviceType(ControlledDeviceType.AC_STATUS);

            if (status == 1) {
                result = "\uD83D\uDD34";
            } else {
                int mode = getValueForDeviceType(ControlledDeviceType.AC_MODE);

                switch (mode) {
                    case 1 -> result = "\uD83D\uDFE2 - \uD83E\uDD16"; // auto
                    case 2 -> result = "\uD83D\uDFE2 - ❄️"; // cool
                    case 3 -> result = "\uD83D\uDFE2 - \uD83C\uDFDC"; // dry
                    case 4 -> result = "\uD83D\uDFE2 - \uD83D\uDD25"; // heat
                    case 5 -> result = "\uD83D\uDFE2 - \uD83D\uDCA8️"; // fan
                    default -> result = "\uD83D\uDFE2 - \uD83E\uDD37\u200D♂️";
                }
            }
        } catch (AddressStateNotHandledException e) {
            result = "Unavailable";
        }
        return result;
    }

    private int getValueForDeviceType(ControlledDeviceType deviceType) throws AddressStateNotHandledException {
        Optional<ControlledDeviceAddressConfig> optional = controlledDeviceStatusService.findByAddressConfig(deviceType);

        if (optional.isPresent()) {
            ControlledDeviceAddressConfig addressConfig = optional.get();
            Optional<AddressState> optionalAddressState
                    = addressStateService.getById(new AddressStateId(addressConfig.getOutputType(), addressConfig.getAddress()));
            if (optionalAddressState.isPresent()) {
                AddressState addressState = optionalAddressState.get();
                return Integer.parseInt(addressState.getValue());
            } else {
                throw new AddressStateNotHandledException(deviceType.getPrettyName() + " - address state is not handled!");
            }
        } else {
            throw new AddressStateNotHandledException(deviceType.getPrettyName() + " - address state is not configured!");
        }
    }
}
