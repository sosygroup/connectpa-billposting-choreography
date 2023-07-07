package it.univaq.connectpa.publicbillposting.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = -1012382449574845645L;

	@Column(name = "eventType")
	private EventType eventType;

	private String operation;

	@Column(name = "instanceId")
	private String instanceId;

	private Long timestamp;

}
