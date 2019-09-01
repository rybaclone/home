package com.mikrotik;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import javax.transaction.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Transactional
    void deleteByDone(boolean done);
    
    @Transactional
    void deleteById(long id);
    
    List<Question> findAll();
 /*   
    @Transactional
    List<Question> findAllByOrderByDateDesc();
    
    
    */
}
