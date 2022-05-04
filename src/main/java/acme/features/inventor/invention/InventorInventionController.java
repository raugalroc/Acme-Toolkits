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
	protected InventorInventionListMineService		listMineService;
	
	@Autowired
	protected InventorInventionShowMineService		showService;
	
	@Autowired
	protected InventorInventionUpdateService		updateService;
	
	@Autowired
	protected InventorInventionDeleteService		deleteService;
	
	@Autowired
	protected InventorInventionPublishService		publishService;
	
	@Autowired
	protected InventorInventionCreateService		createService;
	
	
	
	// Constructors --------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-mine", "list", this.listMineService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
	}
	
}
