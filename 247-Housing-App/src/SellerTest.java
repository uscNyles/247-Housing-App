import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SellerTest {
	
	Seller sell;

	@BeforeEach
	void setUp() throws Exception {
		Main.main(null);
		sell = new Seller("seller", "seller", "email", Main.userApi.getNewUserID(), "888-888-8888", "seller", "seller test bio", new ArrayList<Property>());
	}

	@AfterEach
	void tearDown() throws Exception {
		sell = null;
	}
	
	@Test
	public void testConstructor_ConstructorWithRenterParameter() {
		assertNotNull(new Seller(new Renter("someUser", "pass", "email", 8, "88888888", "test", "bio", "someid")));
	}
	
	@Test
	public void testGetProperties_ShouldNoBeNull() {
		assertNotNull(sell.getProperties());
	}

	@Test
	public void testAddProperty_ManyArgumentsFunction_NewPropertyShouldBeAdded() {
		sell.addProperty("test", "111 Other", "33333", "Columbia", "SC", "Good place", "good", 2, new ArrayList<String>(), 700, PropertyType.APARTMENT);
		boolean isFound = false;
		for (Property prop : sell.getProperties()) {
			if (prop.equals(new Property(0, "test", "111 Other", "33333", "Columbia", "SC", "good"))) {
				isFound = true;
				break;
			}	
		}
		assertTrue(isFound);
	}
	
	@Test
	public void testAddProperty_ProperyAsArgument_NewPropertyShouldBeAdded() {
		Property prop = new Property(0, "test", "111 Other", "33333", "Columbia", "SC", "good");
		sell.addProperty(prop);
		boolean isFound = false;
		for (Property prop2 : sell.getProperties()) {
			if (prop2.equals(new Property(0, "test", "111 Other", "33333", "Columbia", "SC", "good"))) {
				isFound = true;
				break;
			}	
		}
		assertTrue(isFound);
	}
	
	@Test
	public void testShowProperties_TwoPropertiesHaveDifferentOutput() {
		Property prop = new Property(sell.getUserID(), "test", "111 Other", "33333", "Columbia", "SC", "good");
		sell.addProperty(prop);
		String props = sell.showProperties();
		assertTrue(props.equals(sell.getName() + "'s Properties:\n" + prop.toString() + "\n====================================================================\n\n"));
	}
	
	@Test
	public void testEquals_TwoDifferentSellersShouldReturnFalse() {
		Seller sell2 = new Seller("seller2", "pass", "email", 5, "999-999-9999", "nameSomething", "good bio", new ArrayList<Property>());
		assertFalse(sell.equals(sell2));
	}
	
	@Test 
	public void testEquals_TwoSellersWithSameNameShouldReturnTrue() {
		Seller sell2 = new Seller("someUserName", "pass", "email", 5, "999-999-9999", sell.getName(), "good bio", new ArrayList<Property>());
		assertTrue(sell.equals(sell2));
	}

}
