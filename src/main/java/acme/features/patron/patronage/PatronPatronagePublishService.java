package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		
		assert request != null;
		boolean result;
		int patronageId;
		Patronage patronage;
		Patron patron;
		
		patronageId = request.getModel().getInteger("id");
		System.out.println(patronageId);
		patronage = this.repository.findPatronageById(patronageId);
		System.out.println(patronage.toString());
		patron = patronage.getPatron();
		result = patronage.getPublished()==false && request.isPrincipal(patron);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
				
		request.bind(entity, errors,"id", "code", "legalStuff", "budget", "startTime", "endTime", "link");	
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		
		request.unbind(entity, model, "id", "code", "legalStuff", "budget", "startTime", "endTime", "link", "published");
		model.setAttribute("inventors", this.repository.findAllInventors());
//		model.setAttribute("id", entity.getId());
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage patronage;
		int id;
		
		id = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(id);
		
		return patronage;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Patronage exists;
			
			exists = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, exists== null || exists.getId()==entity.getId(), "code", "patronage.patronage.form.error.duplicated");
		}
		
		if(!errors.hasErrors("startTime")) {
			Calendar calendar;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			errors.state(request, entity.getStartTime().after(calendar.getTime()), "startTime", "patron.patronage.form.error.startTime");
		}
		
		if(!errors.hasErrors("finishTime")) {
			Calendar calendar;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartTime());
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			errors.state(request, entity.getEndTime().after(calendar.getTime()), "finishTime", "patron.patronage.form.error.finishTime");
		}
		
		if (!errors.hasErrors("budget")) {
			final Set<String> acceptedCurrencies;
			final String[] acceptedCurrenciesSt=this.repository.findAcceptedCurrencies().split(";");
			acceptedCurrencies=new HashSet<String>();
			Collections.addAll(acceptedCurrencies, acceptedCurrenciesSt);
			
			errors.state(request, entity.getBudget().getAmount()>0., "budget", "patronage.patronage.form.error.budget.negative");
			
			errors.state(request, acceptedCurrencies.contains(entity.getBudget().getCurrency()) , "budget", "patronage.patronage.form.error.budget.invalid");
		}
	
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);
		
		
	}

}
