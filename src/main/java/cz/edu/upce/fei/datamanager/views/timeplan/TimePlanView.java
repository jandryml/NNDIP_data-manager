package cz.edu.upce.fei.datamanager.views.timeplan;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

/**
 * A Designer generated component for the time-plan-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Time plan")
@Route(value = "time-plan", layout = MainLayout.class)
@PermitAll
@Tag("time-plan-view")
@JsModule("./src/views/timeplan/time-plan-view.ts")
public class TimePlanView extends LitTemplate {

    /**
     * Creates a new TimePlanView.
     */
    public TimePlanView() {
        // You can initialise any data required for the connected UI components here.
    }

}
