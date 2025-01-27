package com.entain.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.entain.sport.model.SportEvent;
import com.entain.sport.repo.SportEventRepository;

@DataJpaTest (showSql = false)
public class RepositoryTest {

	@Autowired
	private SportEventRepository repository;
	
	
	@Test
	public void testRepositorySize() {
		assertEquals( repository.findAll().size(), 3);
		
	}
	
	@Test void testFindById() {
		Optional<SportEvent> optional = repository.findById(2);
		assertTrue (optional.isPresent());
	}
	
}
