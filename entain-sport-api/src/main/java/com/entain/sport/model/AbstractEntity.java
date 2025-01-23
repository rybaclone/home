package com.entain.sport.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "id")
	protected int id;

}
