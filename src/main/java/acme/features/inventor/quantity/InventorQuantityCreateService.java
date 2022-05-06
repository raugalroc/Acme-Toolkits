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
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractCreateService<Inventor, Quantity> interface -------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		result = (toolkit != null && !toolkit.getPublished() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int masterId;
		Toolkit toolkit;
		Invention invention;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		invention = new Invention();

		result = new Quantity();
		result.setToolkit(toolkit);
		result.setInvention(invention);
		
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final String inventionCode;
		final Invention invention;
		
		inventionCode = request.getModel().getString("invention.code");
		invention = this.repository.findOneInventionByCode(inventionCode);
		
		entity.setInvention(invention);
		request.bind(entity, errors, "numberOfQuantity", "invention.code");
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("invention.code")) {
			final Invention invention;
			final boolean exists;
			
			invention = entity.getInvention();
			exists = invention.getId() != 0;
			
			errors.state(request, exists, "invention.code", "inventor.quantity.form.error.invalid");
			if (exists)	errors.state(request, this.repository.countByInventionIdAndToolkitId(invention.getId(), entity.getToolkit().getId()) == 0, "invention.code", "inventor.quantity.form.error.duplicated");
		}
		
		if (!errors.hasErrors("numberOfQuantity")) {
			final Invention invention;
			final boolean exists;
			final Boolean isComponent;
			
			invention = entity.getInvention();
			exists = invention.getId() != 0;
			isComponent = exists && invention.getInventionType().equals(InventionType.COMPONENT);
			
			if (Boolean.TRUE.equals(isComponent)) errors.state(request, entity.getNumberOfQuantity() == 1, "numberOfQuantity", "inventor.quantity.form.error.exceed");
		}
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfQuantity", "invention.code");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("published", entity.getToolkit().getPublished());
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
