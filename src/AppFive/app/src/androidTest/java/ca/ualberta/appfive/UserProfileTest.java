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
        UserProfile up = UserProfile.getInstance();
        up.updateUserData("abc", "1234");
        assertEquals(up.getUserName(), "abc");
    }


    public void testSetUserEmail() throws Exception {
        UserProfile up = UserProfile.getInstance();
        up.setUserEmail("test@mail.com");
        assertEquals(up.getUserEmail(), "test@mail.com");
    }
}