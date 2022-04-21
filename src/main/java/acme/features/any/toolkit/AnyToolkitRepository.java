package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Toolkit t WHERE t.published = true")
	Collection<Toolkit> findPublishedToolkits();
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :id")
	Toolkit findOneToolkitById(int id);

}
