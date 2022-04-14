package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	// Internal state ---------------------------------------------------------

				@Autowired
				protected PatronPatronageRepository repository;
				
	// AbstractShowService<Patron, Patronage> interface --------------
				
				
				@Override
				public boolean authorise(final Request<Patronage> request) {
					assert request != null;

					boolean result;
					int patronageId;
					Patronage patronage;

					patronageId = request.getModel().getInteger("id");
					patronage = this.repository.findOnePatronageById(patronageId);
					result = patronage != null && patronage.getPatron().getId() == request.getPrincipal().getActiveRoleId();

					return result;
				}
				
				@Override
				public Patronage findOne(final Request<Patronage> request) {
					assert request != null;
					Patronage result;
					int patronageId;
					
					patronageId = request.getModel().getInteger("id");
					result = this.repository.findOnePatronageById(patronageId);
					return result;
				}
				
				@Override
				public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
					assert request != null;
					assert entity != null;
					assert model != null;
					
					request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationTime", "startTime", "endTime", "link");
					model.setAttribute("inventorId", entity.getInventor().getUserAccount().getId());
					
			
				}


}
