package cz.edu.upce.fei.datamanager.views.gpioplan;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import cz.edu.upce.fei.datamanager.data.entity.Event;
import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.*;
import cz.edu.upce.fei.datamanager.data.service.EventService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * A Designer generated component for the gpio-plan-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@Tag("gpio-plan-form")
@JsModule("./src/views/gpioplan/gpio-plan-form.ts")
public class GpioPlanForm extends LitTemplate {

    private GpioPlan gpioPlan;
    private GpioType gpioPlanType;

    @Id("name")
    private TextField name;
    @Id("priority")
    private IntegerField priority;
    @Id("event")
    private ComboBox<Event> event;
    @Id("pin")
    private ComboBox<RaspiPin> pin;
    @Id("defaultState")
    private ComboBox<PinState> defaultState;
    @Id("duration")
    private IntegerField duration;
    @Id("turnedOn")
    private Checkbox turnedOn;
    @Id("lastTriggered")
    private DateTimePicker lastTriggered;
    @Id("typeLayout")
    private HorizontalLayout typeLayout;
    private final RadioButtonGroup<String> typeRadioGroup = new RadioButtonGroup<>();

    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    private Binder binder = new BeanValidationBinder<>(ManualGpioPlan.class);

    private final EventService eventService;


    /**
     * Creates a new GpioPlanForm.
     */
    public GpioPlanForm(EventService eventService) {
        this.eventService = eventService;
        configureComponents();
        configureBinder();
    }

    private void configureBinder() {
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void configureComponents() {
        event.setItems(eventService.findAllEvents());
        event.setItemLabelGenerator(Event::getName);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new GpioPlanForm.DeleteEvent(this, gpioPlan)));
        close.addClickListener(event -> fireEvent(new GpioPlanForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        configureRadioGroup();

        lastTriggered.setEnabled(false);

        pin.setItems(RaspiPin.values());
        pin.setItemLabelGenerator(RaspiPin::getName);

        defaultState.setItems(PinState.values());
        defaultState.setItemLabelGenerator(PinState::name);

        turnedOn.setReadOnly(true);
    }

    private void configureRadioGroup() {
        typeRadioGroup.setLabel("Type");
        typeRadioGroup.setItems(GpioType.MANUAL.name, GpioType.TIME.name);
        typeLayout.add(typeRadioGroup);

        typeRadioGroup.addValueChangeListener(it -> {
            String currentValue = it.getValue();

            if (currentValue.equals("Manual")) {
                duration.setVisible(false);
                lastTriggered.setVisible(false);
                turnedOn.setVisible(true);
                setGpioPlan(new ManualGpioPlan(), true);
            } else if (currentValue.equals("Time")) {
                duration.setVisible(true);
                lastTriggered.setVisible(true);
                turnedOn.setVisible(false);
                setGpioPlan(new TimeGpioPlan(), true);
            } else {
                log.error("Unknown value when selection type radio in Gpio form");
            }
        });
    }


    public void setGpioPlan(GpioPlan gpioPlan, boolean isNew) {
        this.gpioPlan = gpioPlan;

        if (gpioPlan != null) {
            setPlanType(gpioPlan);
            typeRadioGroup.setReadOnly(!isNew);
        }
        binder.readBean(gpioPlan);
    }


    private void setPlanType(GpioPlan gpioPlan) {
        if (gpioPlan instanceof ManualGpioPlan) {
            this.gpioPlanType = GpioType.MANUAL;
            binder = new BeanValidationBinder<>(ManualGpioPlan.class);
            typeRadioGroup.setValue(GpioType.MANUAL.name);
            configureBinder();
        } else if (gpioPlan instanceof TimeGpioPlan) {
            this.gpioPlanType = GpioType.TIME;
            binder = new BeanValidationBinder<>(TimeGpioPlan.class);
            typeRadioGroup.setValue(GpioType.TIME.name);
            configureBinder();
        } else {
            log.error("Unexpected type during creating GPIO plan form!");
        }
    }

    private void validateAndSave() {
        try {
            if (binder.isValid()) {
                binder.writeBean(gpioPlan);
                gpioPlan.setPinAddress(gpioPlan.getPin().getAddress());
                fireEvent(new GpioPlanForm.SaveEvent(this, gpioPlan));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class GpioPlanFormEvent extends ComponentEvent<GpioPlanForm> {
        private GpioPlan gpioPlan;

        protected GpioPlanFormEvent(GpioPlanForm source, GpioPlan gpioPlan) {
            super(source, false);
            this.gpioPlan = gpioPlan;
        }

        public GpioPlan getGpioPlan() {
            return gpioPlan;
        }
    }

    public static class SaveEvent extends GpioPlanForm.GpioPlanFormEvent {
        SaveEvent(GpioPlanForm source, GpioPlan gpioPlan) {
            super(source, gpioPlan);
        }
    }

    public static class DeleteEvent extends GpioPlanForm.GpioPlanFormEvent {
        DeleteEvent(GpioPlanForm source, GpioPlan gpioPlan) {
            super(source, gpioPlan);
        }
    }

    public static class CloseEvent extends GpioPlanForm.GpioPlanFormEvent {
        CloseEvent(GpioPlanForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    @Getter
    enum GpioType {
        MANUAL("Manual"),
        TIME("Time");

        private final String name;

        GpioType(String name) {
            this.name = name;
        }
    }
}
