package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * This is the master model class for the books
 */
public class AppFive extends BModel<BView>{

    private UserProfile userProfile;
    static private  ArrayList<OwnerInfo> owners = new ArrayList<OwnerInfo>();
    static private  ArrayList<Book> books = new ArrayList<Book>();

    AppFive() {
        super();
        init();
    }

    private void init() {
        this.userProfile = UserProfile.getInstance();
    }

    public static ArrayList<OwnerInfo> getOwners() {
        return owners;
    }

    public OwnerInfo getOwner(int id) {
        return owners.get(id);
    }

    public static ArrayList<Book> getBooks(){
       return books;
    }

    public Book getBook(int id){
        return books.get(id);
    }
}
