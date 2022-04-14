package acme.features.any.patron;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;
import acme.roles.Patron;

@Controller
public class AnyPatronController extends AbstractController<Any, Patron>{

	@Autowired
	protected AnyPatronListService 	listService;
	
	@Autowired
	protected AnyPatronShowService 	showService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
