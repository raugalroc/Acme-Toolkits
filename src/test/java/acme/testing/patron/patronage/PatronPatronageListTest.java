package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestPatronageAsPatron(final int recordIndex, final String status, final String code, 
		final String legalStuff, final String budget, final String creationTime, final String startTime,
		final String endTime) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage List");
		super.checkListingExists();
	
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, legalStuff);
		super.checkColumnHasValue(recordIndex, 4, creationTime);
		super.checkColumnHasValue(recordIndex, 5, startTime);
		super.checkColumnHasValue(recordIndex, 6, endTime);
		
		

		super.signOut();
	}

}
