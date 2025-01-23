package com.entain.sport.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entain.sport.model.SportEvent;

@Repository
public interface SportEventRepository extends JpaRepository<SportEvent, Integer>{

}
