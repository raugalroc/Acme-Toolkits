
package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select pat from Patronage pat where pat.inventor.id = :inventorId and pat.id = :masterId")
	Patronage findOnePatronageByInventorId(int masterId, int inventorId);
	@Query("select pat from Patronage pat where pat.inventor.id = :inventorId")
	Collection<Patronage> findPatronagesByInventorId(int inventorId);
	@Query("select pat from Patronage pat where pat.id = :masterId")
	Patronage findOnePatronageById(int masterId);
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();
}
