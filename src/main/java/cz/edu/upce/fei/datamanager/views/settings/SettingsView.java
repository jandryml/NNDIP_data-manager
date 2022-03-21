package cz.edu.upce.fei.datamanager.views.settings;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.views.MainLayout;

import javax.annotation.security.PermitAll;

/**
 * A Designer generated component for the settings-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
@PermitAll
@Tag("settings-view")
@JsModule("./src/views/settings/settings-view.ts")
public class SettingsView extends LitTemplate {

    /**
     * Creates a new SettingsView.
     */
    public SettingsView() {
        // You can initialise any data required for the connected UI components here.
    }

}
