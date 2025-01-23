package com.entain.sport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="sport_type")
@Data
public class SportType extends AbstractEntity   {
	
	private String name;
	
	public SportType(String name) {
		super();
		this.name = name;
	}
	
}
