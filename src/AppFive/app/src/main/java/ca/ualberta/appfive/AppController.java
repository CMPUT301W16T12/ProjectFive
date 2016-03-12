package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * Created by Omar on 3/10/2016.
 */
public class AppController implements BController{

    AppFive af = null;

    public AppController(AppFive appFive) {
        this.af = appFive;
    }

    public ArrayList<Book> getBooks(){
        return af.getBooks();
    }

    public Book getBook(int index){
        return af.getBook(index);
    }

    private void addBook(Book book) {
        af.addBook(book);
    }

    private void deleteBook(int index) {
        af.deleteBook(index);
    }

    public void editBook(int index, Book newBook) {
        af.editBook(index, newBook);
    }

    public ArrayList<Book> getMyBooks(){
        return af.getMyBooks();
    }

    public Book getMyBook(int index){
        return af.getMyBook(index);
    }
}
