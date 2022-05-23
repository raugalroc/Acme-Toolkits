package acme.testing.inventor.invention;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorInventionPublishTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/positive-publish-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 2,  published);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.clickOnSubmit("Publish Invention");
		
		super.checkNotErrorsExist();
		
		super.signOut(); 

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/negative-publish-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "desc");
		
		super.checkColumnHasValue(recordIndex, 2,  published);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkNotButtonExists("Publish Invention");
		
		super.signOut(); 

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/positive-publish-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 2,  published);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.clickOnSubmit("Publish Invention");
		
		super.checkNotErrorsExist();
		
		super.signOut(); 

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/invention/negative-publish-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void negativeTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String inventionType, final String published) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my tools");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(2, "desc");
		
		super.checkColumnHasValue(recordIndex, 2,  published);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkNotButtonExists("Publish Invention");
		
		super.signOut(); 

	}
	
	@Test
	@Order(50)
	public void hackingTest() {
		//Usar herramientas como PostMan para hacer una petición de publicar a un invention del que no sea propietario
	}
}
