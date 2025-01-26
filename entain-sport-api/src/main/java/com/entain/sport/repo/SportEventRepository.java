package com.entain.sport.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entain.sport.model.EventStatus;
import com.entain.sport.model.SportEvent;
import com.entain.sport.model.SportType;

@Repository
public interface SportEventRepository extends JpaRepository<SportEvent, Integer>{
	
	List<SportEvent> findByName(String name);
	
	List<SportEvent> findByEventStatus(EventStatus status);

	List<SportEvent> findBySportType(SportType sportType);
	
//	List<SportEvent> findByNameAndEventStatus(String name, EventStatus status);
}
