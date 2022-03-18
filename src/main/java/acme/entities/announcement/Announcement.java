
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

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity{

protected static final long serialVersionUID = 1L;
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationMoment;
	@NotNull
	@NotBlank(message = "The title must be mandatory")
	@Length(min = 0, max = 100)
	private String title;
	@NotNull
	@NotBlank(message = "The body must be mandatory")
	@Length(min = 0, max = 255)
	private String body;
	@NotNull
	private Boolean	critic;
	@URL
	private String link;

}

