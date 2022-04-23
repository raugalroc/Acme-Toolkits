package acme.testing.any.invention;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyInventionListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/invention/component/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponents(final int recordIndex, final String name, final String code,
			final String technology, final String description, final String retailPrice,
			final String link, final String inventionType, final String inventor) 
	{
			
		super.clickOnMenu("Anonymous","List of components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		
		
		
	}


}
