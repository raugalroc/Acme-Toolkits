package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	// Internal state ---------------------------------------------------------

				@Autowired
				protected PatronPatronageRepository repository;
				
	// AbstractShowService<Patron, Patronage> interface --------------
				
				
				@Override
				public boolean authorise(final Request<Patronage> request) {
					assert request != null;

					boolean result;
					int patronageId;
					Patronage patronage;

					patronageId = request.getModel().getInteger("id");
					patronage = this.repository.findOnePatronageById(patronageId);
					result = patronage != null && patronage.getPatron().getId() == request.getPrincipal().getActiveRoleId();

					return result;
				}
				
				@Override
				public Patronage findOne(final Request<Patronage> request) {
					assert request != null;
					Patronage result;
					int patronageId;
					
					patronageId = request.getModel().getInteger("id");
					result = this.repository.findOnePatronageById(patronageId);
					return result;
				}
				
				@Override
				public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
					assert request != null;
					assert entity != null;
					assert model != null;
					
					final Inventor inventor = entity.getInventor();
					final String inventorName = inventor.getUserAccount().getIdentity().getName();
					final String inventorSurname = inventor.getUserAccount().getIdentity().getSurname();
					final String inventorEmail = inventor.getUserAccount().getIdentity().getEmail();
					
					final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
					
					final Money budget = MoneyExchange.of(entity.getBudget(), defaultCurrency).execute().getTarget();
						
					request.unbind(entity, model, "status", "code", "legalStuff", "creationTime", "startTime", "endTime", "link", "published");
					request.unbind(inventor, model, "company");
					
					model.setAttribute("id", entity.getId());
					model.setAttribute("budget", budget);
					model.setAttribute("name", inventorName);
					model.setAttribute("surname", inventorSurname);
					model.setAttribute("email", inventorEmail);
				}

}
