
package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAnnouncementShowService implements AbstractShowService<Authenticated, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnouncementRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	/*@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int id;
		Job job;

		id = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(id);
		result = !job.isDraftMode();

		return result;
	}*/
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		boolean result = true;
		Calendar calendar;
		Date deadline;
		final int id = request.getModel().getInteger("id");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		result=this.repository.findOneAnnouncementById(id).getCreationMoment().after(deadline);
		return result;
	}

	@Override
	public Announcement findOne(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAnnouncementById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String isCritical = "yes", isNotCritical="no";
		final String criticName = "isCritic";
		request.unbind(entity, model, "creationMoment", "title", "body", "critic", "link");
		model.setAttribute(criticName, Boolean.TRUE.equals(entity.getCritic())?isCritical:isNotCritical);
		model.setAttribute("confirmation",false);
		model.setAttribute("readonly", false);
	}

}
