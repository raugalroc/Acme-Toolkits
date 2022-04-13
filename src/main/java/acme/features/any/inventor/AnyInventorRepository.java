package acme.features.any.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface AnyInventorRepository extends AbstractRepository{
	@Query("select i from Inventor i join i.userAccount u where u.enabled = true")
	Collection<Inventor> findInventorAvailable();

	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(final int id);
}
