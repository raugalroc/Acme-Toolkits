package acme.features.inventor.invention;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.inventions.Invention;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorInventionController extends AbstractController<Inventor, Invention> {

	// Internal state ------------------------------------------------------------
	
	@Autowired
	protected InventorInventionListMineService		listService;
	
	@Autowired
	protected InventorInventionShowMineService		showService;
	
	// Constructors --------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		
		super.addCommand("list-mine", "list", this.listService);
	}
	
}
