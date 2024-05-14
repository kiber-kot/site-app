package ru.site.application.views.test;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import ru.site.application.views.MainLayout;

@PageTitle("Тест")
@Route(value = "test", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class TestView extends Composite<VerticalLayout> {

    public TestView() {
        Tabs tabs = new Tabs();
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        checkboxGroup.setLabel("Checkbox Group");
        checkboxGroup.setWidth("min-content");
        checkboxGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Назад");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Вперед");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(tabs);
        getContent().add(checkboxGroup);
        getContent().add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonPrimary2);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }
}
