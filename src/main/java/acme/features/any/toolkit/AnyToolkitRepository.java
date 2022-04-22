package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Toolkit t WHERE t.published = true")
	Collection<Toolkit> findPublishedToolkits();
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("SELECT q.invention  FROM Quantity q WHERE q.toolkit.id= :toolkitId")
	Collection<Invention> findManyInventionsByToolkitId(int toolkitId);

	@Query("SELECT sum(q.invention.retailPrice.amount*q.quantity) FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Double findTotalRetailPriceByToolkitId(int toolkitId);

}
