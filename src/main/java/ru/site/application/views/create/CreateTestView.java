package ru.site.application.views.create;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import ru.site.application.views.MainLayout;

@PageTitle("Создание теста")
@Route(value = "create-test", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class CreateTestView extends Composite<VerticalLayout> {

    public CreateTestView() {
        Tabs tabs = new Tabs();
        TextArea textArea = new TextArea();
        ComboBox comboBox = new ComboBox();
        TextArea textArea2 = new TextArea();
        TextArea textArea3 = new TextArea();
        TextArea textArea4 = new TextArea();
        TextArea textArea5 = new TextArea();
        ComboBox comboBox2 = new ComboBox();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        Hr hr = new Hr();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        textArea.setLabel("Напишите воопрос");
        textArea.setWidth("100%");
        comboBox.setLabel("Выберите кол-во ответов");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        textArea2.setLabel("1 Вариант ответа");
        textArea2.setWidth("100%");
        textArea3.setLabel("2 Вариант ответа");
        textArea3.setWidth("100%");
        textArea4.setLabel("3 Вариант ответа");
        textArea4.setWidth("100%");
        textArea5.setLabel("4 Вариант ответа");
        textArea5.setWidth("100%");
        comboBox2.setLabel("Какой вариант ответа является верным?");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        buttonPrimary.setText("Сохранить");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Создать новый вопрос");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(tabs);
        getContent().add(textArea);
        getContent().add(comboBox);
        getContent().add(textArea2);
        getContent().add(textArea3);
        getContent().add(textArea4);
        getContent().add(textArea5);
        getContent().add(comboBox2);
        getContent().add(buttonPrimary);
        getContent().add(buttonPrimary2);
        getContent().add(hr);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
