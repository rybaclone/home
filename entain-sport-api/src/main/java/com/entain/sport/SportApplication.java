package com.entain.sport;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.entain.sport.model.EventStatus;
import com.entain.sport.model.SportEvent;
import com.entain.sport.model.SportType;
import com.entain.sport.repo.SportEventRepository;
import com.entain.sport.repo.SportTypeRepository;

@SpringBootApplication
public class SportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner demo(SportTypeRepository repository, SportEventRepository eventRepo) {
		return (args) -> {
			// save some demo records
			LocalDateTime ldt =  LocalDateTime.now();
					
			SportType football = new SportType("football");
			 repository.save(football);			 
			 repository.save(new SportType("etc"));

 eventRepo.save(new SportEvent( "Eurobasket 2025", ldt.plus(Period.ofMonths(6)), EventStatus.INACTIVE, repository.save(new SportType("basketball"))));
			 //June 11 to July 19, 2026.
			 eventRepo.save(new SportEvent( "World Cup 2026", LocalDateTime.parse("2026-06-11T12:00:00"), EventStatus.INACTIVE, repository.save(football)));
			 
			 eventRepo.save(new SportEvent( "Old volleyball competition", ldt.minus(Period.ofDays(30)), EventStatus.INACTIVE, repository.save(new SportType("volleyball"))));
		};
	} 

}
