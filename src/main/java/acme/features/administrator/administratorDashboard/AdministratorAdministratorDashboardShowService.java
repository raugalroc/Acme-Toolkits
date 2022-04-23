package acme.features.administrator.administratorDashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{

	@Autowired
	protected AdministratorAdministratorDashboardRepository repository;
	
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		final Double						         numberComponents = this.repository.findNumberComponents();
		
	    final Map<Pair<String, String>, Double>    averageRetailPriceComponents= new HashMap<>();
	    final Map<Pair<String, String>, Double>    deviationRetailPriceComponents= new HashMap<>();
	    final Map<Pair<String, String>, Double>    minimumRetailPriceComponents= new HashMap<>();
	    final Map<Pair<String, String>, Double>    maximumRetailPriceComponents= new HashMap<>();

	    
	    final Double                               numberTools = this.repository.findNumberTools();
	    
	    final Map<String, Double>                  averageRetailPriceTools= new HashMap<>();
	    final Map<String, Double>                  deviationRetailPriceTools= new HashMap<>();
	    final Map<String, Double>                  minimumRetailPriceTools= new HashMap<>();
	    final Map<String, Double>                  maximumRetailPriceTools= new HashMap<>();
	    
	    
	    final Map<PatronageStatus, Double>             	   numberPatronages = new EnumMap<>(PatronageStatus.class);
	    
	    final Map<PatronageStatus, Double>         averageBudgetPatronage= new EnumMap<>(PatronageStatus.class);
	    final Map<PatronageStatus, Double>         deviationBudgetPatronage= new EnumMap<>(PatronageStatus.class);
	    final Map<PatronageStatus, Double>         minimumBudgetPatronage= new EnumMap<>(PatronageStatus.class);
	    final Map<PatronageStatus, Double>         maximumBudgetPatronage= new EnumMap<>(PatronageStatus.class);
	    
	    final AdministratorDashboard result = new AdministratorDashboard();
	    
	    
	    final List<String> findAverageRetailPriceComponent = this.repository.findAverageRetailPriceComponent();
	    final List<String> findDeviationRetailPriceComponent = this.repository.findDeviationRetailPriceComponent();
	    final List<String> findMinimumRetailPriceComponent = this.repository.findMinimumRetailPriceComponent();
	    final List<String> findMaximumRetailPriceComponent = this.repository.findMaximumRetailPriceComponent();
	    
	    for (int i=0; i<findAverageRetailPriceComponent.size();i++) {
	    	final String[] averageComponent=findAverageRetailPriceComponent.get(i).split(":");
	    	final Pair<String, String> averagePair = Pair.of(averageComponent[0], averageComponent[1]);
	    	averageRetailPriceComponents.put(averagePair, Double.parseDouble(averageComponent[2]));
	    	
	    	final String[] deviationComponent=findDeviationRetailPriceComponent.get(i).split(":");
	    	final Pair<String, String> deviationPair = Pair.of(deviationComponent[0], deviationComponent[1]);
	    	deviationRetailPriceComponents.put(deviationPair, Double.parseDouble(deviationComponent[2]));
	    	
	    	final String[] minimumComponent=findMinimumRetailPriceComponent.get(i).split(":");
	    	final Pair<String, String> minimumPair = Pair.of(minimumComponent[0], minimumComponent[1]);
	    	minimumRetailPriceComponents.put(minimumPair, Double.parseDouble(minimumComponent[2]));
	    	
	    	final String[] maximumComponent=findMaximumRetailPriceComponent.get(i).split(":");
	    	final Pair<String, String> maximumPair = Pair.of(maximumComponent[0], maximumComponent[1]);
	    	maximumRetailPriceComponents.put(maximumPair, Double.parseDouble(maximumComponent[2]));
	    	
	    }
	       

	    final List<String> findAverageRetailPriceTools = this.repository.findAverageRetailPriceTools();
	    final List<String> findDeviationRetailPriceTools = this.repository.findDeviationRetailPriceTools();
	    final List<String> findMinimumRetailPriceTools = this.repository.findMinimumRetailPriceTools();
	    final List<String> findMaximumRetailPriceTools = this.repository.findMaximumRetailPriceTools();
	    
	    for (int i=0; i<findAverageRetailPriceTools.size();i++) {
	    	final String[] averageTools=findAverageRetailPriceTools.get(i).split(":");
	    	averageRetailPriceTools.put(averageTools[0], Double.parseDouble(averageTools[1]));
	    	
	    	final String[] deviationTools=findDeviationRetailPriceTools.get(i).split(":");
	    	deviationRetailPriceTools.put(deviationTools[0], Double.parseDouble(deviationTools[1]));
	    	
	    	final String[] minimumTools=findMinimumRetailPriceTools.get(i).split(":");
	    	minimumRetailPriceTools.put(minimumTools[0], Double.parseDouble(minimumTools[1]));
	    	
	    	final String[] maximumTools=findMaximumRetailPriceTools.get(i).split(":");
	    	maximumRetailPriceTools.put(maximumTools[0], Double.parseDouble(maximumTools[1]));
	    }
	    
	    final List<String> findNumberPatronages = this.repository.findNumberPatronages();
	    final List<String> findAverageBudgetPatronage = this.repository.findAverageBudgetPatronage();
	    final List<String> findDeviationBudgetPatronage = this.repository.findDeviationBudgetPatronage();
	    final List<String> findMinimumBudgetPatronage = this.repository.findMinimumBudgetPatronage();
	    final List<String> findMaximumBudgetPatronage = this.repository.findMaximumBudgetPatronage();
	    
	    for (int i=0; i<findNumberPatronages.size();i++) {
	    	final String[] numberPatronagesSt=findNumberPatronages.get(i).split(":");
	    	numberPatronages.put(PatronageStatus.values()[Integer.parseInt(numberPatronagesSt[0])], Double.parseDouble(numberPatronagesSt[1]));
	    	
	    	final String[] averagePatronages=findAverageBudgetPatronage.get(i).split(":");
	    	averageBudgetPatronage.put(PatronageStatus.values()[Integer.parseInt(averagePatronages[0])], Double.parseDouble(averagePatronages[1]));
	    	
	    	final String[] deviationPatronages=findDeviationBudgetPatronage.get(i).split(":");
	    	deviationBudgetPatronage.put(PatronageStatus.values()[Integer.parseInt(deviationPatronages[0])], Double.parseDouble(deviationPatronages[1]));
	    	
	    	final String[] minimumPatronages=findMinimumBudgetPatronage.get(i).split(":");
	    	minimumBudgetPatronage.put(PatronageStatus.values()[Integer.parseInt(minimumPatronages[0])], Double.parseDouble(minimumPatronages[1]));
	    	
	    	final String[] maximumPatronages=findMaximumBudgetPatronage.get(i).split(":");
	    	maximumBudgetPatronage.put(PatronageStatus.values()[Integer.parseInt(maximumPatronages[0])], Double.parseDouble(maximumPatronages[1]));
	    }
	    

	    result.setNumberComponents(numberComponents);
	    
	    result.setAverageRetailPriceComponents(averageRetailPriceComponents);
	    result.setDeviationRetailPriceComponents(deviationRetailPriceComponents);
	    result.setMinimumRetailPriceComponents(minimumRetailPriceComponents);
	    result.setMaximumRetailPriceComponents(maximumRetailPriceComponents);
	    
	    result.setNumberTools(numberTools);
	    result.setAverageRetailPriceTools(averageRetailPriceTools);
	    result.setDeviationRetailPriceTools(deviationRetailPriceTools);
	    result.setMinimumRetailPriceTools(minimumRetailPriceTools);
	    result.setMaximumRetailPriceTools(maximumRetailPriceTools);
	    
	    result.setNumberPatronages(numberPatronages);;
	    result.setAverageBudgetPatronage(averageBudgetPatronage);
	    result.setDeviationBudgetPatronage(deviationBudgetPatronage);
	    result.setMinimumBudgetPatronage(minimumBudgetPatronage);
	    result.setMaximumBudgetPatronage(maximumBudgetPatronage);
	        
	    return result;
	}


	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("numberComponents", entity.getNumberComponents());
		model.setAttribute("averageRetailPriceComponents", entity.getAverageRetailPriceComponents());
		model.setAttribute("deviationRetailPriceComponents", entity.getDeviationRetailPriceComponents());
		model.setAttribute("minimumRetailPriceComponents", entity.getMinimumRetailPriceComponents());
		model.setAttribute("maximumRetailPriceComponents", entity.getMaximumRetailPriceComponents());
		
		model.setAttribute("numberTools", entity.getNumberTools());
		model.setAttribute("averageRetailPriceTools", entity.getAverageRetailPriceTools());
		model.setAttribute("deviationRetailPriceTools", entity.getDeviationRetailPriceTools());
		model.setAttribute("minimumRetailPriceTools", entity.getMinimumRetailPriceTools());
		model.setAttribute("maximumRetailPriceTools", entity.getMaximumRetailPriceTools());
		
		model.setAttribute("numberPatronages", entity.getNumberPatronages());
		model.setAttribute("averageBudgetPatronage", entity.getAverageBudgetPatronage());
		model.setAttribute("deviationBudgetPatronage", entity.getDeviationBudgetPatronage());
		model.setAttribute("minimumBudgetPatronage", entity.getMinimumBudgetPatronage());
		model.setAttribute("maximumBudgetPatronage", entity.getMaximumBudgetPatronage());
	
	
	
	}

}
