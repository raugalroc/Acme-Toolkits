package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronage.PatronageStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------
		Double						         numberComponents;
		
	    Map<Pair<String, String>, Double>    averageRetailPriceComponents;
	    Map<Pair<String, String>, Double>    deviationRetailPriceComponents;
	    Map<Pair<String, String>, Double>    minimumRetailPriceComponents;
	    Map<Pair<String, String>, Double>    maximumRetailPriceComponents;
	    
	    Double                               numberTools;
	    
	    Map<String, Double>                  averageRetailPriceTools;
	    Map<String, Double>                  deviationRetailPriceTools;
	    Map<String, Double>                  minimumRetailPriceTools;
	    Map<String, Double>                  maximumRetailPriceTools;
	    
	    Map<PatronageStatus,Double>					numberPatronages;
	    
	    Map<PatronageStatus, Double>         averageBudgetPatronage;
	    Map<PatronageStatus, Double>         deviationBudgetPatronage;
	    Map<PatronageStatus, Double>         minimumBudgetPatronage;
	    Map<PatronageStatus, Double>         maximumBudgetPatronage;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------


}
