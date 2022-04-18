
package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {

	@Query("select an from Announcement an where an.id = :id")
	Announcement findOneAnnouncementById(int id);
	@Query("select an from Announcement an where an.creationMoment > :date")
	Collection<Announcement> findRecentAnnouncement(Date date);

}
