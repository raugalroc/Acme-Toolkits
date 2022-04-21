package acme.features.authenticated.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedSystemConfigurationController extends AbstractController<Authenticated, SystemConfiguration> {
	
	// Internal state ------------------------------------------------------------
	
	@Autowired
	AuthenticatedSystemConfigurationShowService 	showService;
	
	// Constructors --------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
}
