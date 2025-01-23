package com.entain.sport.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sport_event")
public class SportEvent extends AbstractEntity {

	private String name;
	@Column(name = "start_time")
	private LocalDateTime startTime;

	public SportEvent(String name, LocalDateTime startTime, EventStatus eventStatus, SportType sportType) {
		super();
		this.name = name;
		this.startTime = startTime;
		this.eventStatus = eventStatus;
		this.sportType = sportType;
	}

	@OneToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private SportType sportType;

	@Enumerated(EnumType.STRING)
	@Column(name = "event_status")
	private EventStatus eventStatus;

}
