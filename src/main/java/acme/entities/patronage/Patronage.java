package acme.entities.patronage;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	protected Status			status;
	
	@NotNull
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String 			code;

	@NotBlank
	@Length(min=0, max=256)
	protected String			legalStuff;
	
	@NotNull
	protected Money				budget;
	
	@NotNull
	@Past
	protected LocalDateTime		creationMoment;
	
	@NotNull
	protected LocalDateTime		periodOfTime;
	
	@NotNull
	protected Money				retailPrice;
	
	@URL
	protected String 			link;
	
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@ManyToOne
	protected Patron			patron;
	
	@NotNull
	@ManyToOne
	protected Inventor			inventor;
	
	
}
