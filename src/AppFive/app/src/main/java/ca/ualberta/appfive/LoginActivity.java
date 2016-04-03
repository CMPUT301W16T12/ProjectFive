package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This is the activity when the user logs in
 */
public class LoginActivity extends AppCompatActivity implements BView<BModel>{
    /**
     * This string is the location of saved userdata.
     * This should be referenced when device is offline.
     * This might be referenced for skipping login
     * This should be updated when user logs in, or when user updates data
     */
    private EditText usernameInput;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);

        usernameInput = (EditText) findViewById(R.id.ETUserNameLogin);

        Button logInButton = (Button) findViewById(R.id.logInButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                username = usernameInput.getText().toString();
                // LOGIN BUTTON ACTIVITY


                    loginToApp(username);

            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);

        // If the register button is clicked, head straight to the RegisterActivity
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Call when logging in to app.
     * Connects online, verifies user, gets user data
     * @param userName the entered username     */
    protected void loginToApp(String userName) {
        //TODO: Connect to database and verify user, get user data
        AppController ac = AppFiveApp.getAppController();
        Boolean result = ac.isUserInDataBase(userName);
        //Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();
        if (result) {
            ac.getMyBooksFromDB(userName);
            ac.getUserProfile(userName);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "User does not exist, please register first.", Toast.LENGTH_SHORT).show();
        }
        //Update our model

    }

    /**
     * This method updates the model by putting the data we found into it.
     * @param model A model object
     */
    @Override
    public void update(BModel model ) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        fc.notifyViews();
    }
}
