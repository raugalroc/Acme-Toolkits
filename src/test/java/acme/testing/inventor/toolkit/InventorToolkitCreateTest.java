package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/positive-create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
	
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.clickOnButton("Create toolkit");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		 
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/negative-create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTestComponent(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
	
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.clickOnButton("Create toolkit");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
}
