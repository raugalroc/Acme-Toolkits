package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorTest(final int key, final String creationMoment, final String title,
			final String body,
		 final String critical, final String link) 
	{
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor","List announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(key, 0, title);
		super.checkColumnHasValue(key, 1, creationMoment);
		super.checkColumnHasValue(key, 2, link);
		super.checkColumnHasValue(key, 3, critical);
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronTest(final int key, final String creationMoment, final String title,
		final String body, final String critical, final String link) 
	{
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron","List announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(key, 0, title);
		super.checkColumnHasValue(key, 1, creationMoment);
		super.checkColumnHasValue(key, 2, link);
		super.checkColumnHasValue(key, 3, critical);
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAdministratorTest(final int key, final String creationMoment, final String title,
		final String body, final String critical, final String link) 
	{
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","List announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(key, 0, title);
		super.checkColumnHasValue(key, 1, creationMoment);
		super.checkColumnHasValue(key, 2, link);
		super.checkColumnHasValue(key, 3, critical);
		
	}
}
