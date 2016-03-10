package ca.ualberta.appfive;

/**
 * Singleton for User Profile information.
 * This class is only for the logged in user's profile data
 */
public class UserProfile extends BModel<BView>{
    private static UserProfile ourInstance = new UserProfile();
    private static String userName;
    private static String userEmail;


    public static UserProfile getInstance() {
        return ourInstance;
    }

    private UserProfile() {
        super();
    }

    /**
     * Call this update method when a user logs in
     * Polls database for username, checks password, gets contact info
     * @param userName The user's unique username
     * @param userPassword The user's password
     * @throws IllegalArgumentException if the user's name is not in database, or if password
     * isn't right
     */
    public static void updateUserData(String userName, String userPassword)
            throws IllegalArgumentException{
    }

    public void setUserName(String userName) {
        UserProfile.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Call when resetting the user's contact info
     * @param userEmail New email contact info for the user
     */
    public void setUserEmail(String userEmail) {
        UserProfile.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
