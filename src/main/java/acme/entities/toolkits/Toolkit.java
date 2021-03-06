package acme.entities.toolkits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;
import spam.detector.SpamDetector;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {
	
	// Serialisation identifier ----------------------------------------

	protected static final long serialVersionUID		= 1L;
	
	// Atributes -------------------------------------------------------
	

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String			code;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			title;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			description;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			assemblyNotes;

	@URL
	protected String			link; 
	
	@NotNull
	protected Boolean			published;
	
	// Derived attributes ----------------------------------------------
	
	public boolean isSpam(final SystemConfiguration systemConfiguration) {
		
		final String text = this.getTitle() + "\n" + this.getDescription() + "\n" + this.getAssemblyNotes();
		return SpamDetector.isSpam(text, systemConfiguration.getWeakSpamTerms(), systemConfiguration.getStrongSpamTerms(), systemConfiguration.getStrongSpamTermsThreshold(), systemConfiguration.getWeakSpamTermsThreshold());

	}
	
	// Relationships ---------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor				inventor;
		
}
