package cz.edu.upce.fei.datamanager.views.gpioplan;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.GpioPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.ManualGpioPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.TimeGpioPlan;
import cz.edu.upce.fei.datamanager.data.service.plan.GpioPlanService;
import cz.edu.upce.fei.datamanager.views.MainLayout;
import lombok.extern.slf4j.Slf4j;

/**
 * A Designer generated component for the gpio-plan-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@PageTitle("Gpio plan")
@Route(value = "gpio-plan", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("gpio-plan-view")
@JsModule("./src/views/gpioplan/gpio-plan-view.ts")
public class GpioPlanView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addPlanButton")
    private Button addPlanButton;
    @Id("grid")
    private Grid<GpioPlan> grid;
    @Id("gpioPlanForm")
    private GpioPlanForm gpioPlanForm;

    private final GpioPlanService gpioPlanService;


    /**
     * Creates a new GpioPlanView.
     */
    public GpioPlanView(GpioPlanService gpioPlanService) {
        this.gpioPlanService = gpioPlanService;

        configureGrid();
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
        closeEditor();
    }

    private void configureGrid() {
        grid.addComponentColumn(item -> {
            Checkbox enabled = new Checkbox();
            enabled.setValue(item.isEnabled());
            enabled.addValueChangeListener(it -> {
                item.setEnabled(enabled.getValue());
                gpioPlanService.saveGpioPlan(item);
            });
            return enabled;
        });

        grid.addColumn(GpioPlan::getName).setHeader("Name");
        grid.addColumn(GpioPlan::getPlanType).setHeader("Type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editGpioPlan(event.getValue()));
    }

    private void configureBinding() {
        addPlanButton.addClickListener(click -> addGpioPlan());

        gpioPlanForm.addListener(GpioPlanForm.SaveEvent.class, this::saveGpioPlan);
        gpioPlanForm.addListener(GpioPlanForm.DeleteEvent.class, this::deleteGpioPlan);
        gpioPlanForm.addListener(GpioPlanForm.CloseEvent.class, e -> closeEditor());
    }

    private void addGpioPlan() {
        grid.asSingleSelect().clear();
        editGpioPlan(new ManualGpioPlan());
    }

    private void saveGpioPlan(GpioPlanForm.SaveEvent event) {
        gpioPlanService.saveGpioPlan(event.getGpioPlan());
        updateList();
        closeEditor();
    }

    private void deleteGpioPlan(GpioPlanForm.DeleteEvent event) {
        gpioPlanService.deleteGpioPlan(event.getGpioPlan());
        updateList();
        closeEditor();
    }

    public void editGpioPlan(GpioPlan gpioPlan) {
        if (gpioPlan == null) {
            closeEditor();
        } else {
            setPlanType(gpioPlan);
            gpioPlanForm.setGpioPlan(gpioPlan);
            gpioPlanForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void setPlanType(GpioPlan gpioPlan) {
        if(gpioPlan instanceof ManualGpioPlan) {
            gpioPlanForm.setGpioPlanType(GpioPlanForm.GpioType.MANUAL);
        } else if (gpioPlan instanceof TimeGpioPlan) {
            gpioPlanForm.setGpioPlanType(GpioPlanForm.GpioType.MANUAL);
        } else {
            log.error("Unexpected type during creating GPIO plan form!");
        }
    }

    private void closeEditor() {
        gpioPlanForm.setGpioPlan(null);
        gpioPlanForm.setVisible(false);
        grid.deselectAll();
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(gpioPlanService.findAllGpioPlans());
    }
}