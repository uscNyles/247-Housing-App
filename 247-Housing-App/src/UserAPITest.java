import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAPITest {
	
	protected static UserAPI userApi;
	Renter testLoginUser;
	RealEstateAgent testREA;
	Seller testSeller;
	Renter testRenter;
	
	
	@BeforeEach
	public void setup() {
		userApi = UserAPI.getInstance();
		userApi.getUsers().clear();
		userApi.createRenter(new Renter("testusername", "testpassword", "renter1@email.com", 4, "123-456-7890", "Renter Aubrey", "Bio of renter", "A1232456"));
		
		testLoginUser = new Renter("testlogin", "andLogout", "renter1@email.com", 5, "123-456-7890", "Renter Kaleb", "Bio of renter", "A1232456");
		userApi.createRenter(testLoginUser);
		userApi.userLogin("testlogin", "andLogout");
	}
	
	@AfterEach
	public void tearDown() {
		userApi.getUsers().clear();
		
	}
	
	@Test
	void testCreateRealEstateAgent(){
		 testREA = new RealEstateAgent("testreal", "password1", "agent1@email.com", 1, "123-456-7890",
				"Agent Smith", "Bio of agent", "Some Agency", new ArrayList<Property>());
		userApi.createRE(testREA);
		assertEquals("Agent Smith", testREA.getName());
	}
	
	@Test
	void testCreateRenter(){
		testRenter =new Renter("testrenter", "password3", "renter1@email.com", 3, "123-456-7890", "Renter Jill", "Bio of renter", "A1232456");
			userApi.createRenter(testRenter);
			assertEquals("Renter Jill", testRenter.getName());
	}

	@Test
	void testCreateSeller(){
		testSeller =new Seller("tseller", "password2", "seller1@email.com", 2, "123-456-7890", "Seller Bob", "Bio of seller", new ArrayList<Property>());
			userApi.createSeller(testSeller);
			assertEquals("Seller Bob", testSeller.getName());
	}
	
	@Test
	void testUserLoginSuccess(){
		assertEquals(0, userApi.userLogin("testusername", "testpassword"));
	}
	
	@Test
	void testUserLoginInvalid(){
		assertEquals(-1, userApi.userLogin("micheal23", "tesla14"));
	}
	
	@Test
	void testLogoutUserSuccess(){
		assertTrue(userApi.logoutUser(testLoginUser));
	}
	
	@Test
	void testLogoutUserInvalid(){
		assertFalse(userApi.logoutUser(new Renter("testusername", "testpassword", "renter1@email.com", 4, "123-456-7890", "Renter Aubrey", "Bio of renter", "A1232456")));  
	}
	
	@Test
	void testIsLoggedInSuccess(){
		assertTrue(userApi.isLoggedIn(testLoginUser));
	}
	
	@Test
	void testIsLoggedInInvalid(){
		assertFalse(userApi.isLoggedIn(new Renter("", "", "renter1@email.com", 7, "123-456-7890", "Renter Bartholomew", "Bio of renter", "A1232456")));  
	}
	
	@Test
	void testGetNewUserId() {
		int randId = userApi.getNewUserID();
		for(User user: userApi.getUsers()) {
			assertNotEquals(randId, user.getUserID());
		}
	}
	
	@Test
	void testDeleteUser() {
		userApi.deleteUser(4);
		for(User user: userApi.getUsers()) {
			assertNotEquals(4, user.getUserID());
		}
	}

}
