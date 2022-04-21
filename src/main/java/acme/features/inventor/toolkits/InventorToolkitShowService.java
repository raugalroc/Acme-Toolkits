package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service

public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
//		boolean result;
//		int idInventor, idToolkit;
//		idInventor = request.getPrincipal().getActiveRoleId();
//		idToolkit = request.getModel().getInteger("id");
//		final Toolkit t = this.repository.findOneToolkitById(idToolkit);
//		final Inventor myInventor = this.repository.findOneInventorByToolkitId(t.getId());
//		result = myInventor.getId()==idInventor;
		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneToolkitById(id);
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final Double totalPrice = this.repository.findTotalRetailPriceByToolkitId(entity.getId());
		final Collection<Invention> inventions = this.repository.findManyInventionsByToolkitId(entity.getId());
		//TODO sumar todos los precios en base a la moneda actual que tiene el usuario asignada.
		model.setAttribute("totalPrice", totalPrice);
		model.setAttribute("id", entity.getId());
		request.unbind(entity, model, "code", "title", "description","assemblyNotes","link");
		

		
	}
	

}
