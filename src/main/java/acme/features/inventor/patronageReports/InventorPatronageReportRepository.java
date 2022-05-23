package acme.features.inventor.patronageReports;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReports.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	
	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findAllPatronageReportsByInventorId(int id);
	
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select pat from Patronage pat where pat.id = :masterId")
	Patronage findOnePatronageById(int masterId);
	
	@Query("SELECT pr.sequenceNumber FROM PatronageReport pr WHERE pr.patronage.id = :patronageId")
	List<String> findAllSequenceNumberByPatronage(int patronageId);

}
