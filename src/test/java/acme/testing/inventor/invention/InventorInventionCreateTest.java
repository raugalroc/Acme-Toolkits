package acme.testing.inventor.invention;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorInventionCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/positive-create-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published, final String inventionTypeSt, final String publishedSt) {
	
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventionType", inventionType);
		super.fillInputBoxIn("published", published);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		 
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("inventionType", inventionTypeSt);
		super.checkInputBoxHasValue("published", publishedSt);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/negative-create-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
	
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventionType", inventionType);
		super.fillInputBoxIn("published", published);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/positive-create-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published, final String inventionTypeSt, final String publishedSt) {
	
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventionType", inventionType);
		super.fillInputBoxIn("published", published);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		 
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("inventionType", inventionTypeSt);
		super.checkInputBoxHasValue("published", publishedSt);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/negative-create-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void negativeTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
	
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventionType", inventionType);
		super.fillInputBoxIn("published", published);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}
}
