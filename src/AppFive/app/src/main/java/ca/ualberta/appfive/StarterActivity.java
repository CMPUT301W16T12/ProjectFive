package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Starter activity. Leads to login if the user is not logged in.
 */
public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: Check if user is logged in. If so, send to Home activity
        // Else got to login
        Intent intent = new Intent(StarterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
