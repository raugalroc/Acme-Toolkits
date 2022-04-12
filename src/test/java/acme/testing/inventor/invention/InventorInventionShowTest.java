package acme.testing.inventor.invention;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorInventionShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my components");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.clickOnListingRecord(recordIndex);
		
		final String componentInventor1Path = super.getCurrentPath();
		final String componentInventor1Query = super.getCurrentQuery().replace("?", "");
		
		super.signOut();
		
		super.signIn("inventor2", "inventor2");
		
		this.navigateWithQuery();
		
		super.navigate(componentInventor1Path, componentInventor1Query);
		
		super.checkPanicExists();
		super.checkErrorsExist();
		
		this.setQueryContext();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
	private void navigateWithQuery() {
		final BrowserDriver bd = super.getDriver();
		bd.contextQuery += "&";
	}
	
	private void setQueryContext() {
		final BrowserDriver bd = super.getDriver();
		final Integer lastAdd = bd.contextQuery.lastIndexOf("&");
		bd.contextQuery = bd.contextQuery.substring(0, lastAdd);
	}
	
}
