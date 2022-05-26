package acme.testing.inventor.invention;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorInventionListMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/list-mine-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestListMyTools(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType,final String published, final String inventionTypeSt, final String publishedSt) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my tools");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/inventor/invention/list-mine?type=TOOL");
		super.sortListing(0, "asc");
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, published);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, retailPrice);

		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", publishedSt);
		super.checkInputBoxHasValue("inventionType", inventionTypeSt);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/list-mine-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTestListMyComponents(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType,final String published, final String inventionTypeSt, final String publishedSt) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my components");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/inventor/invention/list-mine?type=COMPONENT");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, published);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, retailPrice);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", publishedSt);
		super.checkInputBoxHasValue("inventionType", inventionTypeSt);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/list-mine-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType,final String published, final String inventionTypeSt, final String publishedSt) {
		super.signIn("inventor1", "inventor1");
		
		super.navigate("/inventor/invention/list-mine");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/inventor/invention/list-mine?language=en&debug=true");
		super.checkListingExists();
		super.checkListingEmpty();
		super.sortListing(0, "asc");
		
		this.navigateWithQuery();
		
		super.navigate("/inventor/invention/list-mine", "type=InvalidQuery");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/inventor/invention/list-mine?language=en&debug=true&type=InvalidQuery");
		super.checkListingExists();
		super.checkListingEmpty();
		super.sortListing(0, "asc");
		
		super.navigate("/inventor/invention/list-mine", "type=CompONent");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/inventor/invention/list-mine?language=en&debug=true&type=CompONent");
		super.sortListing(0, "asc");
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, published);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, retailPrice);
		
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
