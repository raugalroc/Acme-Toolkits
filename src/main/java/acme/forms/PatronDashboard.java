package acme.forms;


import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronage.PatronageStatus;
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
	Map<Pair<String, PatronageStatus>, Double> averageBudget;
	Map<Pair<String, PatronageStatus>, Double> deviationBudget;
	Map<Pair<String, PatronageStatus>, Double> minimumBudget;
	Map<Pair<String, PatronageStatus>, Double> maximumBudget;




}
