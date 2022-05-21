package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementShowTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorTest(final int key, final String creationMoment, final String title,
		final String body, final String critical, final String link)
	{
			super.signIn("inventor1", "inventor1");
			
			super.clickOnMenu("Inventor","List announcements");
			super.checkListingExists();
			super.sortListing(0, "asc");
			super.clickOnListingRecord(key);
			super.checkFormExists();
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("creationMoment", creationMoment);
			super.checkInputBoxHasValue("body", body);
			super.checkInputBoxHasValue("isCritic", critical);
			super.checkInputBoxHasValue("link", link);

		
	}
}
