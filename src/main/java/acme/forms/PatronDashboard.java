package acme.forms;


import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

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
	
	double	numberProposed;
	double	numberDenied;
	double	numberAccepted;
	Map<String, Pair<Status, Double>> averageBudged;
	Map<String, Pair<Status, Double>> deviationBudged;
	Map<String, Pair<Status, Double>> minimumBudged;
	Map<String, Pair<Status, Double>> maximumBudged;




}
