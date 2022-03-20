package cz.edu.upce.fei.datamanager.views.dashboard;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.views.MainLayout;

/**
 * A Designer generated component for the dashboard-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@AnonymousAllowed
@Tag("dashboard-view")
@JsModule("./src/views/dashboard/dashboard-view.ts")
public class DashboardView extends LitTemplate {

    /**
     * Creates a new DashboardView.
     */
    public DashboardView() {
        // You can initialise any data required for the connected UI components here.
    }

}
