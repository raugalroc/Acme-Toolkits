package acme.testing.administrator.administratorDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class AdministratorAdministratorDashboardShowTest extends TestHarness {

	@Test
	@Order(10)
	public void positive() {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Administrator Dashboard");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/administrator/administrator-dashboard/show");
		super.checkNotPanicExists();
		
		super.signOut();
	}
	
}
