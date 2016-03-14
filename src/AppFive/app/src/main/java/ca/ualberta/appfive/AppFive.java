package ca.ualberta.appfive;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * This is the master model class for the books
 */
public class AppFive extends BModel<BView>{

    private UserProfile userProfile;
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Book> myBooks = new ArrayList<Book>();

    AppFive() {
        super();
        init();
    }

    private void init() {
        this.userProfile = UserProfile.getInstance();
    }

    public String getUserName() {
        return UserProfile.getUserName();
    }

    public void setUserName(String userName) {
        UserProfile.setUserName(userName);
        notifyViews();
    }

    public String getUserEmail() {
        return UserProfile.getUserEmail();
    }

    public void setUserEmail(String email) {
        UserProfile.setUserEmail(email);
        notifyViews();
    }

    public ArrayList<Book> getBookArray(){
       return books;
    }

    public void setBookArray(ArrayList<Book> newBooks){
        books = newBooks;
    }

    public Book getBook(int index){
        return books.get(index);
    }

    public void addBook(Book book) {
        //books.add(book);
        myBooks.add(book);
        notifyViews();
        //TODO update database
        // try catch block to sync, follow offline user story if fail
    }

    public void deleteBook(int index) {
        //books.remove(index);
        myBooks.remove(index);
        notifyViews();
        // TODO sync up with database
        // try catch block to sync, follow offline user story if fail
    }

    public void editBook(int index, Book newBook) {
        // to edit a book construct a new book with the new attributes
        // and call this function with the index of the old instance,
        // and the new book to replace it.

        Book oldBook = getMyBook(index);
        oldBook.setDescription(newBook.getDescription());
        oldBook.setGenre(newBook.getGenre());
        oldBook.setTitle(newBook.getTitle());
        notifyViews();

        // TODO sync up with database
        // try catch block to sync, follow offline user story if fail
    }

    public ArrayList<Book> getMyBookArray(){
        return myBooks;
    }

    public void setMyBookArray(ArrayList<Book> myNewBooks){
        myBooks = myNewBooks;
    }

    public Book getMyBook(int index){
        return myBooks.get(index);
    }


}
