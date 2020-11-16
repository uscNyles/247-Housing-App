import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyTest {
	
	Property prop;
	Seller sell;
	
	@BeforeEach
	void setUp() throws Exception {
		Main.main(null);
		sell = new Seller("seller", "seller", "email", Main.userApi.getNewUserID(), "111-111-1111", "seller", "seller bio", new ArrayList<Property>());
		prop = new Property(sell.getUserID(), "prop1", "111 Street", "22222", "Columbia", "SC", "Cool");
		Main.userApi.createSeller(sell);
		Main.propertyApi.createProperty(prop);
	}

	@AfterEach
	void tearDown() throws Exception {
		prop = null;
		sell = null;
	}
	
	@Test
	public void testEquals_ReturnTrueWith2PropertiesOfSameAddress() {
		Property prop2 = new Property(0, "goodName", "111 Street", "22222", "Columbia", "SC", "This is a test place");
		assertTrue(prop.equals(prop2));
	}
	
	@Test
	public void testEquals_ReturnFalseWith2PropertiesOfDifferentAddress() {
		Property prop2 = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		assertFalse(prop.equals(prop2));
	}
	
	@Test
	public void testGetSeller_ShouldReturnTheSellerThatWasUsedToMakeProperty() {
		assertEquals(sell.getUserID(), prop.getSeller().getUserID());
	}
	
	@Test
	public void testAddReview_ShouldReturnTrueWhenSuccessfull() {
		Renter rent = new Renter("user", "pass", "email", 0, "7", "name", "bio", "asdf");
		Review rev = new Review(rent, 5, "good");
		assertTrue(prop.addReview(rev));
	}
	
	@Test
	public void testAddReview_SHouldReturnFalseWhenAddedSameReview() {
		Renter rent = new Renter("user", "pass", "email", 0, "7", "name", "bio", "asdf");
		Review rev = new Review(rent, 5, "good");
		Review rev2 = new Review(rent, 4, "no");
		prop.addReview(rev);
		assertFalse(prop.addReview(rev2));
	}
	
	@Test
	public void testRemoveReview_ShouldReturnFalseIfReviewWasNotFound() {
		Renter rent = new Renter("user", "pass", "email", 0, "7", "name", "bio", "asdf");
		Review rev = new Review(rent, 5, "good"); //not needed for the test but shows the review being made
		assertFalse(prop.removeReview(rent));
	}
	
	@Test
	public void testRemoveReview_ShouldReturnTrueIfReviewWasFoundAndDeleted() {
		Renter rent = new Renter("user", "pass", "email", 0, "7", "name", "bio", "asdf");
		Review rev = new Review(rent, 5, "good");
		prop.addReview(rev);
		assertTrue(prop.removeReview(rent));
	}
	
	@Test
	public void testAddPaymentType_ShouldReturnTrueWhenSuccessfull() {
		PaymentType ptype = PaymentType.CASH;
		assertTrue(prop.addPaymentType(ptype));
	}
	
	@Test
	public void testAddPaymentType_ShouldReturnFalseWhenPaymentAlreadyExists() {
		PaymentType ptype = PaymentType.CASH;
		PaymentType ptype2 = PaymentType.CASH;
		prop.addPaymentType(ptype);
		assertFalse(prop.addPaymentType(ptype2));
	}
	
	@Test
	public void testAddRoom_RoomShouldBeAdded() {
		Room room = new Room(Main.roomApi.getNewRoomID(), 2, 2, 2, "good", new ArrayList<String>(), new ArrayList<String>(), PropertyType.APARTMENT, true, false, 100000);
		prop.addRoomDB(room);
		boolean isfound = false;
		for (Room rm : prop.getRooms()) {
			if (rm.equals(room)) {
				isfound = true;
				break;
			}
		}
		assertTrue(isfound);
	}

}
