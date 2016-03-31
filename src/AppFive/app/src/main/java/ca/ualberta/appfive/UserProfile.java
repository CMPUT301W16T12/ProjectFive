package ca.ualberta.appfive;

/**
 * Singleton for User Profile information.
 * This class is only for the logged in user's profile data
 */
public class UserProfile extends BModel<BView>{
    private static UserProfile ourInstance = new UserProfile();
    private static String userName;
    private static String userEmail;
    private static String userId = null;
    private static String userPassword;
    private static String firstName;
    private static String lastName;
    private static String phoneNumber;


    public static UserProfile getInstance() {
        return ourInstance;
    }

    private UserProfile() {
        super();
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
    public static void updateUserData(String userName, String userPassword)
            throws IllegalArgumentException{
    }

    public static void setUserName(String userName) {
        UserProfile.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserId(String userId){
        UserProfile.userId = userId;
    }

    public static String getUserId() {
        return userId;
    }

    /**
     * Call when resetting the user's contact info
     * @param userEmail New email contact info for the user
     */
    public static void setUserEmail(String userEmail) {
        UserProfile.userEmail = userEmail;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserPassword(String userEmail) {
        UserProfile.userPassword = userEmail;
    }

    public static String getUserPassword() {
        return userPassword ;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        UserProfile.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        UserProfile.firstName = firstName;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        UserProfile.phoneNumber = phoneNumber;
    }
}
