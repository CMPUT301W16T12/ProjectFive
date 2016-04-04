package ca.ualberta.appfive;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import java.util.ArrayList;


/**
 * Created by waiyi on 3/31/16.
 */
public class ESControllerTest extends ActivityInstrumentationTestCase2 {

    public ESControllerTest() {
        super(ESController.class);
    }
    private UserProfile user1 = UserProfile.getInstance();
    private UserProfile user2 = UserProfile.getInstance();
    private UserProfile Add_user_tester = UserProfile.getInstance();
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
        testbook1 = new Book("testbook1","", "", "", ((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());

        user2.setUserName("ESCONTROLTESTER1");
        user2.setUserEmail("estester2@ualberta.ca");
        addUserTask.execute(user2);
        testbook2 = new Book("testbook2","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        testbook3 = new Book("testbook3", "", "", "", ((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
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
        ESController.AddBookTask addBookTask = new ESController.AddBookTask();
        Book newBook = new Book("New Book","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        addBookTask.execute(newBook);
        ArrayList<Book> books = ac.getMyBookArray();
        assertTrue(books.contains(newBook));

        ac.deleteBook(ac.getMyBookArray().size()-1);
    }

    public void testEditBookTask() throws Exception {
        ESController.EditBookTask editBookTask = new ESController.EditBookTask();
        Book newBook = new Book("New Book","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        Book editBook = new Book("Edit Book","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        ac.addBook(newBook);

        newBook.setTitle(editBook.getTitle());
        editBookTask.execute(editBook);
        assertEquals(ac.getBook(ac.getMyBookArray().size() - 1).getTitle(), "Edit Book");

        ac.deleteBook(ac.getMyBookArray().size() - 1);
    }

    public void testAddUserTask() throws Exception {
        ESController.AddUserTask addUserTask = new ESController.AddUserTask();
        Add_user_tester.setUserName("Add User Tester");
        addUserTask.execute(Add_user_tester);

        ESController.GetUserTask getUserTask = new ESController.GetUserTask();
        assertNotNull(getUserTask.execute("Add_user_tester"));
    }

    public void testGetUserTask() throws Exception {
        ESController.GetUserTask getUserTask = new ESController.GetUserTask();
        assertNotNull(getUserTask.equals("user1"));
    }


    public void testEditUserTask() throws Exception {
        ESController.EditUserTask editUserTask = new ESController.EditUserTask();
        user1.setUserEmail("newuser1email@test.org");
        editUserTask.execute(user1);

        ESController.GetUserTask getUserTask = new ESController.GetUserTask();
        assertEquals(ac.getUserEmail(),"newuser1email@test.org");
    }

    public void testIsUserInDatabaseTask() throws Exception {
        ESController.IsUserInDatabaseTask isUserInDatabaseTask = new ESController.IsUserInDatabaseTask();
        assertEquals(isUserInDatabaseTask.execute("user1"), Boolean.TRUE);
        assertEquals(isUserInDatabaseTask.execute("qwetrytitopppdskdkf"), Boolean.FALSE);

    }
    public void testGetBookByUserTask() throws Exception {
        ESController.GetBooksbyUserTask getBooksbyUserTask = new ESController.GetBooksbyUserTask();
        ArrayList<Book> test = new ArrayList<Book>();   //Copy of user1's books
        test.add(testbook1);

        getBooksbyUserTask.execute("user1");
        ArrayList<Book> result = ac.getMyBookArray();
        assertEquals(test, result);

    }

    public void testGetBooksBorrowedByUserTask() throws Exception {
        ESController.GetBooksBorrowedbyUserTask getBooksBorrowedbyUserTask = new ESController.GetBooksBorrowedbyUserTask();

        ArrayList<Book> test = new ArrayList<Book>();
        test.add(testbook3);

        getBooksBorrowedbyUserTask.execute();
        ArrayList<Book> result = ac.getMyBookArray();
        assertEquals(test, result);

    }

    public void testGetBooksBidsByUserTask() throws Exception {
        ESController.GetBooksBidsbyUserTask getBooksBidsbyUserTask = new ESController.GetBooksBidsbyUserTask();
        ArrayList<Book> test = new ArrayList<Book>();
        test.add(testbook2);

        getBooksBidsbyUserTask.execute();
        ArrayList<Book> result = ac.getMyBookArray();
        assertEquals(test, result);

    }

    public void testDeleteBookTask() throws Exception {
        ESController.DeleteBookTask deleteBookTask = new ESController.DeleteBookTask();
        Book newBook = new Book("New Book","","","",((BitmapDrawable)activity.getApplicationContext().getDrawable(R.drawable.not_available)).getBitmap());
        ac.addBook(newBook);

        deleteBookTask.execute(newBook);
        assertFalse(ac.getMyBookArray().contains(newBook));
    }

}