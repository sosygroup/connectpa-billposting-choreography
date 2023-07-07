package it.univaq.connectpa.publicbillposting.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class InstanceDetail {

	private String instanceId;

	private List<Task> tasks;

	private List<String> missingData;

	public InstanceDetail() {
		this.tasks = new ArrayList<Task>();
		this.missingData = new ArrayList<String>();
	}
	
}
