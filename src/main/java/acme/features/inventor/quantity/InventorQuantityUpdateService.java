package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractUpdateService<Inventor, Quantity> ------------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int quantityId;
		Toolkit toolkit;

		quantityId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitByQuantityId(quantityId);
		result = (toolkit != null && !toolkit.getPublished() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "numberOfQuantity", "invention.code");
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("numberOfQuantity")) {
			final Invention invention;
			final Boolean isComponent;
			
			invention = entity.getInvention();
			isComponent = invention.getInventionType().equals(InventionType.COMPONENT);
			
			if (Boolean.TRUE.equals(isComponent)) errors.state(request, entity.getNumberOfQuantity() == 1, "numberOfQuantity", "inventor.quantity.form.error.exceed");
		}
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfQuantity", "invention.code");
		model.setAttribute("masterId", entity.getToolkit().getId());
		model.setAttribute("published", entity.getToolkit().getPublished());
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
