package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestPatronageAsPatron(final int recordIndex, final String status, final String code, 
		final String legalStuff, final String budget, final String creationTime, final String startTime,
		final String endTime, final String link) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage List");

		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationTime", creationTime);
		super.checkInputBoxHasValue("startTime", startTime);
		super.checkInputBoxHasValue("endTime", endTime);
		super.checkInputBoxHasValue("link", link);
		
		

		super.signOut();
	}

}
