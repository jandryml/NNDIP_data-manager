package cz.edu.upce.fei.datamanager.views.manualplan;

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
import cz.edu.upce.fei.datamanager.data.entity.plan.ManualPlan;
import cz.edu.upce.fei.datamanager.data.service.ManualPlanService;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import java.util.List;

import static cz.edu.upce.fei.datamanager.views.manualplan.ManualPlanForm.*;

/**
 * A Designer generated component for the manual-plan-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Manual")
@Route(value = "manual", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("manual-plan-view")
@JsModule("./src/views/manualplan/manual-plan-view.ts")
public class ManualPlanView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addPlanButton")
    private Button addPlanButton;
    @Id("grid")
    private Grid<ManualPlan> grid;
    @Id("manualPlanForm")
    private ManualPlanForm manualPlanForm;

    private final ManualPlanService manualPlanService;

    /**
     * Creates a new ManualPlanView.
     */
    public ManualPlanView(ManualPlanService manualPlanService) {
        this.manualPlanService = manualPlanService;

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
                manualPlanService.saveManualPlan(item);
            });
            return enabled;
        });

        grid.addColumn(ManualPlan::getName).setHeader("Name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editManualPlan(event.getValue()));
    }

    private void configureBinding() {
        addPlanButton.addClickListener(click -> addManualPlan());

        manualPlanForm.addListener(SaveEvent.class, this::saveManualPlan);
        manualPlanForm.addListener(DeleteEvent.class, this::deleteManualPlan);
        manualPlanForm.addListener(CloseEvent.class, e -> closeEditor());
    }

    private void addManualPlan() {
        grid.asSingleSelect().clear();
        editManualPlan(new ManualPlan());
    }

    private void saveManualPlan(SaveEvent event) {
        manualPlanService.saveManualPlan(event.getManualPlan());
        updateList();
        closeEditor();
    }

    private void deleteManualPlan(DeleteEvent event) {
        manualPlanService.deleteManualPlan(event.getManualPlan());
        updateList();
        closeEditor();
    }

    public void editManualPlan(ManualPlan manualPlan) {
        if (manualPlan == null) {
            closeEditor();
        } else {
            manualPlanForm.setManualPlan(manualPlan);
            manualPlanForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        manualPlanForm.setManualPlan(null);
        manualPlanForm.setVisible(false);
        grid.deselectAll();
        removeClassName("editing");
    }

    private void updateList() {
        List<ManualPlan> manualPlanList = manualPlanService.findAllManualPlans();
        grid.setItems(manualPlanList);

    }
}
