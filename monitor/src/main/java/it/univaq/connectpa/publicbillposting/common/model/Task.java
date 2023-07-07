package it.univaq.connectpa.publicbillposting.common.model;

import lombok.Data;

@Data
public class Task {

	private String name;

	private Long relativeStartTime;

	private Long relativeEndTime;

}
