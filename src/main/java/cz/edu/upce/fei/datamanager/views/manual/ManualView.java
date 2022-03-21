package cz.edu.upce.fei.datamanager.views.manual;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

/**
 * A Designer generated component for the manual-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Manual")
@Route(value = "manual", layout = MainLayout.class)
@PermitAll
@Tag("manual-view")
@JsModule("./src/views/manual/manual-view.ts")
public class ManualView extends LitTemplate {

    /**
     * Creates a new ManualView.
     */
    public ManualView() {
        // You can initialise any data required for the connected UI components here.
    }

}
