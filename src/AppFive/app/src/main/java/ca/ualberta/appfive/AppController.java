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

    public String getUserName() {return af.getUserName();}

    public void setUserName(String userName) {af.setUserName(userName);}

    public String getUserEmail() {
        return af.getUserEmail();
    }

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

    public String getUserPassword() {
        return af.getUserPassword();
    }

    public void setUserPassword(String UserPassword) {
        af.setUserPassword(UserPassword);
    }

    public String getPhoneNumber() {
        return af.getPhoneNumber();
    }

    public void setPhoneNumber(String PhoneNumber) {
        af.setPhoneNumber(PhoneNumber);
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

    public void deleteBook(int index) {
        af.deleteBook(index);
    }

    public void editBook(int index, Book newBook) {
        af.editBook(index, newBook);
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

    public void editUserInDB(){
        ESController.EditUserTask editUserTask = new ESController.EditUserTask();
        editUserTask.execute(UserProfile.getInstance());
    }
    public void getMyBooksFromDB(String userName){
        ESController.GetBooksbyUserTask getBooksbyUserTask = new ESController.GetBooksbyUserTask();
        getBooksbyUserTask.execute(userName);
    }

    public void resetUserProfile(){
        UserProfile.resetUserProfile();
    }
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
    public void getUserProfile(String userName) {
        ESController.GetUserTask getUserTask = new ESController.GetUserTask();
        getUserTask.execute(userName);
    }

}

