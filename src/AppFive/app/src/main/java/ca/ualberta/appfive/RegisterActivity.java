package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity for making a new user account.
 */
public class RegisterActivity extends AppCompatActivity implements BView<BModel>{
    EditText etUserName, etFirstName, etLastName, etEmail, etPassword, etPhoneNumber;
    String userName, firstName, lastName, email, password, phoneNumber;
    Button bRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();
		
        final Button bRegister = (Button) findViewById(R.id.save);

		
        etUserName = (EditText) findViewById(R.id.regName);
        etFirstName = (EditText) findViewById(R.id.regFirstName);
        etLastName = (EditText) findViewById(R.id. regLastName);
        etEmail = (EditText) findViewById(R.id.regEmail);
        etPassword = (EditText) findViewById(R.id.regPassword);
        etPhoneNumber = (EditText) findViewById(R.id.regPhoneNumber);

        // converting to string for saving as JSON object
        userName = etUserName.getText().toString();
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        phoneNumber = etPhoneNumber.getText().toString();


        // for clicking register button, check the register name whether it is in the database
        bRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // is user in database task; null = no connection, true = user is in database, false = user is not in database, free to register
                // if is null, means theres no connection, can't register, cant check if theres user
                // not null and returns false, user is free to register with username

                if ((etUserName.getText().toString().equals(""))){
                    // when username field is empty
                    Toast.makeText(getApplicationContext(),
                            "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
                }

                // saving registration as JSON in rest api elastic search database
                // need to be first chekcing that their username doesnt exist yet
                // if it doesnt just re loop, dont submit info, change name
				ESController.IsUserInDatabaseTask isUserInDatabaseTask = new ESController.IsUserInDatabaseTask();

                //TODO: if result is true, send toast and do not submit
                // TODO: else, add user

                isUserInDatabaseTask.execute(regName.getText().toString());
                try {
                    Boolean result = isUserInDatabaseTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                // app controller set
                ac.setUserName(userName);
                ac.setFirstName(firstName);
                ac.setLastName(lastName);
                ac.setUserEmail(email);
                ac.setUserPassword(password);
                ac.setPhoneNumber(phoneNumber);

            }
        });

    }

    /**
     * This method registers new user.
     * @param userName Unique user name
     * @param firstName User's first Name
     * @param lastName User's last Name
     * @param password User's password
     * @param email User's email
     * @throws DatabaseConnectException
     */
    protected void registerNewUser(String userName, String firstName, String lastName, String password, String email)
            throws DatabaseConnectException {
        throw new DatabaseConnectException();
    }

    @Override
    public void update(BModel model) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        fc.notifyViews();
    }

}
