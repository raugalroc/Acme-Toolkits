package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected PatronageStatus			status;
	
	@NotBlank
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String 			code;

	@NotBlank
	@Length(min=0, max=255)
	protected String			legalStuff;
	
	@NotNull
	@Valid
	protected Money				budget;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date				creationTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endTime;
	
	@URL
	protected String 			link;
	
	@NotNull
	protected Boolean			published;
	
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@ManyToOne
	protected Patron			patron;
	
	@NotNull
	@ManyToOne
	protected Inventor			inventor;
	
	
}
