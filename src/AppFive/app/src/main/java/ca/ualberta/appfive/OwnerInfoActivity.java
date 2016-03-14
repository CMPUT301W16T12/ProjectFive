package ca.ualberta.appfive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OwnerInfoActivity extends AppCompatActivity implements BView<BModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        String name = getIntent().getStringExtra("OWNER");
        String email = getIntent().getStringExtra("EMAIL");

        TextView userName = (TextView) findViewById(R.id.ownerName);
        TextView userEmail = (TextView) findViewById(R.id.ownerEmail);

        userName.setText(name);
        userEmail.setText(email);

    }

    @Override
    public void update(BModel model) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        FileParser parser = new FileParser(this.getApplicationContext());
        parser.saveInFile();
        fc.notifyViews();
    }

}
