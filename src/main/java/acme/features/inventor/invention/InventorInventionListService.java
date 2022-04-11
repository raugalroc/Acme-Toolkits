package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorInventionListService implements AbstractListService<Inventor, Invention> {

	// Internal state ------------------------------------------------------------

	@Autowired
	protected InventorInventionRepository 		repository;

	// AbstractShowService<Inventor, Invention> interface ------------------------
	
	@Override
	public boolean authorise(final Request<Invention> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Invention> findMany(final Request<Invention> request) {
		assert request != null;
		
		Collection<Invention> result;
		InventionType type;
		
		try {
			type = InventionType.valueOf(request.getModel().getAttribute("type", String.class).toUpperCase());
		} catch (final Throwable e) {
			type = null;
		} 
		result = this.repository.findAllInventionsByType(type);
		
		return result;
	}

	@Override
	public void unbind(final Request<Invention> request, final Invention entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "technology", "retailPrice");
	}
	
}
