package ca.ualberta.appfive;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jjdaz on 2016-03-14.
 */
public class LoginActivityUITest extends ActivityInstrumentationTestCase2 {

    public LoginActivityUITest() {
        super(LoginActivity.class);
    }
    Instrumentation instrumentation;
    Activity activity;

    protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();
    }

    public void toRegisterActivityTest() {
        ((Button) activity.findViewById(R.id.register)).performClick();


    }

    public void toHomeActivityTest() {

    }

    public void toMyBooksActivityTest() {

    }

    public void toMyBiddedActivityTest() {

    }

    public void toUserProfileActivityTest() {

    }

    public void toSearchActivityTest() {

    }

    public void toOwnerInfoActivityTest(){

    }
    public void toEditBookActivityTest() {

    }

    public void toEditProfileActivityTest() {

    }

}
