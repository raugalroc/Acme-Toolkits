package acme.testing.patron.patronDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class PatronPatronDashboardShowTest extends TestHarness{
	
	@Test
	@Order(10)
	public void positive() {
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "Patron Dashboard");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-d06-22.5/patron/patron-dashboard/show");
		super.checkNotPanicExists();
		
		super.signOut();
	}
}
