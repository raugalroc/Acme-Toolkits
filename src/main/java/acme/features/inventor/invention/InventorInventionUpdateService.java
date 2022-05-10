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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorInventionUpdateService implements AbstractUpdateService<Inventor, Invention>{

	@Autowired
	protected InventorInventionRepository repository;
	
	@Override
	public boolean authorise(final Request<Invention> request) {
		assert request != null;
		boolean result;
		int masterId;
		Invention invention;
		Inventor inventor;
		
		masterId=request.getModel().getInteger("id");
		invention=this.repository.findOneInventionById(masterId);
		inventor=invention.getInventor();
		result=invention.getPublished()==false && request.isPrincipal(inventor);
		return result;
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
	public Invention findOne(final Request<Invention> request) {
		assert request != null;
		
		Invention result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findOneInventionById(id);
		
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
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.invention.form.error.code.duplicated");
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
	public void update(final Request<Invention> request, final Invention entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
