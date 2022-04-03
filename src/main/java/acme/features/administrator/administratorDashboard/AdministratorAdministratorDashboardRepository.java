package acme.features.administrator.administratorDashboard;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAdministratorDashboardRepository extends AbstractRepository {

	
	@Query("select count(c) from Inventions c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT")
	Double findNumberComponents();
	
	@Query("select avg(c.retailPrice) from Inventions c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT group by c.technology and c.retailPrice.currency")
	Map<Pair<String, String>, Double> findAverageRetailPriceComponent();
	
	
}
