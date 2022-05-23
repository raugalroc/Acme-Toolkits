package acme.features.inventor.patronageReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReports.PatronageReport;
import acme.entities.toolkits.Toolkit;
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

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		final Inventor inventor;

//		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		result = new PatronageReport();
//		result.setPublished(false);
//		result.setInventor(inventor);

		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			final Toolkit existing;

//			existing = this.repository.findOneToolkitByCode(entity.getCode());
//			errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
		}
		
		{
			final Boolean isSpam;
			
//			isSpam = entity.isSpam(this.repository.getSystemConfiguration());
			
//			errors.state(request, !isSpam, "*", "inventor.toolkit.form.error.spam");
		}

	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
