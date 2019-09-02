package com.mikrotik;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mikrotik.bean.Question;


import java.util.List;

import javax.transaction.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Transactional
    void deleteByDone(boolean done);
    
    @Transactional
    void deleteById(long id);
        
    Question findById(long id);
    
    List<Question> findAll();
        
    List<Question> findByDone(boolean done);           
    
}
