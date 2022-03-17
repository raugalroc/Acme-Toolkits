package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------
		Integer						numberComponents;
		Integer						numberTools;
		Integer						numberPatronages;
		Double						averageRetailPriceComponents;
		Double						deviationRetailPriceComponents;
		Double						minimumRetailPriceComponents;
		Double						maximumRetailPriceComponents;
		Double						averageRetailPriceTools;
		Double						deviationRetailPriceTools;
		Double						minimumRetailPriceTools;
		Double						maximumRetailPriceTools;
		Double						averageRetailPriceBudget;
		Double						deviationRetailPriceBudget;
		Double						minimumRetailPriceBudget;
		Double						maximumRetailPriceBudget;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------


}
