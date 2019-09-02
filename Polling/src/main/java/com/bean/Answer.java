package com.mikrotik.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String text;
	private long questionId;
	
	@ManyToOne(optional=false)
    @JoinColumn(name = "questionId", referencedColumnName="id", insertable=false, updatable=false)
    private Question question;
    	
	public Answer() {
        super();
    }
	
	public Answer(String text, long questionId) {
	
       this.text = text;
       this.questionId = questionId;
    }


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
/*
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
*/	
	
	
}
