
package acme.entities.announcement;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import spam.detector.SpamDetector;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity {

	// Serialisation identifier ----------------------------------------
	
	protected static final long serialVersionUID 		= 1L;
	
	// Atributes -------------------------------------------------------
	
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date 				creationMoment;
	
	@NotNull
	@NotBlank
	@Length(min = 0, max = 100)
	private String 				title;
	
	@NotNull
	@NotBlank
	@Length(min = 0, max = 255)
	private String 				body;
	
	@NotNull
	private Boolean				critic;
	
	@URL
	private String 				link;
	
	// Derived attributes ----------------------------------------------
	
	public boolean isSpam(final SystemConfiguration systemConfiguration) {
		final String text = this.getTitle() + "\n" + this.getBody();
		return SpamDetector.isSpam(text, systemConfiguration.getWeakSpamTerms(), systemConfiguration.getStrongSpamTerms(), systemConfiguration.getStrongSpamTermsThreshold(), systemConfiguration.getWeakSpamTermsThreshold());
	}

	
	// Relationships ---------------------------------------------------
	
}

