package com.entain.sport;

import java.time.LocalDateTime;

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

			 repository.save(new SportType("hockey"));			 
			 repository.save(new SportType("etc"));

			 eventRepo.save(new SportEvent( "football competition", LocalDateTime.now(), EventStatus.INACTIVE, repository.save(new SportType("football"))));
		};
	} 

}
