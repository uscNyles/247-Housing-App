import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RealEstateAgentTest {
	
	RealEstateAgent rea;

	@BeforeEach
	void setUp() throws Exception {
		Main.main(null);
		rea = new RealEstateAgent("rea", "rea", "email", Main.userApi.getNewUserID(), "999-999-9999", "rea", "rea bio", "Good Agency", new ArrayList<Property>());
		Main.userApi.createRE(rea);
	}

	@AfterEach
	void tearDown() throws Exception {
		rea = null;
	}

	@Test
	public void testGetListings_ShouldNotBeNull() {
		assertNotNull(rea.getListings());
	}
	
	@Test
	public void testListProperty_ShouldAddNewProperty() {
		Property prop = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		assertTrue(rea.listProperty(prop));
	}
	
	@Test
	public void testListProperty_ShouldNotWorkWithTwoSameProperties() {
		Property prop = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		Property prop2 = prop;
		rea.listProperty(prop);
		assertFalse(rea.listProperty(prop2));
	}
	
	@Test
	public void testShowListings_ShouldReturnEmptyStringWhenNoListingsExist() {
		assertEquals("Didn't Return \"\"", "", rea.showListings());
	}
	
	@Test
	public void testShowListings_ProperFormatReturned() {
		Property prop = new Property(rea.getUserID(), "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		String check = rea.getName() + "'s Current Listings:\n" + prop.toString() 
			+ "\n====================================================================\n\n";
		rea.listProperty(prop);
		assertEquals("Show Listing output was wrong", check, rea.showListings());
	}

}
