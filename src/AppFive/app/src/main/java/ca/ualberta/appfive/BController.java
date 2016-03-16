package ca.ualberta.appfive;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Controller class is implemented in this.
 *
 */
public interface BController {

    public String getUserName();

    public void setUserName(String userName);

    public String getUserEmail();

    public void setUserEmail(String email);

    public ArrayList<Book> getBookArray();

    public void setBookArray(ArrayList<Book> newBooks);

    public Book getBook(int index);

    public void addBook(Book book);

    public void deleteBook(int index);

    public void editBook(int index, Book newBook);
    public ArrayList<Book> getMyBookArray();

    public void setMyBookArray(ArrayList<Book> myNewBooks);
    public Book getMyBook(int index);

    //TODO: maybe adding this to AppController??
    public Bitmap getMapBitmap();
}

