package ca.ualberta.appfive;

/**
 * Singleton for User Profile information.
 * This class is only for the logged in user's profile data
 */
public class UserProfile{
    private static UserProfile ourInstance = new UserProfile();
    private String userName;
    private String userEmail;
    private String userId = null;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String phoneNumber;


    public static UserProfile getInstance() {
        return ourInstance;
    }

    private UserProfile() {
    }

    public static void resetUserProfile(){
        ourInstance = new UserProfile();
    }

    /**
     * Call this update method when a user logs in
     * Polls database for username, checks password, gets contact info
     * @param userName Unique user name
     * @param firstName User's first Name
     * @param lastName User's last Name
     * @param userPassword User's password
     * @param userEmail User's email
     * @param phoneNumber User's phone number
     * @throws IllegalArgumentException if the user's name is not in database, or if password
     * isn't right
     */
    public void updateUserData(String userName, String userPassword)
            throws IllegalArgumentException{
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * Call when resetting the user's contact info
     * @param userEmail New email contact info for the user
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword ;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
