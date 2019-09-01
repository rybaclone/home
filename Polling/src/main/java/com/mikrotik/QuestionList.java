package com.mikrotik;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
class QuestionList extends VerticalLayout implements QuestionChangeListener {
    @Autowired
    QuestionRepository repository;
    private List<Question> questions;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setQuestions(repository.findAll());
    }

    private void setQuestions(List<Question> questions) {
        this.questions = questions;
        removeAllComponents();
        questions.forEach(question -> addComponent(new QuestionLayout(question, this)));
    }

     void addQuestion(Question question) {
        repository.save(question);
        update();
    }

    @Override
    public void questionChanged(Question question) {
        addQuestion(question);
    }


    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
