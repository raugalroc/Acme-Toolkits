package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness {

	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(0, 2, "false");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.clickOnSubmit("Delete");
		
		super.checkNotErrorsExist();
		
		super.signOut(); 
	
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "desc");
		
		super.checkColumnHasValue(0, 2, "true");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.checkNotButtonExists("Delete");
		
		super.checkNotErrorsExist();
		
		super.signOut(); 
	
	}
	
}
