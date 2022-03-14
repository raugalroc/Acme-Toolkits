package acme.patrondashboard;

import java.util.Map;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class PatronDashboard extends AbstractEntity{
	@NotNull @Min(0)
	private Integer numerProposed;
	@NotNull @Min(0)
	private Integer numerAccepted;
	@NotNull @Min(0)
	private Integer numberDenied;
	@NotNull
	private Map<Status, Money> average;
	@NotNull
	private Map<Status, Money> deviation;
	@NotNull
	private Map<Status, Money> minimum;
	@NotNull
	private Map<Status, Money> maximum;


}
