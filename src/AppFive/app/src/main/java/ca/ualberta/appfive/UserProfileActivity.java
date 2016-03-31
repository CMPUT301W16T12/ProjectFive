package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity for displaying the user's data
 * Includes an option to update the profile
 */
public class UserProfileActivity extends AppCompatActivity implements BView<BModel>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        TextView userEmail = (TextView) findViewById(R.id.TVEmail);
        TextView firstName = (TextView) findViewById(R.id.TVFirstName);
        TextView lastName = (TextView) findViewById(R.id.TVLastName);
        TextView password = (TextView) findViewById(R.id.TVPassword);
        TextView phoneNumber = (TextView) findViewById(R.id.TVPhoneNumber);
        TextView userName = (TextView) findViewById(R.id.TVUsername);

        userName.setText(ac.getUserName());
        userEmail.setText(ac.getUserEmail());
        firstName.setText(ac.getFirstName());
        lastName.setText(ac.getLastName());
        password.setText(ac.getUserPassword());
        phoneNumber.setText(ac.getPhoneNumber());


        /**
         * Button for opening edit profile activity.
         */
        Button editButton = (Button) findViewById(R.id.editbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
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
     * @param model The User's Profile data
     */
    @Override
    public void update(BModel model) {
        AppController ac = AppFiveApp.getAppController();
        TextView userEmail = (TextView) findViewById(R.id.TVEmail);
        TextView firstName = (TextView) findViewById(R.id.TVFirstName);
        TextView lastName = (TextView) findViewById(R.id.TVLastName);
        TextView password = (TextView) findViewById(R.id.TVPassword);
        TextView phoneNumber = (TextView) findViewById(R.id.TVPhoneNumber);
        TextView userName = (TextView) findViewById(R.id.TVUsername);

        userName.setText(ac.getUserName());
        userEmail.setText(ac.getUserEmail());
        firstName.setText(ac.getFirstName());
        lastName.setText(ac.getLastName());
        password.setText(ac.getUserPassword());
        phoneNumber.setText(ac.getPhoneNumber());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        FileParser parser = new FileParser(this.getApplicationContext());
        parser.saveInFile();
        fc.notifyViews();
    }

}
