package acme.features.any.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyInventionListAllToolService implements AbstractListService<Any, Invention>  {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyInventionRepository repository;
		
	// AbstractListService<Any, Invention> interface --------------
		
		@Override
		public boolean authorise(final Request<Invention> request) {
			assert request != null;

			return true;
		}
		
		@Override
		public Collection<Invention> findMany(final Request<Invention> request) {
			assert request != null;

			Collection<Invention> result;

			result = this.repository.findAllTools();

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
