package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inventions.Invention;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	//select q.invention_id, sum(q.quantity*i.retail_price_amount) from quantity 
	//q join invention i on (i.id=q.invention_id)  where q.toolkit_id =39 group by q.invention_id  ;
	
	@Query("SELECT q.toolkit FROM Quantity q WHERE q.invention.inventor.id = :inventorId")
	Collection<Toolkit> findManyToolkitsByInventorId(int inventorId);
	@Query("SELECT t FROM Toolkit t WHERE t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	@Query("SELECT q.invention  FROM Quantity q WHERE q.toolkit.id= :toolkitId")
	Collection<Invention> findManyInventionsByToolkitId(int toolkitId);
	@Query("SELECT sum(q.invention.retailPrice.amount*q.quantity) FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Double findTotalRetailPriceByToolkitId(int toolkitId);
//	@Query("select q.invention.inventor FROM Quantity q where q.toolkit.id=:toolkitId")
//	Inventor findOneInventorByToolkitId(int toolkitId);
	

}
