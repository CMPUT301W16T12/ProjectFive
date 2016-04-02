package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Omar on 2/12/2016.
 */
public class UserProfileActivityTest extends ActivityInstrumentationTestCase2 {

    public UserProfileActivityTest() {
        super(UserProfileActivity.class);
    }

    public void testChangeContactInfo() throws Exception {
        UserProfileActivity upa = (UserProfileActivity) getActivity();

        try{
            upa.changeContactInfo("test@mail.com");

        }catch (DatabaseConnectException e){
            fail();
        }
    }
    public void testGetPassword() throws Exception {
        UserProfileActivity upa = (UserProfileActivity) getActivity();


    }


}