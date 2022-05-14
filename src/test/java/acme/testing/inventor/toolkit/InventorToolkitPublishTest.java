package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness {
	
	@Test
	@Order(20)
	public void positiveTest() {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(0, 2, "false");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.clickOnButton("Quantities of inventions");
		super.clickOnButton("Create");
		super.fillInputBoxIn("invention.code", "AAA-000-Z");
		super.fillInputBoxIn("numberOfQuantity", "1");
		super.clickOnSubmit("Create");
		super.clickOnButton("Return");
		
		super.clickOnSubmit("Publish");
		
		super.checkNotErrorsExist();
		
		super.signOut(); 

	}
	
	@Test
	@Order(10)
	public void negativeTest() {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "desc");
		
		super.checkColumnHasValue(0, 2, "true");
		super.clickOnListingRecord(0);
		super.checkNotButtonExists("Publish");
		
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(0, 2, "false");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.fillInputBoxIn("code", "a");
		super.fillInputBoxIn("description", "sex sex sex sex sex sex");
		super.clickOnSubmit("Publish");
		super.checkAlertExists(false);
		
		super.clickOnButton("Quantities of inventions");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Delete");
		super.clickOnButton("Return");
		super.clickOnSubmit("Publish");
		super.checkAlertExists(false);
		
		super.signOut(); 

	}
	
}
