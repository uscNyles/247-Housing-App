import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

	private static Scanner s;
	private Menu menus;


	public UserInterface() {
		s = new Scanner(System.in);
		menus = new Menu();
	}

	public void outputMenu(String menu) {
		if (menu.equals("login")) {
			System.out.println(menus.getLoginMenu());
		} else if (menu.equals("welcome")) {
			System.out.println(menus.getWelcomeMenu());
		} else if (menu.equals("leave")) {
			System.out.println(menus.getLeaveMenu());
		} else if (menu.equals("thanks")) {
			System.out.println(menus.getLeaveMenu());
		} else if (menu.equals("giveReview")) {
			System.out.println(menus.getGiveReviewMenu());
		} else if (menu.equals("topProperties")) {
			System.out.println(menus.getTopPropertiesMenu());
		} else if (menu.equals("addProperty")) {
			System.out.println(menus.getAddPropertyMenu());
		} else if (menu.equals("loginSuccess")) {
			System.out.println(menus.getLoginSuccessMenu());
		} else if (menu.equals("createUser")) {
			System.out.println(menus.getCreateUserMenu());
		} else if (menu.equals("guestoptions")) {
			System.out.println(menus.getGuestOptions());
		} else if (menu.equals("renteroptions")) {
			System.out.println(menus.getRenterOptions());
		} else if (menu.equals("reaoptions")) {
			System.out.println(menus.getREAOptions());
		} else if (menu.equals("selleroptions")) {
			System.out.println(menus.getSellerOptions());
		} else if (menu.equals("renterselleroptions")) {
			System.out.println(menus.getRenterSellerOptions());
		}
	}

	public int userLogin(String username, String password) {
		if (Main.userApi.userLogin(username, password) == -1) 
			return -1;
		else {
			outputMenu("loginSuccess");
			int usertype = (Main.userApi.userLogin(username, password));
			return usertype;
		}
	}

