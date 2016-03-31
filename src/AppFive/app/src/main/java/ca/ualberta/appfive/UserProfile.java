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
}
