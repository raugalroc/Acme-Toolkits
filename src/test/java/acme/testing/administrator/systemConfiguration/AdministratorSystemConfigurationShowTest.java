package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String systemCurrency, final String acceptedCurrencies, final String strongSpamTerms, final Double strongSpamTermsThreshold, final String weakSpamTerms, final Double weakSpamTermsThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "System configuration");

		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamTermsThreshold", String.format("%.2f", strongSpamTermsThreshold));
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamTermsThreshold", String.format("%.2f", weakSpamTermsThreshold));

		super.signOut();
	}
		
	// Ancillary methods ------------------------------------------------------ 
		
}
