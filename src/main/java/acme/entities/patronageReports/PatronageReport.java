package acme.entities.patronageReports;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.patronage.Patronage;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	
	@NotBlank
	protected String 			sequenceNumber;
	
	@NotNull
	@Past
	protected LocalDateTime		creationMoment;
	
	@NotBlank
	@Length(min=0, max=256)
	protected String			memorandum;
	
	@URL
	protected String 			link;
	
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@ManyToOne
	protected Patronage			patronage;
	
	
}
