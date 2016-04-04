package ca.ualberta.appfive;

import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by jjdaz on 2016-03-13.
 */
//General tests for use cases here
//NOTE: TESTS AFFECT EACH OTHER, TESTS SHOULD SUCCEED WHEN RUN INDIVIDUALLY.
public class AppControllerTest extends ActivityInstrumentationTestCase2 {

    public AppControllerTest() {
        super(AppController.class);
    }
    //Use Cases: User Profile (Set and Edit)
    public void testGetUserName() throws Exception {
        UserProfile.getInstance().setUserName("asdf");
        AppController ac = AppFiveApp.getAppController();
        assertEquals(ac.getUserName(), "asdf");
    }

    public void testSetUserName() throws Exception {
        UserProfile.getInstance().setUserName("asdf");
        AppController ac = AppFiveApp.getAppController();
        assertNotNull(ac.getUserName());
    }

    public void testGetUserEmail() throws Exception {
        UserProfile.getInstance().setUserEmail("aaaa@bbb.cc");
        AppController ac = AppFiveApp.getAppController();
        assertEquals(ac.getUserEmail(), "aaaa@bbb.cc");
    }

    public void testSetUserEmail() throws Exception {
        UserProfile.getInstance().setUserEmail("aaaa@bbb.cc");
        AppController ac = AppFiveApp.getAppController();
        assertNotNull(ac.getUserEmail());
    }

    //Use Case: Add/Delete/Edit Book
    public void testAddBook() throws Exception {
        Bitmap thumbnail = null;
        AppController ac = AppFiveApp.getAppController();
        Book testbook = new Book("title", "author", "genre", "description", thumbnail);
        ac.addBook(testbook);
        assertEquals(ac.getMyBook(0), testbook);
        ac.deleteBook(0);
    }


    public void testDeleteBook() throws Exception {
        Bitmap thumbnail = null;
        AppController ac = AppFiveApp.getAppController();
        Book testbook = new Book("title", "author", "genre", "description", thumbnail);
        ac.addBook(testbook);
        ac.deleteBook(0);
        assertEquals(ac.getMyBookArray().size(), 0);

    }

    public void testEditBook() throws Exception {
        Bitmap thumbnail = null;
        AppController ac = AppFiveApp.getAppController();
        Book testbook = new Book("title", "author", "genre", "description", thumbnail);
        Book newbook = new Book("newtitle", "newauthor", "newgenre", "newdescription", thumbnail);
        ac.addBook(testbook);
        ac.editBook(0, newbook, 0);
        //Different book object is created in the process of edit book, thus testing for same content.
        assertEquals(ac.getMyBook(0).getTitle(), newbook.getTitle());
        ac.deleteBook(0);

    }

}


