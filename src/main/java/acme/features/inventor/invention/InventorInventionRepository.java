package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorInventionRepository extends AbstractRepository {

	@Query("SELECT i FROM Invention i WHERE i.inventionType = :type")
	Collection<Invention> findAllInventionsByType(InventionType type);

	@Query("SELECT i FROM Invention i WHERE i.id = :id")
	Invention findOneInventionById(int id);
	
}
