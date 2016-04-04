package ca.ualberta.appfive;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import static io.searchbox.core.Index.*;

/**
 * ESController is used for communication to database
 */

public class ESController {
    private static JestDroidClient client;
    private static AppController ac = AppFiveApp.getAppController();

    private static String teamdir = "team12";
    private static String booktype = "book";
    private static String usertype = "user";

    public static void verifyClient() {
        // Verify client exists
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    /**
     * For adding a book to database. Pass in the book object when creating.
     * Call get to get the ElasticSearch id.
     */
    public static class AddBookTask extends AsyncTask<Book, Void, String> {
        @Override
        protected String doInBackground(Book... books) {
            verifyClient();

            for (Book book : books) {
                Index index = new Index.Builder(book).index(teamdir).type(booktype).build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        // Set ID
                        return result.getId();
                    } else {
                        Log.i("TODO", "doInBackground: Add book did not succeed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

            }

            return null;
        }
    }

    /**
     * For getting books from database based on a search string. Pass in a search term.
     * Call get to get the list of books.
    */
    public static class GetBookTask extends AsyncTask<String, Void, ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(String... searchStrings) {
            verifyClient();

            ArrayList<Book> books = new ArrayList<>();

            // NOTE: Only first search term will be used
            for (String searchStr: searchStrings) {
                Search search = new Search.Builder(searchStr).addIndex(teamdir).addType(booktype).build();

                try {
                    SearchResult execute = client.execute(search);
                    if (execute.isSucceeded()) {
                        List<Book> retBooks = execute.getSourceAsObjectList(Book.class);
                        books.addAll(retBooks);
                        ac.setMyBookArray(books);
                    } else {
                        Log.i("TODO", "doInBackground: Failed in searching tweets");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return books;
        }
    }

    /**
     * For updating an edited book. Pass in the edited book on task creation.
     * Call get to get a Boolean. This will be true if it went through,
     * false if it connected but could not update, and null if it could not connect.
     */
    public static class EditBookTask extends AsyncTask<Book, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Book... books) {
            verifyClient();

            Index index = new Index.Builder(books[0])
                    .index(teamdir)
                    .type(booktype)
                    .id(books[0].getId())
                    .build();

            try {
                DocumentResult execute = client.execute(index);
                if (execute.isSucceeded()) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
    }

    /**
     * For adding a user to the database. Pass in the UserProfile on task creation.
     * Call get to obtain the ElasticSearch id
     */
    public static class AddUserTask extends AsyncTask<UserProfile, Void, String > {
        @Override
        protected String doInBackground(UserProfile... userProfile) {
            verifyClient();
            for (UserProfile user: userProfile) {
                Index index = new Builder(user).index(teamdir).type(usertype).build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        // Return ID
                        return result.getId();
                    } else {
                        Log.i("TODO", "doInBackground: Add user did not succeed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    /**
    * Fetch current user data from the database. Pass in the user's username on task creation.
    */
    public static class GetUserTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... usernames) {
            verifyClient();

            //TODO: look up exact matching query
            String search_string = "{\"query\":{\"match\":{\"userName\":\"" + usernames[0] + "\"}}}";


            // NOTE: Only first search term will be used
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(usertype).build();


            try {
                SearchResult searchResult = client.execute(search);
                if (searchResult.isSucceeded()) {
                    UserProfile user = searchResult.getSourceAsObject(UserProfile.class);
                    UserProfile.setUserProfile(user);
                    //UserProfile.getInstance().setUserId(searchResult.);

                } else {
                    Log.i("TODO", "doInBackground: Failed in searching tweets");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    /**
     * Fetch user's profile data from the database. Pass in the user's username on task creation.
     * Call get to get the user's data as a UserProfile object
     */
    public static class GetUserProfileTask extends AsyncTask<String, Void, UserProfile> {
        @Override
        protected UserProfile doInBackground(String... usernames) {
            verifyClient();

            //TODO: look up exact matching query
            String search_string = "{\"query\":{\"match\":{\"userName\":\"" + usernames[0] + "\"}}}";


            // NOTE: Only first search term will be used
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(usertype).build();


            try {
                SearchResult searchResult = client.execute(search);
                if (searchResult.isSucceeded()) {
                    UserProfile user = searchResult.getSourceAsObject(UserProfile.class);
                    return user;
                    //UserProfile.getInstance().setUserId(searchResult.);

                } else {
                    Log.i("TODO", "doInBackground: Failed in searching tweets");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    /**
     * For editing a user to database
     * param UserProfile user as object by index to edit
     * @return Boolean true or false
     */
    public static class EditUserTask extends AsyncTask<UserProfile, Void, Boolean> {
        @Override
        protected Boolean doInBackground(UserProfile... userProfiles) {
            verifyClient();

            Index index = new Index.Builder(userProfiles[0])
                    .index(teamdir)
                    .type(usertype)
                    .id(userProfiles[0].getUserId())
                    .build();

            try {
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
    }

    /**
     * For checking whether user is in database
     * param String string to search user by usernmaes
     * @return Boolean true or false
     */
    public static class IsUserInDatabaseTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... usernames) {
            verifyClient();
            //TODO: look up exact matching query
            String search_string = "{\"query\":{\"match\":{\"userName\":\"" + usernames[0] + "\"}}}";


            // NOTE: Only first search term will be used
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(usertype).build();

            //searchStrings = "{\"from\":0,\"size\":10000, \"sort\": {\"date\": {\"order\": \"desc\"}}}";

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {
                    if (execute.getTotal() > 0){
                        return Boolean.TRUE;
                    } else {
                        return Boolean.FALSE;
                    }

                } else {
                    return  null;
                }
            } catch (IOException e) {
                return null;
            }
        }
    }

    public static class GetBooksbyUserTask extends AsyncTask<String, Void, ArrayList<Book>> {
        ArrayList<Book> myBookList = new ArrayList<Book>();

        @Override
        protected ArrayList<Book> doInBackground(String... userNames) {
            verifyClient();


            String search_string =  "";
//            "{\"query\":{" +
//                                    "   \"match\":{" +
//                                    "       \"owner.userName\":\"" + userNames[0] + "\"" +
//                                    "   }" +
//                                    "}}";
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(booktype).build();

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {
                    List<Book> bookList = execute.getSourceAsObjectList(Book.class);
                    myBookList.addAll(bookList);
                    ac.setMyBookArray(myBookList);
                } else {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
            return null;
        }
    }

    public static class GetBooksBorrowedbyUserTask extends AsyncTask<String, Void, ArrayList<Book>> {
        ArrayList<Book> BookList = new ArrayList<Book>();

        @Override
        protected ArrayList<Book> doInBackground(String... userNames) {
            verifyClient();


            String search_string =  "{\"query\": {\"bool\": { \"must\": [" +
                    "   {\"match\":{" +
                    "       \"bids.bidder\":\"" + userNames[0] + "\"" +
                    "   }}," +
                    "   {\"match\":{" +
                    "       \"status\":\" BORROWED\"" +
                    "   }}]}}"+
                    "}";
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(booktype).build();

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {
                    List<Book> bookList = execute.getSourceAsObjectList(Book.class);
                    BookList.addAll(bookList);
                    ac.setBookArray(BookList);
                } else {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
            return null;
        }
    }
    public static class GetBooksBidsbyUserTask extends AsyncTask<String, Void, ArrayList<Book>> {
        ArrayList<Book> myBookList = new ArrayList<Book>();

        ArrayList<Book> BookList = new ArrayList<Book>();

        @Override
        protected ArrayList<Book> doInBackground(String... userNames) {
            verifyClient();


            String search_string =  "{\"query\": {\"bool\": { \"must\": [" +
                    "   {\"match\":{" +
                    "       \"bids.bidder\":\"" + userNames[0] + "\"" +
                    "   }}," +
                    "   {\"match\":{" +
                    "       \"status\":\" BIDDED\"" +
                    "   }}]}}"+
                    "}";
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(booktype).build();

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {
                    List<Book> bookList = execute.getSourceAsObjectList(Book.class);
                    BookList.addAll(bookList);
                    ac.setBookArray(BookList);
                } else {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
            return null;
        }
    }

    public static class DeleteBookTask extends AsyncTask<Book, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Book... books) {
            verifyClient();

            Delete delete = new Delete.Builder("")
                    .index(teamdir)
                    .type(booktype)
                    .id(books[0].getId())
                    .build();

            try {
                DocumentResult execute = client.execute(delete);
                if (execute.isSucceeded()) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } catch (IOException e) {
                return null;
            }

        }
    }

}
