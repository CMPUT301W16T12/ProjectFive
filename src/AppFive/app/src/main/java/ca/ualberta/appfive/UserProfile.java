package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * Singleton for User Profile information.
 * This class is only for the logged in user's profile data
 */
public class UserProfile{
    private static UserProfile ourInstance = new UserProfile();
    private String userName;
    private String userEmail;
    private String userId = null;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private ArrayList<String> notifications = new ArrayList<String>();


    public static UserProfile getInstance() {
        return ourInstance;
    }

    public static void setUserProfile(UserProfile user) {
            ourInstance = user;
    }
    private UserProfile(){
    }

    public static void resetUserProfile(){
        ourInstance = new UserProfile();
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

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void addNotification(String notification) {
        notifications.add(0,notification);
    }
}
