package com.mikrotik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mikrotik.bean.Question;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDateConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class QuestionLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	private final CheckBox cb;
	private final TextField name;
	private final DateField date; 
	private final TextField rating;

	//TODO: NEXT
	private final Grid<Question> grid;

	public QuestionLayout(Question question, QuestionChangeListener changeListener) {
		setWidth("100%");
		setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		/* initiate */
		cb = new CheckBox();
		name = new TextField();
		date = new DateField();
		rating = new TextField();
		// TODO: NEXT
		// grid = new Grid<>(Question.class);
		
		name.setEnabled(false);
		date.setDateFormat("dd-MMM");
		date.setEnabled(false);
		rating.setEnabled(false);
		rating.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		rating.setWidth("50");

		Binder<Question> binder = new Binder<>(Question.class);

		// Binds fields in this class to those in Question based on their names
		// binder.bindInstanceFields(this);

		// The following does the same more explicitly
		binder.bind(name, Question::getName, Question::setName);
		binder.bind(cb, Question::isDone, Question::setDone);
		binder.forField(rating).withConverter(new StringToLongConverter("Wrong  number")).bind("rating");

		binder.forField(date).withConverter(new LocalDateToDateConverter()).bind("createDate");

		binder.setBean(question);		

		addComponent(cb);

		addComponentsAndExpand(name);		
		addComponent(date);
		addComponent(rating);

		binder.addValueChangeListener(event -> changeListener.questionChanged(question));
	}
}
