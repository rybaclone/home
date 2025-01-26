package com.entain.sport.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entain.sport.model.SportType;

@Repository
public interface SportTypeRepository extends JpaRepository<SportType, Integer>{
	
	List<SportType> findByName(String name);
}
