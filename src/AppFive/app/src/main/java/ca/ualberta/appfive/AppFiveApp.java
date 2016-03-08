package ca.ualberta.appfive;

import android.app.Application;

/**
 * The application class for the app.
 *
 * The code was borrowed from the FillerCreep app.
 * https://github.com/abramhindle/FillerCreepForAndroid
 */
public class AppFiveApp extends Application {
    // Singleton
    transient private static AppFiveApp appFiveApp = null;

    static AppFiveApp getAppFive() {
        if (appFiveApp == null) {
            appFiveApp = new AppFiveApp();
        }
        return appFiveApp;
    }

    // Singleton
    transient private static UserProfileController userProfileController = null;

    public static UserProfileController getuserProfileController() {
        if (userProfileController == null) {
            userProfileController = new UserProfileController(getAppFive());
        }
        return userProfileController;
    }
}
