package ca.ualberta.appfive;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * App Controller calls for methods of the AppFive model
 * The methods includes getters and setters from various sub-models.
 * Sub-models include User Profile and Book details.
 */
public class AppController {

    private AppFive af = null;

    public AppController(AppFive appFive) {this.af = appFive;}

    /**
     * This method gets the user's username
     * @return The unique UserName
     */
    public String getUserName() {return af.getUserName();}

    /**
     * This method sets the UserName. Check for uniqueness before calling this method.
     * @param userName The new userName
     */
    public void setUserName(String userName) {af.setUserName(userName);}

    /**
     * Get the user's email
     * @return The user's email address
     */
    public String getUserEmail() {
        return af.getUserEmail();
    }

    /**
     * Set the user's email
     * @param email The user's email address
     */
    public void setUserEmail(String email) {
        af.setUserEmail(email);
    }

    public String getFirstName() {
        return af.getFirstName();
    }

    public void setFirstName(String firstName) {
        af.setFirstName(firstName);
    }

    public String getLastName() {
        return af.getLastName();
    }

    public void setLastName(String lastName) {
        af.setLastName(lastName);
    }


    public String getPhoneNumber() {
        return af.getPhoneNumber();
    }

    public void setPhoneNumber(String PhoneNumber) {
        af.setPhoneNumber(PhoneNumber);
    }

    /**
     * Get the notifications
     * @return An array list of notifications
     */
    public ArrayList<String> getNotifications () {return af.getNotifications();}

    /**
     * Append a notification onto the list of notifications
     * @param notification New notification to be added
     * @param ownerProfile The target owner
     */
    public void addNotification(String notification, UserProfile ownerProfile) {af.addNotification(notification, ownerProfile);}

    public ArrayList<Book> getBookArray(){
        return af.getBookArray();
    }

    public void setBookArray(ArrayList<Book> newBooks) {
        af.setBookArray(newBooks);
    }

    public Book getBook(int index){
        return af.getBook(index);
    }


    /**
     * This method adds a book to the ElasticSearch database.
     * It adds this book to the local data as well.
     * @param book New book to be added
     */
    public void addBook(Book book) {
        ESController.AddBookTask addBookTask = new ESController.AddBookTask();
        addBookTask.execute(book);
        try {
            String id = addBookTask.get();
            book.setId(id);
            af.addBook(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ESController.EditBookTask editBookTask = new ESController.EditBookTask();
        editBookTask.execute(book);
    }

    /**
     * This method deletes a book from local data and from the ElasticSearch database
     * @param index Index of book in list to be deleted
     */
    public void deleteBook(int index) {
        af.deleteBook(index);
    }

    /**
     * This method edits a book. It makes the changes locally and in the ElasticSearch database.
     *
     * @param index Index of book to be edited
     * @param newBook The book object to replace the existing book
     * @param list 0 if it is owned by the current user
     */
    public void editBook(int index, Book newBook, int list) {
        af.editBook(index, newBook, list);
    }

    // TODO: get from database using GetBookByUser then cache in af using setMyBookArray
    public ArrayList<Book> getMyBookArray(){
        return af.getMyBookArray();
    }

    public void setMyBookArray(ArrayList<Book> myNewBooks) {
        af.setMyBookArray(myNewBooks);
    }

    public Book getMyBook(int index){
        return af.getMyBook(index);
    }

    /**
     * This method adds the current user to the ElasticSearch database
     */
    public void addUserToDB(){
        ESController.AddUserTask addUserTask = new ESController.AddUserTask();
        addUserTask.execute(UserProfile.getInstance());
        try {
            String id = addUserTask.get();
            UserProfile.getInstance().setUserId(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        editUserInDB();
    }

    /**
     * If changes have been made to the current user's profile, calling this method will push those
     * changes to the ElasticSearch database
     */
    public void editUserInDB(){
        ESController.EditUserTask editUserTask = new ESController.EditUserTask();
        editUserTask.execute(UserProfile.getInstance());
    }

    /**
     * This method updates the local list of books belonging to the user
     * @param userName The name of the user who owns the books
     */
    public void getMyBooksFromDB(String userName){
        ESController.GetBooksbyUserTask getBooksbyUserTask = new ESController.GetBooksbyUserTask();
        getBooksbyUserTask.execute(userName);
    }

    /**
     * This method fetches a list of books currently borrowed by the user from the ElasticSearch
     * database
     * @param userName The current user's username
     */
    public void getMyBorrowedFromDB(String userName){
        ESController.GetBooksBorrowedbyUserTask getBooksBorrowedbyUserTask = new ESController.GetBooksBorrowedbyUserTask();
        getBooksBorrowedbyUserTask.execute(userName);
        //wait for query to return
    }

    /**
     * Fetch a list of books that the user has bidded on from the ElasticSearch database
     * @param userName The user's username
     */
    public void getMyBidsFromDB(String userName){
        ESController.GetBooksBidsbyUserTask getBooksBidsbyUserTask = new ESController.GetBooksBidsbyUserTask();
        getBooksBidsbyUserTask.execute(userName);
        //wait for query to return
    }

   /* public void populateSearchFromDB(String userName){
        ESController.PopulateSearchTask populateSearchTask = new ESController.PopulateSearchTask();
        populateSearchTask.execute(UserProfile.getInstance().getUserName());

        //wait for query to return
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * This method sets the user profile object to have no data
     */
    public void resetUserProfile(){
        UserProfile.resetUserProfile();
    }

    /**
     * This method tests to see if the given username is in the ElasticSearch database.
     * @param userName The username to be tested
     * @return True if the username is currently in the database, false if it is not
     */
    public Boolean isUserInDataBase(String userName) {
        ESController.IsUserInDatabaseTask isUserInDatabaseTask = new ESController.IsUserInDatabaseTask();
        isUserInDatabaseTask.execute(userName);
        try {
            Boolean result = isUserInDatabaseTask.get();

            return result;


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * Fetches the user's profile data from the ElasticSearch database
     * @param userName The current user's username
     */
    public void getUserProfile(String userName) {
        ESController.GetUserTask getUserTask = new ESController.GetUserTask();
        getUserTask.execute(userName);
    }

    public void search (String search){
        ESController.SearchTask searchTask = new ESController.SearchTask();
        searchTask.execute(search);
    }

}

