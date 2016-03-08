package ca.ualberta.appfive;

/**
 * This is the master model class for the books
 */
public class AppFive extends BModel<BView>{

    private UserProfile userProfile;

    AppFive() {
        super();
        init();
    }

    private void init() {
        userProfile = UserProfile.getInstance();
    }

}
