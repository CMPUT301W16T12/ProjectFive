package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by Omar on 2/12/2016.
 */
public class UserProfileTest extends ActivityInstrumentationTestCase2 {


    public UserProfileTest() {
        super(UserProfile.class);

    }

    public void testUpdateUserData(){
        UserProfile.getInstance().updateUserData("abc", "1234");
        assertEquals(UserProfile.getInstance().getUserName(), "abc");
    }


    public void testSetUserEmail() throws Exception {
        UserProfile.getInstance().setUserEmail("test@mail.com");
        assertEquals(UserProfile.getInstance().getUserEmail(), "test@mail.com");
    }
}