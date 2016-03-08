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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * This is the activity when the user logs in
 */
public class LoginActivity extends AppCompatActivity implements BView<UserProfile>{
    // TODO: pls correct this username -- should check database profile
    private static final String USERNAMEDATA = "usernameData.sav";
    private EditText usernameInput;

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
                String user = usernameInput.getText().toString();
                //TODO: check database of name
                // TODO: if name exist, login by checking password,
                // TODO: if name not exist, throw in exception (toast)
                try {
                    loginToApp(user);
                } catch (DatabaseConnectException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Button registerButton = (Button) findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                String user = usernameInput.getText().toString();
                //TODO: need to pass the usernameInput to Register
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Call when logging in to app
     * @param userName the entered username
     * @throws DatabaseConnectException
     */
    protected void loginToApp(String userName) throws DatabaseConnectException {
        throw new DatabaseConnectException();
    }


    @Override
    public void update(UserProfile model) {

    }
}
