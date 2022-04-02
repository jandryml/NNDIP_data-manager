package cz.edu.upce.fei.datamanager.views.manualplan;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

/**
 * A Designer generated component for the manual-plan-view template.
 *
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

    /**
     * Creates a new ManualPlanView.
     */
    public ManualPlanView() {
        // You can initialise any data required for the connected UI components here.
    }

}
