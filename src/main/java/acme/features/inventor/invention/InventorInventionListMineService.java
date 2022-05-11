package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionType;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorInventionListMineService implements AbstractListService<Inventor, Invention> {

	// Internal state ------------------------------------------------------------

	@Autowired
	protected InventorInventionRepository 		repository;

	// AbstractListService<Inventor, Invention> interface ------------------------
	
	@Override
	public boolean authorise(final Request<Invention> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Invention> findMany(final Request<Invention> request) {
		assert request != null;
		
		Collection<Invention> result;
		Principal principal;
		InventionType type;
		
		try {
			type = InventionType.valueOf(request.getModel().getAttribute("type", String.class).toUpperCase());
		} catch (final Throwable e) {
			type = null;
		}
		principal = request.getPrincipal();
		result = this.repository.findAllInventionsByTypeAndInventorId(type, principal.getActiveRoleId());
		
		return result;
	}

	@Override
	public void unbind(final Request<Invention> request, final Invention entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
		
		final Money retailPrice = MoneyExchange.of(entity.getRetailPrice(), defaultCurrency).execute().getTarget();
		
		model.setAttribute("retailPrice", retailPrice);
		request.unbind(entity, model, "code", "name", "published", "technology");
	}
	
}
