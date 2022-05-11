package acme.features.any.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyInventionRepository extends AbstractRepository{
	
	@Query("select i from Invention i WHERE i.inventionType = 0 and i.published = true")
	Collection<Invention> findAllComponents();
	
	@Query("select q.invention from Quantity q  WHERE q.toolkit.id = :toolkitId")
	Collection<Invention> findAllComponentsByToolkitId(int toolkitId);
	
	@Query("select i from Invention i WHERE i.id = :id")
	Invention findOneInventionById(int id);
	
	@Query("select i from Invention i WHERE i.inventionType = 1 and i.published = true")
	Collection<Invention> findAllTools();
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
