package ca.ualberta.appfive;

/**
 * A model for holding Owner info of books
 */
public class OwnerInfo extends BModel<BView>{

    private String name;
    private String email;

    /**
     * This constructor is called when creating the Owner Info for the current user of the app.
     * @param userProfile The current user's profile data
     */
    public OwnerInfo(UserProfile userProfile) {
        super();
        this.name = userProfile.getUserName();
        this.email = userProfile.getUserEmail();
    }

    /**
     * This is called when creating a book's Owner Info
     * @param name Owner's User Name
     * @param email Owner's contact info
     */
    public OwnerInfo(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}