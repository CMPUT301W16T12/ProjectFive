package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * Created by Omar on 3/10/2016.
 */
public class AppController implements BController{

    AppFive af = null;

    public AppController(AppFive appFive) {this.af = appFive;}

    public String getUserName() {return af.getUserName();}

    public void setUserName(String userName) {af.setUserName(userName);}

    public String getUserEmail() {
        return af.getUserEmail();
    }

    public void setUserEmail(String email) {
        af.setUserEmail(email);
    }

    public ArrayList<Book> getBookArray(){
        return af.getBookArray();
    }

    public void setBookArray(ArrayList<Book> newBooks) {
        af.setBookArray(newBooks);
    }

    public Book getBook(int index){
        return af.getBook(index);
    }

    public void addBook(Book book) {
        af.addBook(book);
    }

    public void deleteBook(int index) {
        af.deleteBook(index);
    }

    public void editBook(int index, Book newBook) {
        af.editBook(index, newBook);
    }

    public ArrayList<Book> getMyBookArray(){
        return af.getMyBookArray();
    }

    public void setMyBookArray(ArrayList<Book> myNewBooks) {
        af.setMyBookArray(myNewBooks);
    }

    public Book getMyBook(int index){
        return af.getMyBook(index);
    }
}
