import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	User user = null;
	@BeforeEach 
	public void setup() {
		Main.main(null);
		user = new User("testUsername", "testpassword", "test@email.com", Main.userApi.getNewUserID(), "888-888-8888", "testname", "this is a good bio");
	}
	
	@AfterEach
	public void teardown() {
		user = null;
	}
	
	@Test
	public void testGetContactInfo_ShouldNotBeNull() {
		assertNotNull(user.getContactInfo());
	}
	
	@Test
	public void testGetContactInfo_ShouldHaveOneItem() {
		user.addContactInfo("testInfo");
		assertSame(1, user.getContactInfo().size(),	"Get contact info wasn't of size 1!");
	}
	
	@Test
	public void testGiveReview_NewPropertyShouldHaveTheReview() {
		Property prop = new Property(-1, "test", "111 Good St", "11111", "Columbia", "SC", "Good description");
		Renter rent = new Renter(user.getUsername(), user.getPassword(), user.getEmail(), user.getUserID(), user.getPhoneNumber(), user.getName(), user.getBio(), "someID", false, null);
		Review rev = new Review(rent, 5, "good description");
		user.giveReview(prop, rev);
		ArrayList<Review> revs = prop.getReviews();
		boolean revFound = false;
		for (Review rev2 : revs) {
			if (rev2.equals(rev)) {
				revFound = true;
				break;
			}
		}
		assertTrue(revFound);
	}
	
	@Test
	public void testEquals_TwoUsersWithSameUsernameAreEqual() {
		User newUser = new User(user.getUsername(), "", "", 0, "", "", "");
		assertTrue(user.equals(newUser));
	}

}
