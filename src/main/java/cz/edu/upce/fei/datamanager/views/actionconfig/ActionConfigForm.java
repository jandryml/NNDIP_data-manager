package cz.edu.upce.fei.datamanager.views.actionconfig;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import cz.edu.upce.fei.datamanager.data.entity.OutputType;

/**
 * A Designer generated component for the action-config-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("action-config-form")
@JsModule("./src/views/actionconfig/action-config-form.ts")
public class ActionConfigForm extends LitTemplate {

    @Id("name")
    private TextField name;
    @Id("address")
    private TextField address;
    @Id("value")
    private TextField value;
    @Id("outputType")
    private ComboBox<OutputType> outputType;
    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    /**
     * Creates a new ActionConfigForm.
     */
    public ActionConfigForm() {
        // You can initialise any data required for the connected UI components here.
        outputType.setItems(OutputType.values());
        outputType.setItemLabelGenerator(OutputType::name);
    }

    public Button getSave() {
        return save;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getClose() {
        return close;
    }
}
