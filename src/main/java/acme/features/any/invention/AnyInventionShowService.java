package acme.features.any.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class AnyInventionShowService implements AbstractShowService<Any, Invention> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected AnyInventionRepository repository;
			
	// AbstractShowService<Any, Invention> interface --------------
			
			@Override
			public boolean authorise(final Request<Invention> request) {
				assert request != null;

				return true;
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
				
				final Inventor inventor = entity.getInventor();
				
				final String inventorName = inventor.getUserAccount().getIdentity().getName();
				final String inventorSurname = inventor.getUserAccount().getIdentity().getSurname();
				final String inventorEmail = inventor.getUserAccount().getIdentity().getEmail();

				final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
				
				final Money retailPrice = MoneyExchange.of(entity.getRetailPrice(), defaultCurrency).execute().getTarget();
				
				model.setAttribute("retailPrice", retailPrice);
				request.unbind(entity, model, "code", "name", "technology", "description", "link");
				model.setAttribute("inventorName", inventorName);
				model.setAttribute("surname", inventorSurname);
				model.setAttribute("email", inventorEmail);
			}

}
