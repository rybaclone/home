package com.entain.sport.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

}
