package com.mikrotik;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;


public class QuestionLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	private final CheckBox done;
    private final TextField name;
    private final Button btnPlus;
    private final Button btnMinus;
        
    private final HorizontalLayout hlButtons;
    
    //TODO: Grid<Question> grid;


    public QuestionLayout(Question question, QuestionChangeListener changeListener) {
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();
        name = new TextField();

        btnMinus = new Button();
        btnPlus = new Button();
        hlButtons = new HorizontalLayout();
             
        name.setEnabled(false);
                
        btnMinus.setIcon(VaadinIcons.MINUS);
        btnPlus.setIcon(VaadinIcons.PLUS);
        
        Binder<Question> binder = new Binder<>(Question.class);
        
        //Binds fields in this class to those in Question based on their names
       binder.bindInstanceFields(this);
       
        // The following does the same more explicitly
     	/* 
	 binder.bind(name, Question::getName, Question::setName);
         binder.bind(done, Question::isDone, Question::setDone);
	*/
             
        binder.setBean(question);

        addComponent(done);
        hlButtons.addComponent(btnPlus);
        hlButtons.addComponent(btnMinus);
       
        addComponentsAndExpand(name);     
        addComponent(hlButtons);

        binder.addValueChangeListener(event -> changeListener.questionChanged(question));
    }
}
