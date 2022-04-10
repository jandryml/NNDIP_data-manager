package cz.edu.upce.fei.datamanager.views.generic;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import cz.edu.upce.fei.datamanager.data.entity.Action;
import cz.edu.upce.fei.datamanager.data.service.ActionService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A Designer generated component for the dynamic-action-component template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("dynamic-action-component")
@JsModule("./src/views/generic/dynamic-action-component.ts")
public class DynamicActionComponent extends LitTemplate {

    private final List<Action> allActionList;
    private final List<DynamicActionComponentItem> dynamicActionComponentItems = new ArrayList<>();
    @Id("actionGrid")
    private Grid<DynamicActionComponentItem> actionGrid;
    @Id("addAction")
    private Button addAction;

    /**
     * Creates a new DynamicActionComponent.
     */
    public DynamicActionComponent(ActionService actionService) {
        allActionList = actionService.findAllActions();
        // You can initialise any data required for the connected UI components here.

        actionGrid.addComponentColumn(DynamicActionComponentItem::getActionComboBox).setHeader("Action");
        actionGrid.addComponentColumn(DynamicActionComponentItem::getDeleteButton);

        actionGrid.setItems(dynamicActionComponentItems);

        addAction.addClickListener(event -> {
            dynamicActionComponentItems.add(new DynamicActionComponentItem(allActionList));
            actionGrid.getDataProvider().refreshAll();
        });
    }

    public List<Action> getSelectedActions() {
        return dynamicActionComponentItems.stream()
                .filter(it -> !it.getActionComboBox().isEmpty())
                .map(it -> it.getActionComboBox().getValue())
                .toList();
    }

    public void setSelectedActions(List<Action> selectedActions) {
        dynamicActionComponentItems.clear();
        dynamicActionComponentItems.addAll(
                selectedActions.stream()
                        .map(it -> new DynamicActionComponentItem(allActionList, it))
                        .toList());
        actionGrid.getDataProvider().refreshAll();
    }


    @Getter
    private class DynamicActionComponentItem {
        private final ComboBox<Action> actionComboBox;
        private final Button deleteButton;

        public DynamicActionComponentItem(List<Action> actionList) {
            this(actionList, null);
        }

        public DynamicActionComponentItem(List<Action> actionList, Action action) {
            actionComboBox = new ComboBox<>();
            actionComboBox.setItems(actionList);
            actionComboBox.setItemLabelGenerator(Action::getName);
            actionComboBox.setRequired(true);
            if (action != null) {
                actionComboBox.setValue(action);
            }

            deleteButton = new Button();
            deleteButton.setText("Remove");
            deleteButton.addClickListener(event -> {
                dynamicActionComponentItems.remove(this);
                actionGrid.getDataProvider().refreshAll();
            });
        }
    }
}
