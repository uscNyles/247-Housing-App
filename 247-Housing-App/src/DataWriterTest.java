import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {

	Renter renter;
	Renter rs;
	Renter renterSeller;
	Seller seller;
	RealEstateAgent realEstateAgent;
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Property> properties = new ArrayList<Property>();
	ArrayList<Room> rooms = new ArrayList<Room>();
	ArrayList<Review> reviews = new ArrayList<Review>();
	
	@BeforeEach
	void setup() {
		//Create Users to write
		renter = new Renter("renter1", "password3", "renter1@email.com", 3, "123-456-7890", "Renter Jill", "Bio of renter", "A1232456");
		rs = new Renter("renter2", "password4", "renter2@email.com", 4, "123-456-7890", "Renter Seller Joey", "Bio of renterSeller", "A5649874");
		renterSeller = new Renter("renter2", "password4", "renter2@email.com", 4, "123-456-7890", "Renter Seller Joey", "Bio of renterSeller", "A5649874", true, new Seller(rs));
		seller = new Seller("seller1", "password2", "seller1@email.com", 2, "123-456-7890", "Seller Bob", "Bio of seller", new ArrayList<Property>());
		realEstateAgent = new RealEstateAgent("agent1", "password1", "agent1@email.com", 1, "123-456-7890", "Agent Smith", "Bio of agent", "Some Agency", new ArrayList<Property>());
		users.add(renter);
		users.add(renterSeller);
		users.add(seller);
		users.add(realEstateAgent);
		users.get(0).addContactInfo("contact1");
		users.get(1).addContactInfo("contact2");
		users.get(2).addContactInfo("contact3");
		users.get(3).addContactInfo("contact4");
		//Create properties to write
		Property property1 = new Property(1, "property1", "address1", "zip1", "city1", "state1", "description1", 1);
		Property property2 = new Property(2, "property2", "address2", "zip2", "city2", "state2", "description2", 2);
		properties.add(property1);
		properties.add(property2);
		//Create rooms
		rooms.add(new Room(1, 1, 1, 1, "condition1", new ArrayList<String>(), new ArrayList<String>(), PropertyType.APARTMENT, false, false, 111));
		rooms.add(new Room(2, 2, 2, 2, "condition2", new ArrayList<String>(), new ArrayList<String>(), PropertyType.CONDO, true, true, 222));
		properties.get(0).addRoomDB(rooms.get(0));
		properties.get(1).addRoomDB(rooms.get(1));
		//Add amenities to the rooms
		properties.get(0).getRooms().get(0).addAmenity("Pool");
		properties.get(1).getRooms().get(0).addAmenity("Pool");
		//Add bonuses to the rooms
		properties.get(0).getRooms().get(0).addBonus("Pets");
		properties.get(1).getRooms().get(0).addBonus("Pets");
		//Add reviews
		reviews.add(new Review(3, 5, "description1", "renter1"));
		reviews.add(new Review(3, 5, "description2", "renter1"));
		properties.get(0).addReviewDB(reviews.get(0));
		properties.get(1).addReviewDB(reviews.get(1));
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
		//Adding favorites to a user
		Renter r = (Renter)users.get(0);
		r.addFavoriteDB(properties.get(0));
		//Clear everything so that the file is blank
		DataWriter.clearUsers();
		DataWriter.clearProperties();
		DataWriter.clearReviews();
		DataWriter.clearRooms();
	}
	
	@AfterEach
	void tearDown() {
		//Clear everything so it's blank
		DataWriter.clearUsers();
		DataWriter.clearProperties();
		DataWriter.clearReviews();
		DataWriter.clearRooms();
		users.clear();
		properties.clear();
		reviews.clear();
		rooms.clear();
	}
	
	@Test
	void testClearUsers() {
		DataWriter.clearUsers();
		users = DataReader.loadUsers();
		assertEquals(0, users.size());
	}
	
	@Test
	void testClearProperties() {
		DataWriter.clearProperties();
		properties = DataReader.loadProperties();
		assertEquals(0, properties.size());
	}
	
	@Test
	void testClearRooms() {
		DataWriter.clearRooms();
		rooms = DataReader.loadRooms();
		assertEquals(0, rooms.size());
	}
	
	@Test
	void testClearReviews() {
		DataWriter.clearReviews();
		reviews = DataReader.loadReviews();
		assertEquals(0, reviews.size());
	}
	
	@Test
	void testWriteRenter() {
		DataWriter.writeRenter(renter);
		users = DataReader.loadUsers();
		assertEquals("renter1", users.get(0).getUsername());
	}
	
	@Test
	void testUpdateRenter() {
		DataWriter.writeRenter(renter);
		renter.setName("updatedRenter");
		DataWriter.writeRenter(renter);
		users = DataReader.loadUsers();
		assertEquals("updatedRenter", users.get(0).getName());
	}
	
	@Test
	void testWriteRenterSeller() {
		DataWriter.writeRenter(renterSeller);
		users = DataReader.loadUsers();
		assertEquals("renter2", users.get(0).getUsername());
	}
	
	@Test
	void testUpdateRenterSeller() {
		DataWriter.writeRenter(renterSeller);
		renterSeller.setName("updatedRenterSeller");
		DataWriter.writeRenter(renterSeller);
		users = DataReader.loadUsers();
		assertEquals("updatedRenterSeller", users.get(0).getName());
	}
	
	@Test
	void testWriteSeller() {
		DataWriter.writeSeller(seller);
		users = DataReader.loadUsers();
		assertEquals("seller1", users.get(0).getUsername());
	}
	
	@Test
	void testUpdateSeller() {
		DataWriter.writeSeller(seller);
		seller.setName("updatedSeller");
		DataWriter.writeSeller(seller);
		users = DataReader.loadUsers();
		assertEquals("updatedSeller", users.get(0).getName());
	}
	
	@Test
	void testWriteRealEstateAgent() {
		DataWriter.writeRE(realEstateAgent);
		users = DataReader.loadUsers();
		assertEquals("agent1", users.get(0).getUsername());
	}
	
	@Test
	void testUpdateRealEstateAgent() {
		DataWriter.writeRE(realEstateAgent);
		realEstateAgent.setName("updatedAgent");
		DataWriter.writeRE(realEstateAgent);
		users = DataReader.loadUsers();
		assertEquals("updatedAgent", users.get(0).getName());
	}
	
	@Test
	void testWriteProperty() {
		DataWriter.writeProperty(properties.get(0));
		properties = DataReader.loadProperties();
		assertEquals("property1", properties.get(0).getName());
	}
	
	@Test
	void testWriteReview() {
		DataWriter.writeReview(reviews.get(0));
		reviews = DataReader.loadReviews();
		assertEquals("description1", reviews.get(0).getDescription());
	}
	
	@Test
	void testWriteRoom() {
		DataWriter.writeRoom(rooms.get(0));
		rooms = DataReader.loadRooms();
		assertEquals("condition1", rooms.get(0).getCondition());
	}
	
	@Test
	void testUpdateRoom() {
		DataWriter.writeRoom(rooms.get(0));
		rooms = DataReader.loadRooms();
		rooms.get(0).setBeds(7);
		DataWriter.writeRoom(rooms.get(0));
		rooms = DataReader.loadRooms();
		assertEquals(7, rooms.get(0).getBeds());
	}
	
	@Test
	void testRemoveUser_Exists() {
		DataWriter.writeRenter((Renter)users.get(0));
		DataWriter.removeUser(users.get(0).getUserID());
		users = DataReader.loadUsers();
		assertEquals(0, users.size());
	}
	
	@Test
	void testRemoveUser_NotExists() {
		DataWriter.writeRenter((Renter)users.get(0));
		DataWriter.removeUser(44);
		users = DataReader.loadUsers();
		assertEquals(1, users.size());
	}
	
	@Test
	void testRemoveProperty_Exists() {
		DataWriter.writeProperty(properties.get(0));
		DataWriter.removeProperty(properties.get(0).getID());
		properties = DataReader.loadProperties();
		assertEquals(0, properties.size());
	}
	
	@Test
	void testRemoveProperty_NotExists() {
		DataWriter.writeProperty(properties.get(0));
		DataWriter.removeProperty(44);
		properties = DataReader.loadProperties();
		assertEquals(1, properties.size());
	}
	
	@Test
	void testRemoveRoom_Exists() {
		DataWriter.writeRoom(rooms.get(0));
		DataWriter.removeRoom(rooms.get(0).getRoomID());
		rooms = DataReader.loadRooms();
		assertEquals(0, rooms.size());
	}
	
	@Test
	void testRemoveRoom_NotExists() {
		DataWriter.writeRoom(rooms.get(0));
		DataWriter.removeRoom(44);
		rooms = DataReader.loadRooms();
		assertEquals(1, rooms.size());
	}
	
	@Test
	void testRemoveReview_Exists() {
		DataWriter.writeReview(reviews.get(0));
		DataWriter.removeReview(reviews.get(0).getID());
		reviews = DataReader.loadReviews();
		assertEquals(0, reviews.size());
	}
	
	@Test
	void testRemoveReview_NotExists() {
		DataWriter.writeReview(reviews.get(0));
		DataWriter.removeReview(44);
		reviews = DataReader.loadReviews();
		assertEquals(1, reviews.size());
	}
	
	@Test
	void testRemoveReviewFromAuthor() {
		DataWriter.writeRenter((Renter)users.get(0));
		DataWriter.writeReview(reviews.get(0));
		DataWriter.removeUser(users.get(0).getUserID());
		reviews = DataReader.loadReviews();
		assertEquals(0, reviews.size());
	}
	
	@Test
	void testRemoveReviewFromProperty() {
		DataWriter.writeProperty(properties.get(0));
		DataWriter.writeProperty(properties.get(1));
		DataWriter.writeReview(reviews.get(0));
		DataWriter.removeProperty(properties.get(0).getID());
		reviews = DataReader.loadReviews();
		assertEquals(0, reviews.size());
	}
	
	@Test
	void testRemovePropertyFromFavorites() {
		DataWriter.writeProperty(properties.get(0));
		DataWriter.writeRenter((Renter)users.get(0));
		DataWriter.removeProperty(properties.get(0).getID());
		users = DataReader.loadUsers();
		Renter removedFavorite = (Renter)users.get(0);
		assertEquals(0, removedFavorite.getFavorites().size());
	}
	
	@Test
	void testAddListing() {
		RealEstateAgent reAgent = (RealEstateAgent)users.get(3);
		Property p = new Property(reAgent.getUserID(), "listing", "address", "zip", "city", "state", "desciption", 1);
		reAgent.listPropertyDB(p);
		DataWriter.writeRE(reAgent);
		DataWriter.writeProperty(p);
		users = DataReader.loadUsers();
		reAgent = (RealEstateAgent)users.get(0);
		assertEquals(1, reAgent.getListings().size());
	}
	
	@Test
	void testRemoveProperty_RealEstateAgentDeleted() {
		RealEstateAgent reAgent = (RealEstateAgent)users.get(3);
		Property p = new Property(reAgent.getUserID(), "listing", "address", "zip", "city", "state", "desciption", 1);
		reAgent.listPropertyDB(p);
		DataWriter.writeRE(reAgent);
		DataWriter.writeProperty(p);
		DataWriter.removeUser(reAgent.getUserID());
		properties = DataReader.loadProperties();
		assertEquals(0, properties.size());
	}
	
	@Test
	void testRemoveListing_PropertyDeleted() {
		RealEstateAgent reAgent = (RealEstateAgent)users.get(3);
		Property p = new Property(reAgent.getUserID(), "listing", "address", "zip", "city", "state", "desciption", 1);
		reAgent.listPropertyDB(p);
		DataWriter.writeRE(reAgent);
		DataWriter.writeProperty(p);
		DataWriter.removeProperty(p.getID());
		users = DataReader.loadUsers();
		reAgent = (RealEstateAgent)users.get(0);
		assertEquals(0, reAgent.getListings().size());
	}
	
	@Test
	void testRemovePropertyFromSeller_PropertyDeleted() {
		Seller sellerRemovedProperty = (Seller)users.get(2);
		Property p = new Property(sellerRemovedProperty.getUserID(), "listing", "address", "zip", "city", "state", "desciption", 1);
		sellerRemovedProperty.addPropertyDB(p);
		DataWriter.writeSeller(sellerRemovedProperty);
		DataWriter.writeProperty(p);
		DataWriter.removeProperty(p.getID());
		users = DataReader.loadUsers();
		sellerRemovedProperty = (Seller)users.get(0);
		assertEquals(0, sellerRemovedProperty.getProperties().size());
	}
	
	@Test
	void testRemoveRoomFromProperties() {
		DataWriter.writeProperty(properties.get(0));
		DataWriter.writeRoom(rooms.get(0));
		DataWriter.removeRoom(rooms.get(0).getRoomID());
		properties = DataReader.loadProperties();
		assertEquals(1, properties.get(0).getRooms().size());
	}
	
	@Test
	void testRemoveSeller() {
		Seller sellerRemoval = (Seller)users.get(2);
		Property removeProperty = properties.get(1);
		sellerRemoval.addPropertyDB(removeProperty);
		DataWriter.writeSeller(sellerRemoval);
		DataWriter.writeProperty(removeProperty);
		DataWriter.removeUser(sellerRemoval.getUserID());
		properties = DataReader.loadProperties();
		assertEquals(0, properties.size());
	}
}
