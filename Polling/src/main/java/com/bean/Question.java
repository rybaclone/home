package com.mikrotik.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String description;

//	@Column(name = "date", nullable = false)
	private Date createDate;
	//private LocalDate createDate; 
	public int rating;

	private boolean done;

	public Question() {
	}

/*	public Question(String name) {
		this.name = name;
		this.description = "auto fill for test";
		this.createDate = new Date();
		this.rating = 0;
	}
*/

	public Question(String name, String description) {
		this.name = name;
		this.description = description;
		this.createDate = new Date(); // LocalDate.now(); 
		this.rating = 0;
	}
	
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	public Question(String name, boolean done) {
		this.name = name;
		this.done = done;
	}
}