<<<<<<< HEAD
	public void addProperty() {
		System.out.print("Name: ");
		String name = s.nextLine();
		System.out.println();
		System.out.print("Street Address: ");
		String address = s.nextLine();
		System.out.println();
		System.out.print("Zip Code: ");
		String zipCode = s.nextLine();
		System.out.println();
		System.out.print("City: ");
		String city = s.nextLine();
		System.out.println();
		System.out.print("State: ");
		String state = s.nextLine();
		System.out.println();
		System.out.print("Description: ");
		String description = s.nextLine();
		System.out.println();
		Property prop = null;
		if (Main.renter != null)
			prop = new Property(Main.renter.getUserID(), name, address, city, state, zipCode, description);
		else if (Main.seller != null)
			prop = new Property(Main.seller.getUserID(), name, address, city, state, zipCode, description);
		else if (Main.rea != null)
			prop = new Property(Main.rea.getUserID(), name, address, city, state, zipCode, description);
		System.out.print("How many rooms would you like to add to this property? ");
		int choice = s.nextInt();
		System.out.println();
		s.nextLine();
		if (choice < 1) {
			System.out.println("Invalid value entered.");
			return;
		}
		ArrayList<Room> rooms = new ArrayList<Room>();
		for (int i = 0; i < choice; i++) {
			System.out.println("Room #" + (i+1));
			System.out.print("Room Number: ");
			int roomNum = s.nextInt(); 
			System.out.println();
			s.nextLine();
			System.out.print("Number of beds: ");
			int numBeds = s.nextInt();
			System.out.println();
			s.nextLine();
			System.out.print("Number of baths: ");
			int numBaths = s.nextInt();
			System.out.println();
			s.nextLine();
			System.out.print("Room Condition: ");
			String condition = s.nextLine();
			System.out.println();
			System.out.println("Enter the available amenities (type 'done' when finished): ");
			ArrayList<String> amenities = new ArrayList<String>();
			String amenity = s.nextLine();
			while (!amenity.equalsIgnoreCase("done")) {
				amenities.add(amenity);
				amenity = s.nextLine();
			}
			System.out.println("Enter the bonuses of the room (Ex: pets, walk2campus) (type 'done' when finished): ");
			ArrayList<String> bonuses = new ArrayList<String>();
			String bonus = s.nextLine();
			while (!bonus.equalsIgnoreCase("done")) {
				bonuses.add(bonus);
				bonus = s.nextLine();
			}
			boolean cont = true;
			PropertyType ptype = PropertyType.APARTMENT; // just to initialize 
			while(cont) {
				System.out.println("Choose from the following property types:"
						+ "\n\t1. Apartment"
						+ "\n\t2. Condo"
						+ "\n\t3. House");
				System.out.print("Selection number: ");
				int type = s.nextInt();
				s.nextLine();
				System.out.println();
				switch (type) {
				case 1:
					cont = false;
					ptype = PropertyType.APARTMENT;
					break;
				case 2:
					cont = false;
					ptype = PropertyType.CONDO;
					break;
				case 3:
					cont = false;
					ptype = PropertyType.HOUSE;
					break;
				default:
					System.out.println("Invalid value was entered. Please select a valid choice 1 to 3.");
					break;
				}
			}
			System.out.print("Is the property available to sublease (enter 'yes' or 'no'): ");
			String subLease = s.next();
			s.nextLine();
			boolean sublease;
			if (subLease.equalsIgnoreCase("yes")) {
				sublease = true;
			} else {
				sublease = false;
			}
			System.out.println();
			System.out.print("Enter the price per month of the room: $");
			double price = s.nextDouble();
			System.out.println();
			s.nextLine();
			rooms.add(new Room(Main.roomApi.getNewRoomID(), roomNum, numBeds, numBaths, condition, amenities, bonuses, ptype, sublease, false, price));
		}
		for (Room room : rooms) {
			prop.addRoomDB(room);
			Main.roomApi.createRoom(room);
		}
		Main.propertyApi.createProperty(prop);
		if (Main.renter != null)
			Main.renter.getSeller().addProperty(prop);
		else if (Main.seller != null)
			Main.seller.addProperty(prop);
		else if (Main.rea != null)
			Main.rea.listProperty(prop);
=======
	//NEED TO MAKE THIS CREATE ROOMS AS WELL -> CURRENTLY ONLY CREATES A PROPERTY
	public void addProperty(Seller sellerUser) {
		System.out.println("Name: ");
		String name = s.nextLine();
		System.out.println("Street Address: ");
		String address = s.nextLine();
		System.out.println("Zip Code: ");
		String zipCode = s.nextLine();
		System.out.println("City: ");
		String city = s.nextLine();
		System.out.println("State: ");
		String state = s.nextLine();
		System.out.println("Description: ");
		String description = s.nextLine();
		Property p = new Property(sellerUser.getUserID(), name, address, city, state, zipCode, description);
		p.setName(name);
		Main.propertyApi.createProperty(p);
>>>>>>> branch 'main' of https://github.com/uscNyles/247-Housing-App.git
	}
	
	public ArrayList<Property> searchProperties(String query) {
		//First, search through everything related to the complex
		String searchQuery = query.toLowerCase();
		ArrayList<Property> props = Main.propertyApi.getProperties();
		//These have something that the user is searching for
		ArrayList<Property> ret = new ArrayList<Property>();
		for(Property p : props) {
			if(searchQuery.contains(p.getAddress().toLowerCase()) || searchQuery.contains(p.getCity().toLowerCase()) || searchQuery.contains(p.getZipCode()) || searchQuery.contains(p.getName().toLowerCase())) {
				if(!ret.contains(p)) {
					ret.add(p);
				}
				continue;
			}
			room:
			for(Room r : p.getRooms()) {
				for(String s : r.getAmenities()) {
					if(searchQuery.contains(s.toLowerCase())) {
						if(!ret.contains(p)) {
							ret.add(p);
						}
						break room;
					}
				}
				for(String s : r.getBonuses()) {
					if(searchQuery.contains(s.toLowerCase())) {
						if(!ret.contains(p)) {
							ret.add(p);
						}
						break room;
					}
				}
				if(searchQuery.contains(r.getBaths() + "") || searchQuery.contains(r.getBeds() + "") || searchQuery.contains(r.getCondition().toLowerCase())) {
					if(!ret.contains(p)) {
						ret.add(p);
					}
				}
			}
		}
		return ret;
	}

	public void showFavorites() {
		for(Property p : Main.renter.getFavorites()) {
			System.out.println(p.toString());
		}
	}

<<<<<<< HEAD
	public void removeListing(int id) {
		Main.propertyApi.deleteProperty(id);
	}
	
	public void removeFavorite(int id) {
		for(Property prop : Main.renter.getFavorites()) {
			if(prop.getID() == id) {
				Main.renter.removeFavorite(prop);
				return;
			}
		}
		System.out.println("That property is not in your favorites.");
	}
	
	public void addToFavorites(int propertyID) {
		for (Property prop : Main.propertyApi.getProperties()) {
			if (prop.getID() == propertyID) {
				Main.renter.addFavorite(prop);
				return;
			}
		}
=======
	public void removeProperty() {
		System.out.println("Please enter the id of the property you wish to remove: ");
		if(Main.renter == null) {
			for(Property p : Main.seller.getProperties()) {
				System.out.println(p.toString());
			}
			int id = s.nextInt();
			s.nextLine();
			Main.propertyApi.deleteProperty(id);
			return;
		}
		for(Property p : Main.renter.getSeller().getProperties()) {
			System.out.println(p.toString());
		}
		int id = s.nextInt();
		s.nextLine();
		Main.propertyApi.deleteProperty(id);
	}

	public void removeListing(int id) {
		Main.propertyApi.deleteProperty(id);
	}
	
	public void removeFavorite(int id) {
		for(Property prop : Main.renter.getFavorites()) {
			if(prop.getID() == id) {
				Main.renter.removeFavorite(prop);
				return;
			}
		}
		System.out.println("That property is not in your favorites.");
	}
	
	public void addToFavorites(int propertyID) {
		for (Property prop : Main.propertyApi.getProperties()) {
			if (prop.getID() == propertyID) {
				Main.renter.addFavorite(prop);
				return;
			}
		}
	}
	
	public void signLease(int userId) {
		
		ArrayList<Integer> tenantIDs = new ArrayList<Integer>();
		tenantIDs.add(userId);
		
		System.out.println("Will you be signing this lease with another tenant? "
				+ "\n(Their uscId will be required)");
		System.out.println("\t0. I will sign a lease by myself");
		String multipleSigners = s.nextLine();
		
		
>>>>>>> branch 'main' of https://github.com/uscNyles/247-Housing-App.git
	}
	
}
