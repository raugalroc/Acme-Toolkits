package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	@Query("SELECT p FROM Patronage p WHERE p.patron.id = :patronId")
	Collection<Patronage> findPatronagesByPatron(Integer patronId);
	
	@Query("SELECT p FROM Patronage p WHERE p.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();
	
	@Query("SELECT patron from Patron patron where patron.id = :id")
	Patron findPatronById(int id);
	
	@Query("SELECT p FROM Patronage p WHERE p.code = :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("SELECT ac.acceptedCurrencies from SystemConfiguration ac")
	String findAcceptedCurrencies();
	
	@Query("SELECT inventor from Inventor inventor where inventor.userAccount.username = :username")
	Inventor findInventorByUsername(String username);
	
	@Query("SELECT inventor from Inventor inventor")
	List<Inventor> findAllInventors();
	
	@Query("SELECT patronage from Patronage patronage where patronage.id = :id")
	Patronage findPatronageById(int id);

}
