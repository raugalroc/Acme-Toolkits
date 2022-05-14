package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness {
	
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
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("numberOfQuantity", "1");
		super.clickOnSubmit("Update");
		super.clickOnButton("Return");
		
		super.clickOnSubmit("Update");
		
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
		super.checkNotButtonExists("Update");
		
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(0, 2, "false");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.fillInputBoxIn("code", "a");
		super.fillInputBoxIn("description", "sex sex sex sex sex sex");
		super.clickOnSubmit("Update");
		super.checkAlertExists(false);
		
		super.signOut(); 

	}
	
}
