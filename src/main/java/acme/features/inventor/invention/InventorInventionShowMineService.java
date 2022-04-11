package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorInventionShowMineService implements AbstractShowService<Inventor, Invention> {

	// Internal state ------------------------------------------------------------

	@Autowired
	protected InventorInventionRepository 		repository;

	// AbstractShowService<Inventor, Invention> interface ------------------------
	
	@Override
	public boolean authorise(final Request<Invention> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Inventor inventor;
		Principal principal;

		masterId = request.getModel().getInteger("id");
		inventor = this.repository.findOneInventionById(masterId).getInventor();
		principal = request.getPrincipal();
		result = (
			inventor.getUserAccount().getId() == principal.getAccountId()
		);
		
		return result;
	}

	@Override
	public Invention findOne(final Request<Invention> request) {
		assert request != null;
		
		Invention result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneInventionById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Invention> request, final Invention entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "link");
	}
	
}
