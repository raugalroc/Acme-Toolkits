package acme.features.administrator.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorSystemConfigurationController extends AbstractController<Administrator, SystemConfiguration> {

	// Internal state ------------------------------------------------------------
	
	@Autowired
	protected AdministratorSystemConfigurationShowService		showService;
	
	// Constructors --------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
	
}
