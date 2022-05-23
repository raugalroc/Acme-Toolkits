package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowMineService implements AbstractShowService<Inventor, Patronage>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorPatronageRepository repository;

		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;
			int idInventor, idPatronage;
			idInventor = request.getPrincipal().getActiveRoleId();
			idPatronage = request.getModel().getInteger("id");
			return this.repository.findOnePatronageById(idPatronage).getInventor().getId()==idInventor;
			
		}

		@Override
		public Patronage findOne(final Request<Patronage> request) {
			assert request != null;	
			return this.repository.findOnePatronageById(request.getModel().getInteger("id"));
		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("patronageId", entity.getId());


			final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
			
			final Money budget = MoneyExchange.of(entity.getBudget(), defaultCurrency).execute().getTarget();
			
			model.setAttribute("budget", budget);
			request.unbind(entity, model, "status", "legalStuff", "patron.userAccount.username", "creationTime", "startTime", "endTime", "link");
		}
}
