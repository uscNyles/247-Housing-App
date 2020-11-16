import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataReaderTest {
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Property> properties = new ArrayList<Property>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Review> reviews = new ArrayList<Review>();
	
	@BeforeEach
	public void setup() {
		//Clear everything
		users.clear();
		DataWriter.clearUsers();
		properties.clear();
		DataWriter.clearProperties();
		rooms.clear();
		DataWriter.clearRooms();
		reviews.clear();
		DataWriter.clearReviews();
		DataWriter.writeRE(new RealEstateAgent("agent1", "password1", "agent1@email.com", 1, "123-456-7890", "Agent Smith", "Bio of agent", "Some Agency", new ArrayList<Property>()));
		DataWriter.writeSeller(new Seller("seller1", "password2", "seller1@email.com", 2, "123-456-7890", "Seller Bob", "Bio of seller", new ArrayList<Property>()));
		DataWriter.writeRenter(new Renter("renter1", "password3", "renter1@email.com", 3, "123-456-7890", "Renter Jill", "Bio of renter", "A1232456"));
		Renter rs = new Renter("renter 2", "password4", "renter2@email.com", 4, "123-456-7890", "Renter Seller Joey", "Bio of renterSeller", "A5649874");
		DataWriter.writeRenter(new Renter("renter 2", "password4", "renter2@email.com", 4, "123-456-7890", "Renter Seller Joey", "Bio of renterSeller", "A5649874", true, new Seller(rs)));
		users = DataReader.loadUsers();
		//Adding contact information to the users
		users.get(0).addContactInfo("contact1");
		users.get(1).addContactInfo("contact2");
		users.get(2).addContactInfo("contact3");
		users.get(3).addContactInfo("contact4");
		DataWriter.writeRE((RealEstateAgent)users.get(0));
		DataWriter.writeSeller((Seller)users.get(1));
		DataWriter.writeRenter((Renter)users.get(2));
		DataWriter.writeRenter((Renter)users.get(3));
		users = DataReader.loadUsers();
		//Add properties
		DataWriter.writeProperty(new Property(1, "property1", "address1", "zip1", "city1", "state1", "description1", 1));
		DataWriter.writeProperty(new Property(2, "property2", "address2", "zip2", "city2", "state2", "description2", 2));
		properties = DataReader.loadProperties();
		//Add rooms
		properties.get(0).addRoomDB(new Room(1, 1, 1, 1, "condition1", new ArrayList<String>(), new ArrayList<String>(), PropertyType.APARTMENT, false, false, 111));
		properties.get(1).addRoomDB(new Room(2, 2, 2, 2, "condition2", new ArrayList<String>(), new ArrayList<String>(), PropertyType.CONDO, true, true, 222));
		//Add amenities to the rooms
		properties.get(0).getRooms().get(0).addAmenity("Pool");
		properties.get(1).getRooms().get(0).addAmenity("Pool");
		//Add bonuses to the rooms
		properties.get(0).getRooms().get(0).addBonus("Pets");
		properties.get(1).getRooms().get(0).addBonus("Pets");
		//Save the rooms
		DataWriter.writeRoom(properties.get(0).getRooms().get(0));
		DataWriter.writeRoom(properties.get(1).getRooms().get(0));
		//Add reviews
		properties.get(0).addReviewDB(new Review(3, 5, "description1", "renter1"));
		properties.get(1).addReviewDB(new Review(3, 5, "description2", "renter1"));
		//Set the ID of the reviews
		properties.get(0).getReviews().get(0).setID(1);
		properties.get(1).getReviews().get(0).setID(2);
		//Add payment types to the rooms
		properties.get(0).addPaymentTypeDB(PaymentType.CASH);
		properties.get(0).addPaymentTypeDB(PaymentType.CHECK);
		properties.get(0).addPaymentTypeDB(PaymentType.CREDIT);
		properties.get(0).addPaymentTypeDB(PaymentType.DEBIT);
		properties.get(1).addPaymentTypeDB(PaymentType.CASH);	
		properties.get(1).addPaymentTypeDB(PaymentType.CHECK);
		properties.get(1).addPaymentTypeDB(PaymentType.CREDIT);
		properties.get(1).addPaymentTypeDB(PaymentType.DEBIT);
		DataWriter.writeReview(properties.get(0).getReviews().get(0));
		DataWriter.writeReview(properties.get(1).getReviews().get(0));
		DataWriter.writeProperty(properties.get(0));
		DataWriter.writeProperty(properties.get(1));
		users = DataReader.loadUsers();
		properties = DataReader.loadProperties();
		rooms = DataReader.loadRooms();
		reviews = DataReader.loadReviews();
	}
	
	@AfterEach
	public void tearDown() {
		users.clear();
		DataWriter.clearUsers();
		properties.clear();
		DataWriter.clearProperties();
		rooms.clear();
		DataWriter.clearRooms();
		reviews.clear();
		DataWriter.clearReviews();
	}
	
	@Test
	void testLoadUsersSize() {
		assertEquals(4, users.size());
	}
	
	@Test
	void testLoadUsersUsername() {
		assertEquals("agent1", users.get(0).getUsername());
	}
	
	@Test
	void testLoadPropertiesSize() {
		assertEquals(2, properties.size());
	}
	
	@Test
	void testLoadPropertiesFirstName() {
		assertEquals("property1", properties.get(0).getName());
	}
	
	@Test
	void testLoadRoomsSize() {
		assertEquals(2, rooms.size());
	}
	
	@Test
	void testLoadRoomsFirstCondition() {
		assertEquals("condition1", rooms.get(0).getCondition());
	}
	
	@Test
	void testLoadReviewsSize() {
		assertEquals(2, reviews.size());
	}
	
	@Test
	void testLoadReviewsFirstDescription() {
		assertEquals("description1", reviews.get(0).getDescription());
	}
	
	@Test
	void testUserExists_True() {
		assertTrue(DataReader.userExists(2));
	}
	
	@Test
	void testUserExists_False() {
		assertFalse(DataReader.userExists(44));
	}
	
	@Test
	void testPropertyExists_True() {
		assertTrue(DataReader.propertyExists(2));
	}
	
	@Test
	void testPropertyExists_False() {
		assertFalse(DataReader.propertyExists(44));
	}
	
	@Test
	void testRoomExists_True() {
		assertTrue(DataReader.roomExists(2));
	}
	
	@Test
	void testRoomExists_False() {
		assertFalse(DataReader.roomExists(44));
	}
	
	@Test
	void testReviewExists_True() {
		assertTrue(DataReader.reviewExists(2));
	}
	
	@Test
	void testReviewExists_False() {
		assertFalse(DataReader.reviewExists(44));
	}
	
	@Test
	void testGetUser_Working() {
		User user = DataReader.getUser(2);
		assertEquals("seller1", user.getUsername());
	}
	
	@Test
	void testGetUser_Null() {
		User user = DataReader.getUser(44);
		assertNull(user);
	}
	
	@Test
	void testGetProperty_Working() {
		Property property = DataReader.getProperty(2);
		assertEquals("property2", property.getName());
	}
	
	@Test
	void testGetProperty_Null() {
		Property property = DataReader.getProperty(44);
		assertNull(property);
	}
	
	@Test
	void testGetRoom_Working() {
		Room room = DataReader.getRoom(2);
		assertEquals("condition2", room.getCondition());
	}
	
	@Test
	void testGetRoom_Null() {
		Room room = DataReader.getRoom(44);
		assertNull(room);
	}
	
	@Test
	void testGetReview_Working() {
		Review review = DataReader.getReview(2);
		assertEquals("description2", review.getDescription());
	}
	
	@Test
	void testGetReview_Null() {
		Review review = DataReader.getReview(44);
		assertNull(review);
	}
	
	@Test
	void testGetUsersJSON() {
		JSONArray userJSON = DataReader.getUsersJSON();
		JSONObject firstUser = (JSONObject)userJSON.get(0);
		assertEquals("agent1", String.valueOf(firstUser.get(DataReader.USERS_USERNAME)));
	}
	
	@Test
	void testGetPropertiesJSON() {
		JSONArray propertyJSON = DataReader.getPropertiesJSON();
		JSONObject firstProperty = (JSONObject)propertyJSON.get(0);
		assertEquals("property1", String.valueOf(firstProperty.get(DataReader.PROPERTIES_NAME)));
	}
	
	@Test
	void testGetRoomsJSON() {
		JSONArray roomJSON = DataReader.getRoomsJSON();
		JSONObject firstRoom = (JSONObject)roomJSON.get(0);
		assertEquals("condition1", String.valueOf(firstRoom.get(DataReader.ROOM_CONDITION)));
	}
	
	@Test
	void testGetReviewsJSON() {
		JSONArray reviewJSON = DataReader.getReviewsJSON();
		JSONObject firstReview = (JSONObject)reviewJSON.get(0);
		assertEquals("description1", firstReview.get(DataReader.REVIEWS_DESCRIPTION));
	}
}
