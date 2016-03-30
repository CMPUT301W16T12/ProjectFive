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
 * It includes methods for handling user profile and books.
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

    public String getUserPassword() {
        return UserProfile.getUserPassword();
    }

    public void setUserPassword(String password){
        UserProfile.setUserPassword(password);
        notifyViews();
    }

    public String getFirstName() {
        return UserProfile.getFirstName();
    }

    public void setFirstName(String firstName){
        UserProfile.setFirstName(firstName);
        notifyViews();
    }

    public String getLastName() {
        return UserProfile.getLastName();
    }

    public void setLastName(String lastName){
        UserProfile.setLastName(lastName);
        notifyViews();
    }

    public String getPhoneNumber() {
        return UserProfile.getPhoneNumber();
    }

    public void setPhoneNumber(String PhoneNumber){
        UserProfile.setPhoneNumber(PhoneNumber);
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

    /**
     * This method adds a book to the local list of books and to the database
     * @param book to be added
     */
    public void addBook(Book book) {
        //books.add(book);
        myBooks.add(book);
        notifyViews();
        //TODO update database
        // try catch block to sync, follow offline user story if fail
    }

    /**
     * This method deletes a book locally by index and deletes from the database
     * @param index from the booklist to delete
     */
    public void deleteBook(int index) {
        //books.remove(index);
        myBooks.remove(index);
        notifyViews();
        // TODO sync up with database
        // try catch block to sync, follow offline user story if fail
    }

    /**
     * This method edits a book by first contructing a new book
     * then edit the original book by call its index and replace by the new book
     * to the local and database
     * @param index
     * @param newBook
     */
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
