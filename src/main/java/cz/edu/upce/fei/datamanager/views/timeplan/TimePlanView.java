package cz.edu.upce.fei.datamanager.views.timeplan;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.plan.TimePlan;
import cz.edu.upce.fei.datamanager.data.service.TimePlanService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

/**
 * A Designer generated component for the time-plan-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Time plan")
@Route(value = "time-plan", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("time-plan-view")
@JsModule("./src/views/timeplan/time-plan-view.ts")
public class TimePlanView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addPlanButton")
    private Button addPlanButton;
    @Id("grid")
    private Grid<TimePlan> grid;
    @Id("timePlanForm")
    private TimePlanForm timePlanForm;
    
    private final TimePlanService timePlanService;

    /**
     * Creates a new TimePlanView.
     */
    public TimePlanView(TimePlanService timePlanService) {
        this.timePlanService = timePlanService;

        configureGrid();
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
        closeEditor();
    }

    private void configureGrid() {
        grid.addComponentColumn(item -> {
                    Icon icon;
                    if (item.isEnabled()) {
                        icon = VaadinIcon.CHECK_CIRCLE.create();
                        icon.setColor("green");
                    } else {
                        icon = VaadinIcon.CLOSE_CIRCLE.create();
                        icon.setColor("red");
                    }
                    return icon;
                })
                .setKey("enabled")
                .setHeader("Enabled");

        grid.addColumn(TimePlan::getName).setHeader("Name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editTimePlan(event.getValue()));
    }

    private void configureBinding() {
        addPlanButton.addClickListener(click -> addTimePlan());

        timePlanForm.addListener(TimePlanForm.SaveEvent.class, this::saveTimePlan);
        timePlanForm.addListener(TimePlanForm.DeleteEvent.class, this::deleteTimePlan);
        timePlanForm.addListener(TimePlanForm.CloseEvent.class, e -> closeEditor());
    }

    private void addTimePlan() {
        grid.asSingleSelect().clear();
        editTimePlan(new TimePlan());
    }

    private void saveTimePlan(TimePlanForm.SaveEvent event) {
        timePlanService.saveTimePlan(event.getTimePlan());
        updateList();
        closeEditor();
    }

    private void deleteTimePlan(TimePlanForm.DeleteEvent event) {
        timePlanService.deleteTimePlan(event.getTimePlan());
        updateList();
        closeEditor();
    }

    public void editTimePlan(TimePlan timePlan) {
        if (timePlan == null) {
            closeEditor();
        } else {
            timePlanForm.setTimePlan(timePlan);
            timePlanForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        timePlanForm.setTimePlan(null);
        timePlanForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(timePlanService.findAllTimePlans());
    }


}
