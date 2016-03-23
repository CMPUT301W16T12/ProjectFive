package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Omar on 2/12/2016.
 */
public class EditProfileActivityTest extends ActivityInstrumentationTestCase2 {

    public EditProfileActivityTest() {
        super(EditProfileActivity.class);
    }

    public void testCommitProfileEdits() throws Exception {
        EditProfileActivity epa = (EditProfileActivity) getActivity();

        try{
            epa.commitProfileEdits("Test@mail.com");

        }catch (DatabaseConnectException e){
            fail();
        }
    }
}