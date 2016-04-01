package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity for editing the user's profile.
 */
public class EditProfileActivity extends AppCompatActivity implements BView<BModel>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        final TextView userName = (TextView) findViewById(R.id.usernameedit);
        userName.setText(ac.getUserName());

        final EditText editUserEmail = (EditText) findViewById(R.id.useremailedit);
        editUserEmail.setText(ac.getUserEmail(), TextView.BufferType.EDITABLE);

        Button saveProfInfo = (Button) findViewById(R.id.saveprofinfo);
        saveProfInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save entered email
                String newEmail = editUserEmail.getText().toString();
                ac.setUserEmail(newEmail);

                // TODO: check: retreive by username from database only after clicked on save
                // TODO: need to save the edit to database
                // getting username from database
                //ESController.GetUserTask getUserTask = new ESController.GetUserTask();
                //getUserTask.execute(userName.toString());

                // TODO: how to save the edit to database??
                ac.editUserInDB();


                finish();
            }
        });


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
