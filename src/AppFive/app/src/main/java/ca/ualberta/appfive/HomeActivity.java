package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This activity displays the home page of app
 */
public class HomeActivity extends AppCompatActivity implements BView<BModel>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();


        // bodyText = (EditText) findViewById(R.id.body);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        Button myProfileButton = (Button) findViewById(R.id.myProfileButton);
        Button myBookButton = (Button) findViewById(R.id.myBooksButton);
        Button biddedButton = (Button) findViewById(R.id.biddedButton);
        Button borrowedButton = (Button) findViewById(R.id.borrowedButton);
        ListView notificationsLV = (ListView) findViewById(R.id.LVNotifications);

        ArrayAdapter<String> nla = new ArrayAdapter<String>(this, R.layout.notification_entry,UserProfile.getInstance().getNotifications());

        notificationsLV.setAdapter(nla);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
        myBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HomeActivity.this, MyBooksActivity.class);
                startActivity(intent);

            }
        });

        //TODO: ListView of Event Histroy

    }

    @Override
    protected void onStart() {
        super.onStart();

        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.loadFromFile();
    }

    @Override
    public void update(BModel model) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
        fc.notifyViews();
    }


}
