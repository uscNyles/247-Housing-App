import java.util.ArrayList;

public abstract class User {

	private String username;
	private String password;
	private String email;
	private String userID;
	private String phoneNumber;
	private String name;
	private String bio;
	private ArrayList<String> contactInfo;

	public User(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.userID = userID;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.bio = bio;
		this.contactInfo = new ArrayList<String>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getUserID() {
		return userID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public ArrayList<String> getContactInfo() {
		return contactInfo;
	}

	public void setUsername(String username) {
		//TODO: Verify that the username has not already been taken
		this.username = username;
	}

	public boolean setPassword(String password) {
		if (password.length() >= 8) {
			this.password = password;
			return true;
		} else {
			this.password = "password";
			return false;
		}
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserID(String userID) {
		//TODO: Verify that the ID has not already been used using database
		this.userID = userID;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setContactInfo(ArrayList<String> contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	public void addContactInfo(String info) {
		contactInfo.add(info);
	}

	public void giveReview(Property property, Review review) {
		property.addReview(review);
	}

}
