package acme.testing.inventor.invention;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorInventionUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/positive-update-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkSubmitExists("Update Invention");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventionType", inventionType);
		super.fillInputBoxIn("published", published);

		super.clickOnSubmit("Update Invention");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
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
	@CsvFileSource(resources = "/inventor/invention/negative-update-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkSubmitExists("Update Invention");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Update Invention");

		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/positive-update-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkSubmitExists("Update Invention");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventionType", inventionType);
		super.fillInputBoxIn("published", published);

		super.clickOnSubmit("Update Invention");
		super.clickOnMenu("Inventor", "List my tools");
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
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
	@CsvFileSource(resources = "/inventor/invention/negative-update-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void negativeTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkSubmitExists("Update Invention");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Update Invention");

		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(50)
	public void hackingTest() {
		//Usar herramientas como PostMan para hacer una petición de actualizado a un invention del que no sea propietario
	}
	
	
}
