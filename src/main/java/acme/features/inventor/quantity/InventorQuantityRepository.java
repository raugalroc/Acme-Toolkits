package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.quantity.Quantity;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository {

	@Query("SELECT t FROM Toolkit t WHERE t.id = ?1")
	Toolkit findOneToolkitById(int masterId);

	@Query("SELECT q from Quantity q WHERE q.toolkit.id = ?1")
	Collection<Quantity> findManyQuantitiesByMasterId(int masterId);

	@Query("SELECT q.toolkit FROM Quantity q WHERE q.id = ?1")
	Toolkit findOneToolkitByQuantityId(int quantityId);

	@Query("SELECT q FROM Quantity q WHERE q.id = ?1")
	Quantity findOneQuantityById(int id);

	@Query("SELECT i FROM Invention i WHERE i.code = ?1")
	Invention findOneInventionByCode(String inventionCode);

	@Query("SELECT COUNT(*) FROM Quantity q WHERE q.invention.id = ?1 AND q.toolkit.id = ?2")
	int countByInventionIdAndToolkitId(int inventionId, int toolkitId);

	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
