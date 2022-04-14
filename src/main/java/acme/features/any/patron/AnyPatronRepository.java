package acme.features.any.patron;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface AnyPatronRepository extends AbstractRepository{

	@Query("select p from Patron p join p.userAccount u where u.enabled = true")
	Collection<Patron> findPatronAvailable();

	@Query("select p from Patron p where p.id = :id")
	Patron findOnePatronById(final int id);
	
}
