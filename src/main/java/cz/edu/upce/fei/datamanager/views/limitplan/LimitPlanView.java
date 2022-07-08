package cz.edu.upce.fei.datamanager.views.limitplan;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.Event;
import cz.edu.upce.fei.datamanager.data.entity.enums.LimitPlanType;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.LimitPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriodType;
import cz.edu.upce.fei.datamanager.data.service.EventService;
import cz.edu.upce.fei.datamanager.data.service.plan.LimitPlanService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import java.math.BigDecimal;

/**
 * A Designer generated component for the limit-plan-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Limit config")
@Route(value = "limits", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("limit-plan-view")
@JsModule("./src/views/limitplan/limit-plan-view.ts")
public class LimitPlanView extends LitTemplate {

    @Id("optimalTemperature")
    private NumberField optimalTemperature;

    @Id("lowTempThreshold")
    private NumberField lowTempThreshold;
    @Id("lowTempEvent")
    private ComboBox<Event> lowTempEvent;
    @Id("lowTempEnabled")
    private Checkbox lowTempEnabled;

    @Id("highTempThreshold")
    private NumberField highTempThreshold;
    @Id("highTempEvent")
    private ComboBox<Event> highTempEvent;
    @Id("highTempEnabled")
    private Checkbox highTempEnabled;

    @Id("optimalCo2")
    private NumberField optimalCo2;
    @Id("thresholdCo2")
    private NumberField thresholdCo2;
    @Id("eventCo2")
    private ComboBox<Event> eventCo2;
    @Id("enabledCo2")
    private Checkbox enabledCo2;
    @Id("co2Priority")
    private IntegerField co2Priority;

    @Id("saveButton")
    private Button button;

    @Id("seasonLayout")
    private HorizontalLayout seasonLayout;

    private final RadioButtonGroup<YearPeriodType> typeRadioGroup = new RadioButtonGroup<>();

    private final EventService eventService;
    private final LimitPlanService limitPlanService;

    /**
     * Creates a new LimitPlanView.
     */
    public LimitPlanView(EventService eventService, LimitPlanService limitPlanService) {
        this.eventService = eventService;
        this.limitPlanService = limitPlanService;

        configureNumberFields();
        configureComboBoxes();

        button.addClickListener(event -> saveLimitPlans());

        configureRadioGroup();
        YearPeriodType periodType = limitPlanService.getActiveYearPeriod();
        typeRadioGroup.setValue(periodType);
    }

    private void configureComboBoxes() {
        configureComboBox(lowTempEvent);
        configureComboBox(highTempEvent);
        configureComboBox(eventCo2);
    }

    private void configureComboBox(ComboBox<Event> comboBox) {
        comboBox.setItems(eventService.findAllEvents());
        comboBox.setItemLabelGenerator(cz.edu.upce.fei.datamanager.data.entity.Event::getName);
    }

    private void configureNumberFields() {
        configNumberField(optimalTemperature, 0.1);
        configNumberField(lowTempThreshold, 0.1);
        configNumberField(highTempThreshold, 0.1);
        configNumberField(optimalCo2, 25);
        configNumberField(thresholdCo2, 25);
    }

    private void configureRadioGroup() {
        typeRadioGroup.setLabel("Type");
        typeRadioGroup.setItems(YearPeriodType.SUMMER, YearPeriodType.WINTER);
        typeRadioGroup.setItemLabelGenerator(YearPeriodType::getName);
        seasonLayout.add(typeRadioGroup);

        typeRadioGroup.addValueChangeListener(it -> {
            YearPeriodType periodType = it.getValue();
            limitPlanService.setActiveYearPeriod(periodType);
            loadLimitPlans(periodType);
        });
    }

    private void configNumberField(NumberField numberField, double step) {
        numberField.setStep(step);
    }

    private void saveLimitPlans() {
        saveLimitPlan(LimitPlanType.TEMPERATURE_LOW, optimalTemperature.getValue(), lowTempThreshold.getValue(),
                lowTempEnabled.getValue(), lowTempEvent.getValue(), 0);

        saveLimitPlan(LimitPlanType.TEMPERATURE_HIGH, optimalTemperature.getValue(), highTempThreshold.getValue(),
                highTempEnabled.getValue(), highTempEvent.getValue(), 0);

        saveLimitPlan(LimitPlanType.CO2, optimalCo2.getValue(),
                thresholdCo2.getValue(), enabledCo2.getValue(), eventCo2.getValue(), co2Priority.getValue());
    }


        // TODO dont delete, update
    private void saveLimitPlan(LimitPlanType type, Double optimalValue, Double thresholdValue, boolean enabled, Event event, int priority) {
        LimitPlan limitPlan = new LimitPlan(type, optimalValue, thresholdValue);
        limitPlan.setName(type.name());
        limitPlan.setEnabled(enabled);
        limitPlan.setEvent(event);
        limitPlan.setPriority(priority);

        limitPlanService.saveLimitPlan(limitPlan, typeRadioGroup.getValue());
    }

    private void loadLimitPlans(YearPeriodType periodType) {
        LimitPlan lowTempPlan = getLimitPlan(LimitPlanType.TEMPERATURE_LOW, periodType);
        LimitPlan highTempPlan = getLimitPlan(LimitPlanType.TEMPERATURE_HIGH, periodType);
        LimitPlan co2Plan = getLimitPlan(LimitPlanType.CO2, periodType);

        optimalTemperature.setValue(lowTempPlan.getOptimalValue());
        lowTempThreshold.setValue(lowTempPlan.getThresholdValue());
        highTempThreshold.setValue(highTempPlan.getThresholdValue());
        optimalCo2.setValue(co2Plan.getOptimalValue());
        thresholdCo2.setValue(co2Plan.getThresholdValue());
        highTempEvent.setValue(highTempPlan.getEvent());
        lowTempEvent.setValue(lowTempPlan.getEvent());
        eventCo2.setValue(co2Plan.getEvent());
        lowTempEnabled.setValue(lowTempPlan.isEnabled());
        highTempEnabled.setValue(highTempPlan.isEnabled());
        enabledCo2.setValue(co2Plan.isEnabled());
        co2Priority.setValue(co2Plan.getPriority());
    }

    private LimitPlan getLimitPlan(LimitPlanType planType, YearPeriodType periodType) {
        return limitPlanService.findLimitPlanByName(planType.name(), periodType).orElse(new LimitPlan());
    }
}
