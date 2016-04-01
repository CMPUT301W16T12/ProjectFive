package ca.ualberta.appfive;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;

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
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        RegisterActivity ra = (RegisterActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));
    }

    public void testtoHomeActivity() {
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        HomeActivity ha = (HomeActivity) getActivity();
        ViewAsserts.assertOnScreen(ha.getWindow().getDecorView(), activity.findViewById(R.id.TVHomeTitle));
    }

    public void testtoMyBooksActivity() {
        ((Button) activity.findViewById(R.id.myBooksButton)).performClick();
        MyBooksActivity ra = (MyBooksActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVMyBooksTitle));

    }

    public void testtoMyBiddedActivity() {
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        MyBiddedActivity ra = (MyBiddedActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));

    }

    public void testtoUserProfileActivity() {
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        UserProfileActivity ra = (UserProfileActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));

    }

    public void testtoSearchActivity() {
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        SearchActivity ra = (SearchActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));

    }

    public void testtoOwnerInfoActivity(){
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        OwnerInfoActivity ra = (OwnerInfoActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));

    }
    public void testtoBookDisplayActivity(){
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        BookDisplayActivity ra = (BookDisplayActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));

    }
    public void testtoEditBookActivity() {
        ((Button) activity.findViewById(R.id.addBookButton)).performClick();
        EditBookActivity ra = (EditBookActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.editBookButton));

    }

    public void testtoEditProfileActivity() {
        ((Button) activity.findViewById(R.id.registerButton)).performClick();
        EditProfileActivity ra = (EditProfileActivity) getActivity();
        ViewAsserts.assertOnScreen(ra.getWindow().getDecorView(), activity.findViewById(R.id.TVRegisterTitle));

    }

}
