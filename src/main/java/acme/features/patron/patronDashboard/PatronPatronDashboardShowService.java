package acme.features.patron.patronDashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{
	
	@Autowired
	protected PatronPatronDashboardRepository repository;
	
	
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		int patronId;
		patronId = request.getPrincipal().getActiveRoleId();
		
		final Double						       numberProposed = this.repository.findNumberProposed(patronId);
		final Double						       numberAccepted = this.repository.findNumberAccepted(patronId);
		final Double						       numberDenied = this.repository.findNumberDenied(patronId);
		
	    final Map<Pair<String, PatronageStatus>, Double>    averageBudget = new HashMap<>();
	    final Map<Pair<String, PatronageStatus>, Double>   deviationBudget = new HashMap<>();
	    final Map<Pair<String, PatronageStatus>, Double>    minimumBudget = new HashMap<>();
	    final Map<Pair<String, PatronageStatus>, Double>    maximumBudget = new HashMap<>();

	    
	    final PatronDashboard result = new PatronDashboard();
	    
	    
	    final List<String> findAverageBudget = this.repository.findAverageBudget(patronId);
	    final List<String> findDeviationBudget = this.repository.findDeviationBudget(patronId);
	    final List<String> findMinimumBudget = this.repository.findMinimumBudget(patronId);
	    final List<String> findMaximumBudget = this.repository.findMaximumBudget(patronId);
	    
	    
	    for (int i=0; i<findAverageBudget.size();i++) {
	    	final String[] averagePatronage = findAverageBudget.get(i).split(":");
	    	final Pair<String, PatronageStatus> averagePair = Pair.of(averagePatronage[0], PatronageStatus.values()[Integer.parseInt(averagePatronage[1])]);
	    	averageBudget.put(averagePair, Double.parseDouble(averagePatronage[2]));
	    	
	    	final String[] deviationPatronage=findDeviationBudget.get(i).split(":");
	    	final Pair<String, PatronageStatus> deviationPair = Pair.of(deviationPatronage[0], PatronageStatus.values()[Integer.parseInt(deviationPatronage[1])]);
	    	deviationBudget.put(deviationPair, Double.parseDouble(deviationPatronage[2]));
	    	
	    	final String[] minimumPatronage=findMinimumBudget.get(i).split(":");
	    	final Pair<String, PatronageStatus> minimumPair = Pair.of(minimumPatronage[0], PatronageStatus.values()[Integer.parseInt(minimumPatronage[1])]);
	    	minimumBudget.put(minimumPair, Double.parseDouble(minimumPatronage[2]));
	    	
	    	final String[] maximumPatronage=findMaximumBudget.get(i).split(":");
	    	final Pair<String, PatronageStatus> maximumPair = Pair.of(maximumPatronage[0], PatronageStatus.values()[Integer.parseInt(maximumPatronage[1])]);
	    	maximumBudget.put(maximumPair, Double.parseDouble(maximumPatronage[2]));
	    	
	    }


	   
	    result.setNumberProposed(numberProposed);
	    result.setNumberAccepted(numberAccepted);
	    result.setNumberDenied(numberDenied);
	    
	    result.setAverageBudget(averageBudget);
	    result.setDeviationBudget(deviationBudget);
	    result.setMinimumBudget(minimumBudget);
	    result.setMaximumBudget(maximumBudget);
	        
	    return result;
	}


	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("numberProposed", entity.getNumberProposed());
		model.setAttribute("numberAccepted", entity.getNumberAccepted());
		model.setAttribute("numberDenied", entity.getNumberDenied());
		
		model.setAttribute("averageBudget", entity.getAverageBudget());
		model.setAttribute("deviationBudget", entity.getDeviationBudget());
		model.setAttribute("minimumBudget", entity.getMinimumBudget());
		model.setAttribute("maximumBudget", entity.getMaximumBudget());
		
	}
}
