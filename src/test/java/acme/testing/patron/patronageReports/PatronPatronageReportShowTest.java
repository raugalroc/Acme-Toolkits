package acme.testing.patron.patronageReports;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestPatronageReportsAsPatron(final int recordIndex, final String sequenceNumber, final String creationMoment, 
		final String memorandum, final String link, final String patronageCode) {
		
		super.signIn("patron2", "patron2");

		super.clickOnMenu("Patron", "List my Patronage Reports");
		
	
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronage.code", patronageCode);
		
		

		super.signOut();
	}
}
