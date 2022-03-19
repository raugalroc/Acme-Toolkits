package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

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
	    
	    Double             	                 numberPatronages;
	    
	    Map<Status, Double>         averageBudgetPatronage;
	    Map<Status, Double>         deviationBudgetPatronage;
	    Map<Status, Double>         minimumBudgetPatronage;
	    Map<Status, Double>         maximumBudgetPatronage;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------


}
