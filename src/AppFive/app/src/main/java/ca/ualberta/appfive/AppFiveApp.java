package ca.ualberta.appfive;

import android.app.Application;

/**
 * The application class for the app.
 *
 * The code is referencing from the FillerCreep app.
 * https://github.com/abramhindle/FillerCreepForAndroid
 */
public class AppFiveApp extends Application {
    // Singleton
    transient private static AppFive appFive = null;

    static AppFive getAppFive() {
        if (appFive == null) {
            appFive = new AppFive();
        }
        return appFive;
    }

    // Singleton
    transient private static AppController appController = null;

    public static AppController getAppController() {
        if (appController == null) {
       appController = new AppController(getAppFive());
        }
        return appController;
    }
}
