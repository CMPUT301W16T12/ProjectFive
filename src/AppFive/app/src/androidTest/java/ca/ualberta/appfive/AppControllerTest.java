package ca.ualberta.appfive;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by jjdaz on 2016-03-13.
 */
//NOTE: TESTS AFFECT EACH OTHER, TESTS SHOULD SUCCEED WHEN RUN INDIVIDUALLY.
//TODO: Change 'testGetBooks' and 'testGetBook' after implementing datatbase.
public class AppControllerTest extends ActivityInstrumentationTestCase2 {

    public AppControllerTest() {
        super(AppController.class);
    }

    public void testGetUserName() throws Exception {
        UserProfile.setUserName("asdf");
        AppController ac = AppFiveApp.getAppController();
        assertEquals(ac.getUserName(), "asdf");
    }

    public void testSetUserName() throws Exception {
        UserProfile.setUserName("asdf");
        AppController ac = AppFiveApp.getAppController();
        assertNotNull(ac.getUserName());
    }

    public void testGetUserEmail() throws Exception {
        UserProfile.setUserEmail("aaaa@bbb.cc");
        AppController ac = AppFiveApp.getAppController();
        assertEquals(ac.getUserEmail(),"aaaa@bbb.cc");
    }

    public void testSetUserEmail() throws Exception {
        UserProfile.setUserEmail("aaaa@bbb.cc");
        AppController ac = AppFiveApp.getAppController();
        assertNotNull(ac.getUserEmail());
    }
    /*
    public void testGetBooks() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        Book testbook2 = new Book("Aa","Bb","Cc","Dd");
        Book testbook3 = new Book("AA","BB","CC","DD");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        ac.addBook(testbook2);
        ac.addBook(testbook3);

        ArrayList<Book> arrayList = new ArrayList<>();
        arrayList.add(testbook1);
        arrayList.add(testbook2);
        arrayList.add(testbook3);

        assertEquals(ac.getMyBookArray().size(), arrayList.size());

        ac.deleteBook(0);
        ac.deleteBook(0);
        ac.deleteBook(0);

    }

    public void testGetBook() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        assertEquals(ac.getBook(0).getTitle(), testbook1.getTitle());

        ac.deleteBook(0);
    }

    public void testAddBook() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        assertEquals(ac.getMyBookArray().size(), 1);

        ac.deleteBook(0);
    }

    public void testDeleteBook() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        ac.deleteBook(0);
        assertEquals(ac.getMyBookArray().size(), 0);

    }

    public void testEditBook() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        Book testbook2 = new Book("DD","CC","BB","AA");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        ac.editBook(0, testbook2);
        assertEquals(ac.getBook(0).getTitle(),testbook2.getTitle());

        ac.deleteBook(0);

    }

    public void testGetMyBooks() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        Book testbook2 = new Book("Aa","Bb","Cc","Dd");
        Book testbook3 = new Book("AA","BB","CC","DD");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        ac.addBook(testbook2);
        ac.addBook(testbook3);

        ArrayList<Book> arrayList = new ArrayList<>();
        arrayList.add(testbook1);
        arrayList.add(testbook2);
        arrayList.add(testbook3);

        assertEquals(ac.getMyBookArray().size(), arrayList.size());

        ac.deleteBook(0);
        ac.deleteBook(0);
        ac.deleteBook(0);
    }

    public void testGetMyBook() throws Exception {
        Book testbook1 = new Book("aa","bb","cc","dd");
        AppController ac = AppFiveApp.getAppController();
        ac.addBook(testbook1);
        assertEquals(ac.getBook(0).getTitle(), testbook1.getTitle());

        ac.deleteBook(0);
    }
    */
}