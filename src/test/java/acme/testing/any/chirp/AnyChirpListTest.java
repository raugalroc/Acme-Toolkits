package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAsAnonymous(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {
		super.clickOnMenu("Anonymous", "Recent chirps");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/chirp/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveAsAuthenticated(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "Recent chirps");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/chirp/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveAsAdministrator(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Recent chirps");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/chirp/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		
	}
	
	

}
