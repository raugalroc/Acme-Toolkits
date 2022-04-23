package acme.features.patron.patronDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronDashboardRepository  extends AbstractRepository{
	
	@Query("select count(p) from Patronage p where p.patron.id = :id and p.status=acme.entities.patronage.PatronageStatus.PROPOSED")
	Double findNumberProposed(int id);
	
	@Query("select count(p) from Patronage p where p.patron.id = :id and p.status=acme.entities.patronage.PatronageStatus.ACCEPTED")
	Double findNumberAccepted(int id);
	
	@Query("select count(p) from Patronage p where p.patron.id = :id and p.status=acme.entities.patronage.PatronageStatus.DENIED")
	Double findNumberDenied(int id);
	
	
	
	
	@Query("select concat(p.budget.currency, ':', p.status, ':', avg(p.budget.amount)) from Patronage p where p.patron.id = :id group by p.status, p.budget.currency")
	List<String> findAverageBudget(int id);
	
	@Query("select concat(p.budget.currency, ':', p.status, ':', stddev(p.budget.amount)) from Patronage p where p.patron.id = :id group by p.status, p.budget.currency")
	List<String> findDeviationBudget(int id);
	
	@Query("select concat(p.budget.currency, ':', p.status, ':', min(p.budget.amount)) from Patronage p where p.patron.id = :id group by p.status, p.budget.currency")
	List<String> findMinimumBudget(int id);
	
	@Query("select concat(p.budget.currency,':', p.status, ':', max(p.budget.amount)) from Patronage p where p.patron.id = :id group by p.status, p.budget.currency")
	List<String> findMaximumBudget(int id);
	
	
}
