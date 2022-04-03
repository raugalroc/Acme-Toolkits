package acme.features.administrator.administratorDashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.forms.AdministratorDashboard;
import acme.forms.Status;
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
		
	    final Map<Pair<String, String>, Double>    averageRetailPriceComponents=this.repository.findAverageRetailPriceComponent();
	    final Map<Pair<String, String>, Double>    deviationRetailPriceComponents;
	    final Map<Pair<String, String>, Double>    minimumRetailPriceComponents;
	    final Map<Pair<String, String>, Double>    maximumRetailPriceComponents;
	    
	    final Double                               numberTools;
	    
	    final Map<String, Double>                  averageRetailPriceTools;
	    final Map<String, Double>                  deviationRetailPriceTools;
	    final Map<String, Double>                  minimumRetailPriceTools;
	    final Map<String, Double>                  maximumRetailPriceTools;
	    
	    final Double             	                 numberPatronages;
	    
	    final Map<Status, Double>         averageBudgetPatronage;
	    final Map<Status, Double>         deviationBudgetPatronage;
	    final Map<Status, Double>         minimumBudgetPatronage;
	    final Map<Status, Double>         maximumBudgetPatronage;
	    
	    final AdministratorDashboard result = new AdministratorDashboard();
	    
	    result.setNumberComponents(numberComponents);
	    result.setAverageRetailPriceComponents(averageRetailPriceComponents);
	    
	    return result;
	}


	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

}
