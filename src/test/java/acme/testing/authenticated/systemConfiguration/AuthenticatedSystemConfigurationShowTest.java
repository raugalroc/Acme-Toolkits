package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAsPatron(final String systemCurrency, final String acceptedCurrencies) {
		super.signIn("patron2", "patron2");

		super.clickOnMenu("Patron", "System Configuration");

		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);

		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTestAsInventor(final String systemCurrency, final String acceptedCurrencies) {
		
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "System Configuration");

		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);

		super.signOut();
	}
}
