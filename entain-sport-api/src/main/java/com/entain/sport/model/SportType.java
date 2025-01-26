package com.entain.sport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="sport_type")
@Data
@NoArgsConstructor
public class SportType/* extends AbstractEntity */  {
		
	//@GeneratedValue(strategy = GenerationType.IDENTITY)//
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected int id;
	
	private String name;
	
	public SportType(String name) {
		//super();
		this.name = name;
	}
	
}
