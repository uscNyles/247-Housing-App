import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RenterTest {
	
	Renter rent;
	
	@BeforeEach
	void setUp() throws Exception {
		Main.main(null);
		rent = new Renter("renter", "renter", "email", Main.userApi.getNewUserID(), "888-888-8888", "renter", "renter bio", "someID", false, null);
	}

	@AfterEach
	void tearDown() throws Exception {
		rent = null;
	}
	
	@Test
	public void testFavorites_ShouldNotBeNull() {
		assertNotNull(rent.getFavorites());
	}

	@Test
	public void testAddFavorite_NewFavoriteShouldBeAddedToRenter() {
		Property prop = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		assertTrue(rent.addFavorite(prop));
	}
	
	@Test
	public void testAddFavorite_DuplicateFavoriteShouldNotBeAdded() {
		Property prop = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		Property copy = prop;
		rent.addFavorite(prop);
		assertFalse(rent.addFavorite(copy));
	}
	
	@Test
	public void testRemoveFavorite_ShouldNotRemoveAPropertyThatWasNeverAdded() {
		Property prop = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		assertFalse(rent.removeFavorite(prop));
	}
	
	@Test
	public void testRemoveFavorite_ShouldRemoveAPropertyThatHasBeenAdded() {
		Property prop = new Property(0, "goodName", "111 Best St", "22222", "Columbia", "SC", "This is a test place");
		rent.addFavorite(prop);
		assertTrue(rent.removeFavorite(prop));
	}
	
	@Test
	public void testGetSeller_ShouldBeNull() {
		assertNull(rent.getSeller());
	}
	
	@Test
	public void testMakeSeller_GetSellerShouldReturnASellerAfterMakeSellerCall() {
		rent.makeSeller();
		assertNotNull(rent.getSeller());
	}

}
