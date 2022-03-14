package acme.entities.toolkits;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.component.Component;
import acme.entities.tools.Tool;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolKit extends AbstractEntity {
	
	// Serialisation identifier ----------------------------------------
	
		protected static final long serialVersionUID		= 1L;
		
		// Atributes -------------------------------------------------------
		
	
		@NotNull
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
		
		// Derived attributes ----------------------------------------------
		
		// Relationships ---------------------------------------------------
		
		@NotNull
		@Valid
		@ManyToMany
		protected Collection<Tool>			tool;
		
		@NotNull
		@Valid
		@ManyToMany
		protected Collection<Component>			component;

}
