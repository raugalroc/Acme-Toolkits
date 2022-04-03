package acme.features.administrator.administratorDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.forms.AdministratorDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

public class AdministratorAdministratorDashboardController extends AbstractController<Administrator, AdministratorDashboard>{

	@Autowired
	protected AdministratorAdministratorDashboardShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
	
	
}
