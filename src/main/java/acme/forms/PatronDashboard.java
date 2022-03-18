package acme.entities.patrondashboard;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer	numberProposed;
	Integer	numberDenied;
	Integer	numberAccepted;
	Double	averageProposed;
	Double	averageDenied;
	Double	averageAccepted;
	Double	deviationProposed;
	Double	deviationAccepted;
	Double	deviationDenied;
	Double	minimumProposed;
	Double	minimumAccepted;
	Double	minimumDenied;
	Double	maximumProposed;
	Double	maximumAccepted;
	Double	maximumDenied;


}
