package acme.features.inventor.invention;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inventions.Invention;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorInventionCreateService implements AbstractCreateService<Inventor, Invention>{

	@Autowired
	protected InventorInventionRepository repository;

	@Override
	public boolean authorise(final Request<Invention> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Invention> request, final Invention entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "code", "name", "technology", "description", "retailPrice", "link", "inventionType", "published");
	
	}

	@Override
	public void unbind(final Request<Invention> request, final Invention entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "link", "inventionType", "published");
	
		
	}

	@Override
	public Invention instantiate(final Request<Invention> request) {
		assert request != null;
		
		final Invention result;
		final Inventor inventor;
		
		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		result = new Invention();
		result.setCode("");
		result.setDescription("");
		result.setInventor(inventor);
		result.setName("");
		result.setPublished(false);
		result.setTechnology("");

		return result;
	}

	@Override
	public void validate(final Request<Invention> request, final Invention entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Invention existing;
			
			existing=this.repository.findOneComponentByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.invention.form.error.code.duplicated");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			final Set<String> acceptedCurrencies;
			final String[] acceptedCurrenciesSt=this.repository.findAcceptedCurrencies().split(";");
			acceptedCurrencies=new HashSet<String>();
			Collections.addAll(acceptedCurrencies, acceptedCurrenciesSt);
			
			errors.state(request, entity.getRetailPrice().getAmount()>0., "retailPrice", "inventor.invention.form.error.retailPrice.negative");
			
			errors.state(request, acceptedCurrencies.contains(entity.getRetailPrice().getCurrency()) , "retailPrice", "inventor.invention.form.error.retailPrice.invalid");
		}
		
		{
			Boolean isSpam;
			
			isSpam = entity.isSpam(this.repository.getSystemConfiguration());
			
			errors.state(request, !isSpam, "*", "inventor.invention.form.error.spam");
		}

	}

	@Override
	public void create(final Request<Invention> request, final Invention entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	
}
