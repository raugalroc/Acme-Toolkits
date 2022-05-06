package acme.entities.inventions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

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
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invention extends AbstractEntity {

	// Serialisation identifier ----------------------------------------
	
	protected static final long serialVersionUID		= 1L;
	
	// Atributes -------------------------------------------------------
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			name;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String			code;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			technology;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			description;
	
	@Valid
	protected Money				retailPrice;
	
	@URL
	protected String			link;
	
	protected InventionType		inventionType;
	
	@NotNull
	protected Boolean			published;
	
	// Derived attributes ----------------------------------------------
	
	public boolean isSpam(final SystemConfiguration systemConfiguration) {
		
		final String text = this.getName() + "\n" + this.getTechnology() + "\n" + this.getDescription();
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
			
			while (matcher.find()) {weakMatches++;};
		}
		final double weakRatio = weakMatches / numberOfWords;
		if (weakRatio > weakThreshold) return true;
		
		for (final String term: strongTerms) {
			word = "\\b" + term.replace(" ", "\\s+") + "\\b";
			pattern = java.util.regex.Pattern.compile(word, java.util.regex.Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(text);
			
			while (matcher.find()) {strongMatches++;};
		}
		final double strongRatio = strongMatches / numberOfWords;
		if (strongRatio > strongThreshold) return true;
		
		return false;

	}

	
	// Relationships ---------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor				inventor;
	
}
