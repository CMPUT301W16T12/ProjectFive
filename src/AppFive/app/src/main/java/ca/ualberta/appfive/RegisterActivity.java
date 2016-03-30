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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        final Button registerButton = (Button) findViewById(R.id.save);

        final EditText regName = (EditText) findViewById(R.id.regName);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESController.IsUserInDatabaseTask isUserInDatabaseTask = new ESController.IsUserInDatabaseTask();

                // uProfile = new ArrayList<>();
                //uProfile.add(UserProfile.getInstance());

                isUserInDatabaseTask.execute(regName.getText().toString());
                try {
                    Boolean result = isUserInDatabaseTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            //TODO: if result is true, send toast and do not submit
                // TODO: else, add user

            }
        });
    }

    /**
     * This method registers new user.
     * @param userName Unique user name
     * @param password User's password
     * @param email User's email
     * @throws DatabaseConnectException
     */
    protected void registerNewUser(String userName, String password, String email)
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
