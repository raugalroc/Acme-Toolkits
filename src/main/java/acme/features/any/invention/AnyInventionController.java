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
		protected AnyInventionShowService			showService;
		
		@Autowired
		protected AnyInventionListAllToolService	listAllToolService;
		
		@Autowired
		protected AnyInventionListAllByToolkitService listAllByToolkitService;
		
		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
			super.addCommand("list-component","list", this.listAllComponentService);
			super.addCommand("list-tool","list", this.listAllToolService);
			super.addCommand("list-invention-toolkit",  "list", this.listAllByToolkitService);
		}

}
