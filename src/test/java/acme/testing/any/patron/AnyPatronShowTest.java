package acme.testing.any.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPatronShowTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/any/patron/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAsInventor(final int recordIndex, final String key, final String company, final String statement, final String link, final String name, final String surname, final String email) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Account", "Patrons");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/patron/list");
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("username", key);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/patron/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveAsAnonymous(final int recordIndex, final String key, final String company, final String statement, final String link, final String name, final String surname, final String email) {
		
		super.clickOnMenu("Anonymous", "Patrons");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/patron/list");
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("username", key);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		
	}
}
