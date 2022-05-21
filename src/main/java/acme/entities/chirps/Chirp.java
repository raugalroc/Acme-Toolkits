package acme.entities.chirps;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import spam.detector.SpamDetector;

@Entity
@Getter
@Setter
public class Chirp extends AbstractEntity {

	// Serialisation identifier ----------------------------------------
	
	protected static final long serialVersionUID		= 1L;
	
	// Atributes -------------------------------------------------------
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				creationMoment;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			title;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			author;

	@NotBlank
	@Length(min = 0, max = 255)
	protected String			body;
	
	@Email
	protected String			email;
	
	// Derived attributes ----------------------------------------------
	
	public boolean isSpam(final SystemConfiguration systemConfiguration) {
		
		final String text = this.getTitle() + "\n" + this.getAuthor() + "\n" + this.getBody();
		return SpamDetector.isSpam(text, systemConfiguration.getWeakSpamTerms(), systemConfiguration.getStrongSpamTerms(), systemConfiguration.getStrongSpamTermsThreshold(), systemConfiguration.getWeakSpamTermsThreshold());

	}

	
	// Relationships ---------------------------------------------------
	
}
