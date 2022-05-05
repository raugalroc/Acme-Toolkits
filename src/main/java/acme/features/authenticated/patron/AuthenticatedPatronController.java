package acme.features.authenticated.patron;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Patron;

@Controller
public class AuthenticatedPatronController extends AbstractController<Authenticated, Patron>{
	
	@Autowired
	protected AuthenticatedPatronCreateService createService;

	
	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
	}
}
