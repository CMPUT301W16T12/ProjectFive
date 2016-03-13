package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Activity for editing the user's profile
 */
public class EditProfileActivity extends AppCompatActivity implements BView<BModel>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Call to update the user's email
     * @param email New user email
     * @throws DatabaseConnectException
     */
    protected void commitProfileEdits(String email) throws DatabaseConnectException{
        throw new DatabaseConnectException();
    }

    @Override
    public void update(BModel model) {

    }
}
