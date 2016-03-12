package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * This is the master model class for the books
 */
public class AppFive extends BModel<BView>{

    private UserProfile userProfile;
    static private ArrayList<Book> books = new ArrayList<Book>();
    static private ArrayList<Book> mybooks = new ArrayList<Book>();

    AppFive() {
        super();
        init();
    }

    private void init() {
        this.userProfile = UserProfile.getInstance();
    }

    public static ArrayList<Book> getBooks(){
       return books;
    }

    public static Book getBook(int index){
        return books.get(index);
    }

    private static void addBook(Book book) {
        books.add(book);
        book.notifyViews();
        //TODO update database
    }

    private static void deleteBook(int index) {
        books.remove(index);
        //TODO update database
    }

    public static void editBook(int index, Book newBook) {
        // to edit a book construct a new book with the new attributes
        // and call this function with the index of the old instance,
        // and the new book to replace it.

        Book oldBook = getBook(index);
        oldBook.setDescription(newBook.getDescription());
        oldBook.setGenre(newBook.getGenre());
        oldBook.setTitle(newBook.getTitle());

        //TODO update database
    }




    public static ArrayList<Book> getMybooks(){
        return mybooks;
    }

    public static Book getMybook(int index){
        return mybooks.get(index);
    }

    public static void addMybook(Book book) {}

    public static void deleteMybook(int index) {}



}
