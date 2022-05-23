package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractListService<Inventor, Quantity> interface ---------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int masterId;
		final Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		result = (toolkit != null && (toolkit.getPublished() || request.isPrincipal(toolkit.getInventor())));

		return result;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		Collection<Quantity> result;
		int masterId;

		masterId = request.getModel().getInteger("masterId");
		result = this.repository.findManyQuantitiesByMasterId(masterId);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int masterId;
		Toolkit toolkit;
		final boolean showCreate;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		showCreate = (!toolkit.getPublished() && request.isPrincipal(toolkit.getInventor()));

		model.setAttribute("masterId", masterId);
		model.setAttribute("showCreate", showCreate);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
		
		final Money retailPrice = MoneyExchange.of(entity.getInvention().getRetailPrice(), defaultCurrency).execute().getTarget();
		
		model.setAttribute("invention.retailPrice", retailPrice);
		request.unbind(entity, model, "numberOfQuantity", "invention.code", "invention.name", "invention.technology");
	}

}
