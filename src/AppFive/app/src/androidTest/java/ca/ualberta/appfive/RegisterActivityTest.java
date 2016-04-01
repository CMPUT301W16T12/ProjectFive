package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Omar on 2/12/2016.
 */
public class RegisterActivityTest extends ActivityInstrumentationTestCase2 {
    public RegisterActivityTest() {
        super(RegisterActivity.class);
    }
    /*
    public void testRegisterNewUser() throws Exception {
        RegisterActivity ra = (RegisterActivity) getActivity();

        try{
            ra.registerNewUser("user", "first name", "last name", "password", "email");

        }catch (DatabaseConnectException e){
            fail();
        }
    }
    */
}
