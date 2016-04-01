package ca.ualberta.appfive;

/**
 * A model for holding Owner info of books
 */
public class OwnerInfo{

    private String name;
    private String email;

    /**
     * This constructor is called when creating the Owner Info for the current user of the app.
     */
    public OwnerInfo() {
        super();
        this.name = UserProfile.getInstance().getUserName();
        this.email = UserProfile.getInstance().getUserEmail();
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