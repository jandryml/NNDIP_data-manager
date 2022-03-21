package cz.edu.upce.fei.datamanager.views.limitconfig;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

/**
 * A Designer generated component for the limit-config-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Limit config")
@Route(value = "limits", layout = MainLayout.class)
@PermitAll
@Tag("limit-config-view")
@JsModule("./src/views/limitconfig/limit-config-view.ts")
public class LimitConfigView extends LitTemplate {

    /**
     * Creates a new LimitConfigView.
     */
    public LimitConfigView() {
        // You can initialise any data required for the connected UI components here.
    }

}
