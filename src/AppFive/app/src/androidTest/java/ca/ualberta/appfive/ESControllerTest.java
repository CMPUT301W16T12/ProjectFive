package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;


/**
 * Created by waiyi on 3/31/16.
 */
public class ESControllerTest extends ActivityInstrumentationTestCase2 {

    public ESControllerTest() {
        super(ESController.class);
    }

    public void testExecute() throws Exception{
        UserProfile.getInstance().setUserName("omokdad");
        UserProfile.getInstance().setUserEmail("omokdad@yahoo.com");
        ESController.AddUserTask addUserTask = new ESController.AddUserTask();
        addUserTask.execute(UserProfile.getInstance());
        assertTrue(true);
    }

}