package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractShowService<Inventor, Quantity> interface ---------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int quantityId;
		final Toolkit toolkit;

		quantityId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitByQuantityId(quantityId);
		result = (toolkit != null && (toolkit.getPublished() || request.isPrincipal(toolkit.getInventor())));

		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfQuantity", "toolkit.code", "invention.code");
		model.setAttribute("masterId", entity.getToolkit().getId());
		model.setAttribute("published", entity.getToolkit().getPublished());
	}

}
