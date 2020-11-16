import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest {
	
	Room room;

	@BeforeEach
	void setUp() throws Exception {
		Main.main(null);
		room = new Room(Main.roomApi.getNewRoomID(), 2, 3, 1, "good", new ArrayList<String>(), new ArrayList<String>(), PropertyType.APARTMENT, true, false, 10000);
	}

	@AfterEach
	void tearDown() throws Exception {
		room = null;
	}

	@Test
	public void testAddAmenity_ShouldFindAmenityThatIsAdded() {
		room.addAmenity("Garage");
		boolean isfound = false;
		for (String str : room.getAmenities()) {
			if (str.equals("Garage")) {
				isfound = true;
				break;
			}
		}
		assertTrue(isfound);
	}
	
	@Test
	public void testRemoveAmenity_ShouldReturnFalseIfAmenityWasNotAdded() {
		assertFalse(room.removeAmenity("something"));
	}
	
	@Test
	public void testRemoveAmenity_ShouldReturnTrueIfAmenityWasAddedThenRemoved() {
		room.addAmenity("Garage");
		assertTrue(room.removeAmenity("Garage"));
	}

	@Test
	public void testAddBonus_NewBonusShouldBeFound() {
		room.addBonus("Bonus");
		boolean isfound = false;
		for (String str : room.getBonuses()) {
			if (str.equalsIgnoreCase("Bonus")) {
				isfound = true;
				break;
			}
		}
		assertTrue(isfound);
	}
	
	@Test
	public void testRmBonus_ShouldReturnFalseIfBonusWasNotAdded() {
		assertFalse(room.rmBonus("whatever"));
	}
	
	@Test
	public void testRmBonus_ShouldReturnTrueIfBonusWasAddedThenRemoved() {
		room.addBonus("Garage");
		assertTrue(room.rmBonus("garage"));
	}
	
}
