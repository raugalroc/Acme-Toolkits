package acme.testing.any.inventor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyInventorListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/any/inventor/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAsInventor(final int recordIndex, final String key, final String company, final String statement, final String link) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Account", "Inventors");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/inventor/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, key);
		super.checkColumnHasValue(recordIndex, 1, company);
		super.checkColumnHasValue(recordIndex, 2, statement);
		super.checkColumnHasValue(recordIndex, 3, link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("username", key);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/inventor/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveasAnonymous(final int recordIndex, final String key, final String company, final String statement, final String link) {
		
		super.clickOnMenu("Anonymous", "Inventors");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/inventor/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, key);
		super.checkColumnHasValue(recordIndex, 1, company);
		super.checkColumnHasValue(recordIndex, 2, statement);
		super.checkColumnHasValue(recordIndex, 3, link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("username", key);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
		
	}
}
