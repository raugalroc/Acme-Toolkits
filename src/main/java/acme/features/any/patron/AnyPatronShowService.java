package acme.features.any.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class AnyPatronShowService implements AbstractShowService<Any, Patron>{

	@Autowired
	protected AnyPatronRepository	repository;
	
	
	@Override
	public boolean authorise(final Request<Patron> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Patron findOne(final Request<Patron> request) {
		assert request != null;
		
		Patron result;
		int id;
		
		id=request.getModel().getInteger("id");
		result = this.repository.findOnePatronById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patron> request, final Patron entity, final Model model) {
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
