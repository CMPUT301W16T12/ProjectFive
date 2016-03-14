package ca.ualberta.appfive;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
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

    public void testtoRegisterActivity() throws Exception {
        ((Button) activity.findViewById(R.id.register)).performClick();
        RegisterActivity ra = (RegisterActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));
    }

    public void testtoHomeActivity() {
        ((Button) activity.findViewById(R.id.register)).performClick();
        HomeActivity ha = (HomeActivity) getActivity();
        ViewAsserts.assertOnScreen(ha.getWindow().getDecorView(), activity.findViewById(R.id.homeTitle));
    }

    public void testtoMyBooksActivity() {
        ((Button) activity.findViewById(R.id.myBook)).performClick();
        MyBooksActivity ra = (MyBooksActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.textView6));

    }

    public void testtoMyBiddedActivity() {
        ((Button) activity.findViewById(R.id.register)).performClick();
        MyBiddedActivity ra = (MyBiddedActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));

    }

    public void testtoUserProfileActivity() {
        ((Button) activity.findViewById(R.id.register)).performClick();
        UserProfileActivity ra = (UserProfileActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));

    }

    public void testtoSearchActivity() {
        ((Button) activity.findViewById(R.id.register)).performClick();
        SearchActivity ra = (SearchActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));

    }

    public void testtoOwnerInfoActivity(){
        ((Button) activity.findViewById(R.id.register)).performClick();
        OwnerInfoActivity ra = (OwnerInfoActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));

    }
    public void testtoBookDisplayActivity(){
        ((Button) activity.findViewById(R.id.register)).performClick();
        BookDisplayActivity ra = (BookDisplayActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));

    }
    public void testtoEditBookActivity() {
        ((Button) activity.findViewById(R.id.addbookbutton)).performClick();
        EditBookActivity ra = (EditBookActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.editBook));

    }

    public void testtoEditProfileActivity() {
        ((Button) activity.findViewById(R.id.register)).performClick();
        EditProfileActivity ra = (EditProfileActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.saveReg));

    }

}
