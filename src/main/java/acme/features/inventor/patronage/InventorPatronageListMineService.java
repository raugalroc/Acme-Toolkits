package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageListMineService implements AbstractListService<Inventor,Patronage>{
	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request!=null;
		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request!=null;
		final Integer id = request.getPrincipal().getActiveRoleId();
		return this.repository.findPatronagesByInventorId(id);
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
		
		final Money budget = MoneyExchange.of(entity.getBudget(), defaultCurrency).execute().getTarget();
		
		model.setAttribute("budget", budget);
		request.unbind(entity, model, "status", "legalStuff", "creationTime", "link");
	}
	
}
