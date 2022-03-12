package cz.edu.upce.fei.datamanager.views.actionconfig;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.edu.upce.fei.datamanager.views.MainLayout;
import javax.annotation.security.PermitAll;

@PageTitle("Action config")
@Route(value = "actions", layout = MainLayout.class)
@PermitAll
public class ActionconfigView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public ActionconfigView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);
    }

}
