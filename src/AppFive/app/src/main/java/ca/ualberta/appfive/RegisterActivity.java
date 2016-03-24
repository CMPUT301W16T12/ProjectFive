package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Activity for making a new user account.
 */
public class RegisterActivity extends AppCompatActivity implements BView<BModel>{
    EditText etUserName, etFirstName, etLastName, etEmail, etPassword;
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

        etUserName = (EditText) findViewById(R.id.regName);
        etFirstName = (EditText) findViewById(R.id.regFirstName);
        etLastName = (EditText) findViewById(R.id. regLastName);
        etEmail = (EditText) findViewById(R.id.regEmail);
        etPassword = (EditText) findViewById(R.id.regPassword);

        //bRegister.setOnClickListener(this);



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
    protected void registerNewUser(String userName, String firstName, String lastName String password, String email)
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
