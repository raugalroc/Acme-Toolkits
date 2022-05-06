package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.quantity.Quantity;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Toolkit t WHERE t.inventor.id = :inventorId")
	Collection<Toolkit> findManyToolkitsByInventorId(int inventorId);
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("SELECT q.invention  FROM Quantity q WHERE q.toolkit.id= :toolkitId")
	Collection<Invention> findManyInventionsByToolkitId(int toolkitId);
	
	@Query("SELECT sum(q.invention.retailPrice.amount*q.numberOfQuantity) FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Double findTotalRetailPriceByToolkitId(int toolkitId);
	
	@Query("SELECT i FROM Inventor i WHERE i.id = ?1")
	Inventor findOneInventorById(int id);

	@Query("SELECT t FROM Toolkit t WHERE t.code = ?1")
	Toolkit findOneToolkitByCode(String code);

	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = ?1")
	Collection<Quantity> findManyQuantitiesByToolkitId(int id);

	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();
	
}
