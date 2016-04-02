package ca.ualberta.appfive;

import android.os.Bundle;
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


        // Because Username is only non editable user info
        TextView userName = (TextView) findViewById(R.id.TVUsername);
        userName.setText(ac.getUserName());

        final EditText editUserEmail = (EditText) findViewById(R.id.ETEmail);
        final EditText editFirstName = (EditText) findViewById(R.id.ETFirstName);
        final EditText editLastName = (EditText) findViewById(R.id.ETLastName);
        final EditText editPhoneNumber = (EditText) findViewById(R.id.ETPhoneNumber);

        editUserEmail.setText(ac.getUserEmail(), TextView.BufferType.EDITABLE);
        editFirstName.setText(ac.getFirstName(), TextView.BufferType.EDITABLE);
        editLastName.setText(ac.getLastName(), TextView.BufferType.EDITABLE);
        editPhoneNumber.setText(ac.getPhoneNumber(), TextView.BufferType.EDITABLE);


        Button saveProfInfo = (Button) findViewById(R.id.saveProfileEditButton);

        saveProfInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Save entered new data
                String newEmail = editUserEmail.getText().toString();
                String newFirstName = editFirstName.getText().toString();
                String newLastName = editLastName.getText().toString();
                String newPhoneNumber = editPhoneNumber.getText().toString();

                ac.setUserEmail(newEmail);
                ac.setFirstName(newFirstName);
                ac.setLastName(newLastName);
                ac.setPhoneNumber(newPhoneNumber);

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
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
        fc.notifyViews();
    }

}
