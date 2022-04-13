
package acme.features.any.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class AnyInventorShowService implements AbstractShowService<Any, Inventor>{
	@Autowired
	protected AnyInventorRepository	repository;
	
	
	@Override
	public boolean authorise(final Request<Inventor> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Inventor findOne(final Request<Inventor> request) {
		assert request != null;
		
		Inventor result;
		int id;
		
		id=request.getModel().getInteger("id");
		result = this.repository.findOneInventorById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Inventor> request, final Inventor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "username", "company", "statement", "link", "name", "surname", "email");
		model.setAttribute("username", entity.getUserAccount().getUsername());
		model.setAttribute("name", entity.getUserAccount().getIdentity().getName());
		model.setAttribute("surname", entity.getUserAccount().getIdentity().getSurname());
		model.setAttribute("email", entity.getUserAccount().getIdentity().getEmail());
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		
	}
}
