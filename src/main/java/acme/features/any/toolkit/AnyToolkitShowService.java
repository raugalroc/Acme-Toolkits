package acme.features.any.toolkit;

import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

}
