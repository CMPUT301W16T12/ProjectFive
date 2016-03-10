package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * This is the activity when the user logs in
 */
public class LoginActivity extends AppCompatActivity implements BView<UserProfile>{
    /**
     * This string is the location of saved userdata.
     * This should be referenced when device is offline.
     * This might be referenced for skipping login
     * This should be updated when user logs in, or when user updates data
     */
    private static final String USERNAMEDATA = "usernameData.sav";
    private EditText usernameInput;

    private String username;
    private String passwd = "";
    private String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameInput = (EditText) findViewById(R.id.userName);


        Button logInButton = (Button) findViewById(R.id.logIn);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                username = usernameInput.getText().toString();

                try {
                    // Poll database for username and update model
                    loginToApp(username);
                    //Go to home activity if successful
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } catch (DatabaseConnectException e) {
                    //TODO: Read user info from Prefs
                    // Go to home activity if parsing user data from file successful
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } catch (NotFoundException e) {
                    //Show an error if name is not found in database
                    Toast notFoundToast = Toast.makeText(LoginActivity.this,R.string.user_not_found,
                            Toast.LENGTH_LONG);
                    notFoundToast.show();
                }

            }
        });

        Button registerButton = (Button) findViewById(R.id.register);

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
     * @param userName the entered username
     * @throws DatabaseConnectException
     */
    protected void loginToApp(String userName) throws DatabaseConnectException, NotFoundException {
        //TODO: Connect to database and verify user, get user data

        //Update our model
        update(UserProfile.getInstance());

    }

    /**
     * This method updates the model by putting the data we found into it.
     * @param model A model object
     */
    @Override
    public void update(UserProfile model) {
        model.setUserName(username);
        model.setUserEmail(userEmail);
    }
}
