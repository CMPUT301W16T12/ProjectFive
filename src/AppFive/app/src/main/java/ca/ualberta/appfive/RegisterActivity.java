package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Activity for making a new user account
 */
public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // saveInFile();

    }

    /**
     *
     * @param userName Unique user name
     * @param password User's password
     * @param email User's email
     * @throws DatabaseConnectException
     */
    protected void registerNewUser(String userName, String password, String email)
            throws DatabaseConnectException {
        throw new DatabaseConnectException();
    }
    //TODO: FIXME: need save new username to database
 /**   private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(USERNAMEDATA, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(usernameInput, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }
*/
}
