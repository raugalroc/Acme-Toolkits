package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionType;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorInventionRepository extends AbstractRepository {

	@Query("SELECT i FROM Invention i JOIN i.inventor r WHERE i.inventionType = :type AND r.id = :id")
	Collection<Invention> findAllInventionsByTypeAndInventorId(InventionType type, int id);

	@Query("SELECT i FROM Invention i WHERE i.id = :id")
	Invention findOneInventionById(int id);
	
	@Query("SELECT i FROM Invention i WHERE i.code = :code AND i.inventionType = 'COMPONENT'")
	Invention findOneComponentByCode(String code);
	
	@Query("SELECT i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("SELECT ac.acceptedCurrencies from SystemConfiguration ac")
	String findAcceptedCurrencies();
}
