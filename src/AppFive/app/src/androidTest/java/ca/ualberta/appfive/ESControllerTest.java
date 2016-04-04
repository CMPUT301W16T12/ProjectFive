package ca.ualberta.appfive;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.test.ActivityInstrumentationTestCase2;


/**
 * Created by waiyi on 3/31/16.
 */
public class ESControllerTest extends ActivityInstrumentationTestCase2 {

    public ESControllerTest() {
        super(ESController.class);
    }
    private UserProfile user1 = UserProfile.getInstance();
    private UserProfile user2 = UserProfile.getInstance();
    private Book testbook1, testbook2, testbook3 ;
    Activity activity;
    final AppController ac = AppFiveApp.getAppController();

    //Populate the database with Users and Books
    @Override
    protected void setUp() throws Exception{
        ESController.AddUserTask addUserTask = new ESController.AddUserTask();

        user1.setUserName("ESCONTROLTESTER1");
        user1.setUserEmail("estester1@ualberta.ca");
        addUserTask.execute(user1);
        user2.setUserName("ESCONTROLTESTER1");
        user2.setUserEmail("estester2@ualberta.ca");
        addUserTask.execute(user2);

        testbook1 = new Book("testbook1","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        testbook2 = new Book("testbook2","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        testbook3 = new Book("testbook3","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        testbook2.setStatus(Book.Status.BIDDED);
        testbook3.setStatus(Book.Status.BORROWED);

        ac.addBook(testbook1);
        ac.addBook(testbook2);
        ac.addBook(testbook3);
    }

    //Remove test objects from database
    @Override
    protected  void tearDown() throws Exception{
        ESController.DeleteUserTask deleteUserTask = new ESController.DeleteUserTask();
        deleteUserTask.execute(user1);
        deleteUserTask.execute(user2);

        ac.deleteBook(0);
        ac.deleteBook(0);
        ac.deleteBook(0);
    }

    public void testExecute() throws Exception{
        UserProfile.getInstance().setUserName("omokdad");
        UserProfile.getInstance().setUserEmail("omokdad@yahoo.com");
        ESController.AddUserTask addUserTask = new ESController.AddUserTask();
        addUserTask.execute(UserProfile.getInstance());
        assertTrue(true);
    }

    public void testAddBookTask() throws Exception{
        

    }

    public void testGetBookTask() throws Exception {

    }

    public void testEditBookTask() throws Exception {

    }

    public void testAddUserTask() throws Exception {

    }

    public void testGetUserTask() throws Exception {

    }

    public void testGetUserProfileTask() throws Exception {

    }

    public void testEditUserTask() throws Exception {

    }

    public void testIsUserInDatabaseTask() throws Exception {

    }

    public void testGetBookByUserTask() throws Exception {

    }

    public void testGetBooksBorrowedByUserTask() throws Exception {

    }

    public void testGetBooksBiddedByUserTask() throws Exception {

    }

    public void testDeleteBookTask() throws Exception {

    }

}