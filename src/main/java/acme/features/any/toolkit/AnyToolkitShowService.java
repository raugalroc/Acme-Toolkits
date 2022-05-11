package acme.features.any.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneToolkitById(id);
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
		final Double totalPrice = this.repository.findManyQuantitiesByToolkitId(entity.getId()).stream()
										.mapToDouble(q -> MoneyExchange.of(MoneyExchange.moneyOf(
																					q.getInvention().getRetailPrice().getAmount() * q.getNumberOfQuantity(), 
																					q.getInvention().getRetailPrice().getCurrency()), 
																			defaultCurrency).execute().getTarget().getAmount())
										.sum();
		
		model.setAttribute("totalPrice", MoneyExchange.moneyOf(totalPrice, defaultCurrency));
		model.setAttribute("id", entity.getId());
		request.unbind(entity, model, "code", "title", "description","assemblyNotes","link");
		
		
	}

}
