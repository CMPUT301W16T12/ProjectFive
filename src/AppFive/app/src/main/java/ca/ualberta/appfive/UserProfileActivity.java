package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Activity for displaying the user's data
 * Includes an option to update the profile
 */
public class UserProfileActivity extends AppCompatActivity implements BView<UserProfile>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * For changing the user's contact information
     * @param newEmail Updated contact information
     * @throws DatabaseConnectException when device is offline
     */
    protected void changeContactInfo(String newEmail) throws DatabaseConnectException{
        throw new DatabaseConnectException();

    }

    /**
     * When the User's profile is updated, update the view
     * @param userProfile The User's Profile data
     */
    @Override
    public void update(UserProfile userProfile) {

    }
}
