package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Omar on 2/12/2016.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2 {

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    public void testLoginToApp() throws Exception {
        LoginActivity la = (LoginActivity) getActivity();

        try{
            la.loginToApp("abc");

        }catch (DatabaseConnectException e){
            fail();
        };
    }
}
