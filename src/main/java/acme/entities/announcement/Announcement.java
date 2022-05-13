
package acme.entities.announcement;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

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
		final List<String> weakTerms = Arrays.stream(systemConfiguration.getWeakSpamTerms().split(";")).map(String::trim).collect(Collectors.toList());
		final Double weakThreshold = systemConfiguration.getWeakSpamTermsThreshold() / 100;
		final List<String> strongTerms = Arrays.stream(systemConfiguration.getStrongSpamTerms().split(";")).map(String::trim).collect(Collectors.toList());
		final Double strongThreshold = systemConfiguration.getStrongSpamTermsThreshold() / 100;
		
		final int numberOfWords = text.split("\\s+").length;
		double weakMatches = 0.;
		double strongMatches = 0.;
		
		String word;
		java.util.regex.Pattern pattern;
		Matcher matcher;
		
		for (final String term: weakTerms) {
			word = "\\b" + term.replace(" ", "\\s+") + "\\b";
			pattern = java.util.regex.Pattern.compile(word, java.util.regex.Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(text);
			
			while (matcher.find()) {weakMatches += term.split("\\s+").length;};
		}
		final double weakRatio = weakMatches / numberOfWords;
		if (weakRatio > weakThreshold) return true;
		
		for (final String term: strongTerms) {
			word = "\\b" + term.replace(" ", "\\s+") + "\\b";
			pattern = java.util.regex.Pattern.compile(word, java.util.regex.Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(text);
			
			while (matcher.find()) {strongMatches += term.split("\\s+").length;};
		}
		final double strongRatio = strongMatches / numberOfWords;
		if (strongRatio > strongThreshold) return true;
		
		return false;

	}

	
	// Relationships ---------------------------------------------------
	
}

