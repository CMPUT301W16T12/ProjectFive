package ca.ualberta.appfive;

/**
 * A model for holding Owner info of books
 */
public class OwnerInfo{

    private String userName;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;


    /**
     * This constructor is called when creating the Owner Info for the current user of the app.
     */
    public OwnerInfo() {
        super();
        this.userName = UserProfile.getInstance().getUserName();
        this.userEmail = UserProfile.getInstance().getUserEmail();
        this.phoneNumber = UserProfile.getInstance().getPhoneNumber();
        this.firstName = UserProfile.getInstance().getFirstName();
        this.lastName = UserProfile.getInstance().getLastName();
    }


    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}