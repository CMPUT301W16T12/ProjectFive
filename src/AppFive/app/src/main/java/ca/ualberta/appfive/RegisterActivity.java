package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity for making a new user account.
 */
public class RegisterActivity extends AppCompatActivity implements BView<BModel>{
    EditText etUserName, etFirstName, etLastName, etEmail, etPassword,etConfirmPassword, etPhoneNumber;
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

		Button bRegister = (Button) findViewById(R.id.saveRegistrationButton);
		
        etUserName = (EditText) findViewById(R.id.ETUsernameRegistration);
        etFirstName = (EditText) findViewById(R.id.ETFirstNameRegistration);
        etLastName = (EditText) findViewById(R.id.ETLastNameRegistration);
        etEmail = (EditText) findViewById(R.id.ETEmailRegistration);
        etPassword = (EditText) findViewById(R.id.ETPasswordRegistration);
        etConfirmPassword = (EditText) findViewById(R.id.ETMatchPasswordRegistration);
        etPhoneNumber = (EditText) findViewById(R.id.ETPhoneNumberRegistration);

        // converting to string for saving as JSON object
        userName = etUserName.getText().toString();
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        phoneNumber = etPhoneNumber.getText().toString();


        // when user clicks register button
        bRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // is user in database task; null = no connection, true = user is in database, false = user is not in database, free to register
                // if is null, means theres no connection, can't register, cant check if theres user

                // THIS IS ALL PSEUDO FOR THE FUTURE ESCONTROLLER
                // result = ESCONTROLLER. IsUserInDatabaseTask()

                //if result = null {
                //    Toast.makeText(getApplicationContext(),
                //            "Weak Connection, can't register at this time", Toast.LENGTH_SHORT).show();
                //} else if result = true {
                //    Toast.makeText(getApplicationContext(),
                //            "Username already exists, cannot register", Toast.LENGTH_SHORT).show();
                //} else {
                    // user is not in database, free to register
                    //continue
                //}

                
                //checking to make sure username is not empty
                if ((etUserName.getText().toString().equals(""))){
                    // when username field is empty
                    Toast.makeText(getApplicationContext(),
                            "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
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



       // saveInFile();

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

        // adding valid registration information to database
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
