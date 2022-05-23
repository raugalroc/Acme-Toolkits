package acme.features.inventor.patronageReports;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReports.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractCreateService<Inventor, PatronageReport> interface -------------------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result = false;
		int patronageId;
		int inventorId;
		int roleId;
		final Patronage patronage;

		patronageId = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(patronageId);
		
		if(patronage != null) {
			inventorId = patronage.getInventor().getId();
			roleId = request.getPrincipal().getActiveRoleId();
			
			result = inventorId == roleId;	
		}
		
		return result;
	}
	
	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "sequenceNumber", "creationMoment", "memorandum", "link");
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "link");
		final Patronage patronage = this.repository.findOnePatronageById(request.getModel().getInteger("masterId"));
		model.setAttribute("patronage.code", patronage.getCode());
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		model.setAttribute("patronageId", request.getModel().getInteger("masterId").toString());
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Patronage patronage;
		Date moment;
		result = new PatronageReport();
		patronage = this.repository.findOnePatronageById(request.getModel().getInteger("masterId"));

		result.setPatronage(patronage);
		
		final List<String> sequenceNumbers = this.repository.findAllSequenceNumberByPatronage(request.getModel().getInteger("masterId"));
		if(sequenceNumbers.isEmpty()) {
			result.setSequenceNumber(patronage.getCode() + ":0001");
		} else {
			Integer max = Integer.MIN_VALUE;
			for(final String sn : sequenceNumbers) {
				final Integer serialNumber = Integer.valueOf(sn.split(":")[1]);
				if (serialNumber > max) max = serialNumber;
			}
			max++;
			result.setSequenceNumber(patronage.getCode() + String.format(":%4s", max.toString()).replace(' ', '0'));
		}
		
		result.setMemorandum("");
		result.setLink("");
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		{
			boolean confirmation;
	
			confirmation = request.getModel().getBoolean("confirmation");
			errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		}
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
