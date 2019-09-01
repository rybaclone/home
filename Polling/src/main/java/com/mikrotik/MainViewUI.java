package com.mikrotik;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import org.h2.value.Value;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")

public class MainViewUI extends UI {

    private VerticalLayout layout;

    @Autowired
    QuestionList lstQuestions;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addForm();
        fillTestQuestionList();
        addQuestionList();
       
    }

    private void fillTestQuestionList() {

    	for (int i=0; i < 3; i++) {
    		Question tmp = new Question("question name "+i, "question description "+i);
    		lstQuestions.addQuestion(tmp);
    	}
		
	}

	private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label header = new Label("Questionary");
        layout.addComponent(header);
        header.addStyleName(ValoTheme.LABEL_H1);
        setContent(layout);
    }

   

    private void viewForm() {
    	 HorizontalLayout formLayout = new HorizontalLayout();
         formLayout.setWidth("50%");
    }
    
    private void editForm() {
    	 HorizontalLayout formLayout = new HorizontalLayout();
         formLayout.setWidth("50%");
    }
    
    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("50%");

        TextField txtNameField = new TextField();
        txtNameField.focus();
        Button addButton = new Button("");
        
     
        Button deleteButton = new Button("Delete redundant items");
        deleteButton.addClickListener(click->lstQuestions.deleteCompleted());
        
        formLayout.addComponentsAndExpand(txtNameField);
        formLayout.addComponent(addButton);
        formLayout.addComponent(deleteButton);
        
        layout.addComponent(formLayout);

        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.PLUS);

        addButton.addClickListener(click -> {
        	lstQuestions.addQuestion(new Question(txtNameField.getValue()));
            txtNameField.setValue("");
            txtNameField.focus();
        });
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addQuestionList() {
        layout.addComponent(lstQuestions);
    }

}
