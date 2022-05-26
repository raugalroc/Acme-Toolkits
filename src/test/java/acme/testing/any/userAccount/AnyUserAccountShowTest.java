package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountShowTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAsAnonymous(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles) {
		super.clickOnMenu("Anonymous", "User Account");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/user-account/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, roles);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("roles", roles);
			
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveAsPrincipal(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "User Account");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/user-account/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, roles);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("roles", roles);
			
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveAsAdministrator(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User Account");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/any/user-account/list");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, roles);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("roles", roles);
			
		super.signOut();
	}

	
}
