package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/positive-create-announcement.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnnouncement(final int key, final String title, final String body, final String critic, final String link) {
	
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create announcement");
		
			
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critic", critic);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
	
		super.clickOnMenu("Administrator", "List announcements");

		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(0, "asc");
				
		String finalCritic = "no";
		if(critic.equals("true")) {
			finalCritic = "yes";
		}
		super.checkColumnHasValue(key, 0, title);
		super.checkColumnHasValue(key, 2, link);
		super.checkColumnHasValue(key, 3, finalCritic);

		
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/negative-create-announcement.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTestAnnouncement(final int key, final String title, final String body, final String critic, final String link) {
	
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create announcement");
		
			
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critic", critic);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		
		super.signOut();
	}
}
