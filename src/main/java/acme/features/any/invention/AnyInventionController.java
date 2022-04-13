package acme.features.any.invention;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.inventions.Invention;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyInventionController extends AbstractController<Any, Invention> {
	
	// Internal state ---------------------------------------------------------
			
		@Autowired
		protected AnyInventionListAllComponentService	listAllComponentService;
		
		@Autowired
		protected AnyInventionShowComponentService			showComponentService;
		
		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showComponentService);
			super.addCommand("list-component","list", this.listAllComponentService);
		}

}
