package com.entain.sport.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entain.sport.model.EventStatus;
import com.entain.sport.model.SportEvent;
import com.entain.sport.model.SportType;
import com.entain.sport.repo.SportEventRepository;

@RestController
@RequestMapping("/api/v01")
public class SportEventController {

	@Autowired
	private SportEventRepository repository;

	/*
	 * { "name": "basketball competition 3", "startTime": "2025-01-27T10:00:00",
	 * "sportType": {"id": 2, "name": "etc"}, "eventStatus": "ACTIVE" }
	 */
	@PostMapping("/events/add")
	public ResponseEntity<SportEvent> createEvent(@RequestBody SportEvent event) {

		SportEvent newEvent = repository.save(event);
		return ResponseEntity.status(HttpStatus.CREATED).body(event);

	}

	// localhost:8080/api/v01/event/update/1?status=FINISHED
	@PutMapping("/event/update/{id}")
	public ResponseEntity<SportEvent> updateEvent(@PathVariable int id, @RequestParam EventStatus status) {
		Optional<SportEvent> optional = repository.findById(id);
		if (optional.isPresent()) {
			SportEvent event = optional.get();			
			return new ResponseEntity<>(event, checkStatus(status, event));

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// localhost:8080/api/v01/event/update/1?status=ACTIVE
	@GetMapping("/events/list") // optional filters by status and sport type
	public ResponseEntity<List<SportEvent>> getAllEvents(@RequestParam(required = false) EventStatus status, SportType sportType) {
		
		List<SportEvent> list;
		
		if  (sportType.getId() > 0 ) {
			list = repository.findBySportType(sportType);		
		} else if (status != null) {
			list = repository.findByEventStatus(status);
		} else {
			list = repository.findAll();
		}

		if (list.size() > 0) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<SportEvent> getEventById(@PathVariable Integer id) {
		Optional<SportEvent> event = repository.findById(id);
		if (event.isPresent()) {
			return new ResponseEntity<>(event.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Entain";
	}

	/*
	 * Change sport event status inactive -> active(if start_time is in the past) ->
	 * finished !-> inactive !-> finished
	 */
	private HttpStatus checkStatus(EventStatus status, SportEvent event) {

		HttpStatus httpStatus = HttpStatus.OK;

		LocalDateTime ldt = LocalDateTime.now();

		// nok
		if ((event.getStartTime().compareTo(ldt) < 0) || (event.getEventStatus() == status) // both status equal
				|| (event.getEventStatus() == EventStatus.FINISHED)
				|| (event.getEventStatus() == EventStatus.INACTIVE && status == EventStatus.FINISHED)) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
		// ok
		if ((event.getEventStatus() == EventStatus.INACTIVE && status == EventStatus.ACTIVE)
				|| (event.getEventStatus() == EventStatus.ACTIVE && status == EventStatus.FINISHED)) {
			event.setEventStatus(status);
			repository.save(event);
			return HttpStatus.RESET_CONTENT;
		}

		return httpStatus;

	}

}
