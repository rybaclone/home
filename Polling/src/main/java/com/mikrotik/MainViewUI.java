package com.mikrotik;

//import com.mikrotik.bean.Answer;
import com.mikrotik.bean.Question;
import com.mikrotik.ui.QuestionView;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

@SpringUI // vs ? @Route(value="polling")
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
		// addActionButtons();
	}

	private void fillTestQuestionList() {

		for (int i = 0; i < 3; i++) {
			Question tmp = new Question("question name " + i, "question description " + i);
			lstQuestions.addQuestion(tmp);
		}

	}

	/*
	private void fillTestAnswersList() {

		for (int i = 1; i < lstQuestions.getComponentCount() - 1; i++) {
			for (int j = 0; j < 2; j++) {
				Answer tmp = new Answer("answer text " + j, i);
			}
		}
	}
	*/

	private void setupLayout() {
		layout = new VerticalLayout();
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		Label header = new Label("Questionary");
		layout.addComponent(header);
		header.addStyleName(ValoTheme.LABEL_H1);
		setContent(layout);
	}

	private void viewForm() {

		QuestionView viewForm = new QuestionView();
		layout.addComponent(viewForm);

	}

	private void editForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setWidth("50%");
	}

	private void addForm() {
		VerticalLayout vl = new VerticalLayout();
		HorizontalLayout formLayout = new HorizontalLayout();
		HorizontalLayout hlDescrioption = new HorizontalLayout();

		TextArea textArea = new TextArea("");
		textArea.setPlaceholder("Description write here ...");
		textArea.setWidth("100%");
		textArea.setHeight("50");

		hlDescrioption.addComponentsAndExpand(textArea);

		TextField txtNameField = new TextField();
		txtNameField.focus();
		Button addButton = new Button("");

		// NEW
		Button voteUpButton = new Button("Up");
		voteUpButton.addClickListener(click -> lstQuestions.voteIncrement());
		
		Button voteDownButton = new Button("Down");
		 voteDownButton.addClickListener(click -> lstQuestions.voteDecrement());

		/* short list */
		Button deleteButton = new Button("Delete redundant items");
		deleteButton.addClickListener(click -> lstQuestions.deleteCompleted());

		formLayout.addComponentsAndExpand(txtNameField);
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(addButton);
		buttons.addComponent(deleteButton);
		buttons.addComponent(voteUpButton);
		buttons.addComponent(voteDownButton);

		formLayout.addComponent(buttons);
		// formLayout.addComponent(deleteButton);

		vl.setWidth("75%");
		vl.addComponent(formLayout);
		vl.addComponent(hlDescrioption);

		layout.addComponent(vl);

		addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addButton.setIcon(VaadinIcons.PLUS);

		addButton.addClickListener(click -> {
			lstQuestions.addQuestion(new Question(txtNameField.getValue(), textArea.getValue()));
			txtNameField.setValue("");
			txtNameField.focus();
			textArea.setValue("");
		});
		addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}

	private void addQuestionList() {
		layout.addComponent(lstQuestions);
	}

}
