package cz.edu.upce.fei.datamanager.views.addressState;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import cz.edu.upce.fei.datamanager.data.entity.AddressState;
import cz.edu.upce.fei.datamanager.data.service.AddressStateService;
import cz.edu.upce.fei.datamanager.views.MainLayout;
import lombok.extern.slf4j.Slf4j;

/**
 * A Designer generated component for the address-state-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Slf4j
@PageTitle("Address state")
@Route(value = "address-state", layout = MainLayout.class)
// TODO change security restriction
// @PermitAll
@AnonymousAllowed
@Tag("address-state-view")
@JsModule("./src/views/addressState/address-state-view.ts")
public class AddressStateView extends LitTemplate {

    @Id("grid")
    private Grid<AddressState> grid;

    private final AddressStateService addressStateService;

    /**
     * Creates a new AddressStateView.
     */
    public AddressStateView(AddressStateService addressStateService) {
        this.addressStateService = addressStateService;
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.addColumn(addressState -> addressState.getOutputType().getPrettyName()).setHeader("Output type");
        grid.addColumn(AddressState::getAddress).setHeader("Address");
        grid.addColumn(AddressState::getValue).setHeader("Value");

        grid.addColumn(AddressState::getPlanName).setHeader("Trigger plan");
        grid.addColumn(AddressState::getActionName).setHeader("Trigger action");

        updateList();
    }


    private void updateList() {
        grid.setItems(addressStateService.getAllAddressesState());
    }
}
