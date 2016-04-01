package ca.ualberta.appfive;


import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestResult;
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
     * For adding a book to database
     * @param Book book as an object
     * @return null
     */
    public static class AddBookTask extends AsyncTask<Book, Void, Void> {
        @Override
        protected Void doInBackground(Book... books) {
            verifyClient();

            for (Book book : books) {
                Index index = new Index.Builder(book).index(teamdir).type(booktype).build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        // Set ID
                        book.setId(result.getId());
                    } else {
                        Log.i("TODO", "doInBackground: Add book did not succeed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }
    }

    /**
     * For getting a book from database
     * @param String for searching keyword
     * @return books, of a array list of book
    TODO: check : Might not need, since books are always opened from lists, the book can be gotten from that list
    */
    public static class GetBookTask extends AsyncTask<String, Void, ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(String... searchStrings) {
            verifyClient();

            ArrayList<Book> books = new ArrayList<>();

            // NOTE: Only first search term will be used
            Search search = new Search.Builder(searchStrings[0]).addIndex(teamdir).addType(booktype).build();

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {
                    List<Book> retBooks = execute.getSourceAsObjectList(Book.class);
                    books.addAll(retBooks);
                } else {
                    Log.i("TODO", "doInBackground: Failed in searching tweets");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return books;
        }
    }

    /**
     * For editing a book to database
     * @param book book as object by index to edit
     * @return Boolean true or false
    TODO: check : Might not need, since books are always opened from lists, the book can be gotten from that list
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
     * For adding a user to database
     * @param UserProfile user profile as an object
     * @return null
     */
    public static class AddUserTask extends AsyncTask<UserProfile, Void, Void> {
        @Override
        protected Void doInBackground(UserProfile... userProfile) {
            verifyClient();
            for (UserProfile user: userProfile) {
                Index index = new Builder(user).index(teamdir).type(usertype).build();
                try {


                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        // Set ID
                        user.setUserId(result.getId());
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
    * For getting the user from database
    * @param String for searching user
    * @return null,
    **/
    public static class GetUserTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... usernames) {
            verifyClient();

            //TODO: look up exact matching query
            String search_string = "{\"from\":0,\"size\":10000,\"query\":{\"match\":{\"message\":\"" + usernames[0] + "\"}}, \"sort\": {\"date\": {\"order\": \"desc\"}}}";


            // NOTE: Only first search term will be used
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(usertype).build();

            //searchStrings = "{\"from\":0,\"size\":10000, \"sort\": {\"date\": {\"order\": \"desc\"}}}";

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {

                    UserProfile retUser = execute.getSourceAsObject(UserProfile.class);
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
     * @param UserProfile user as object by index to edit
     * @return Boolean true or false
     */
    public static class EditUserTask extends AsyncTask<UserProfile, Void, Boolean> {
        @Override
        protected Boolean doInBackground(UserProfile... userProfiles) {
            verifyClient();

            Index index = new Index.Builder(userProfiles[0])
                    .index(teamdir)
                    .type(booktype)
                    .id(userProfiles[0].getUserId())
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
     * For checking whether user is in database
     * @param String string to search user by usernmaes
     * @return Boolean true or false
     */
    public static class IsUserInDatabaseTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... usernames) {
            verifyClient();
            //TODO: look up exact matching query
            String search_string = "{\"from\":0,\"size\":10000,\"query\":{\"match\":{\"message\":\"" + usernames[0] + "\"}}, \"sort\": {\"date\": {\"order\": \"desc\"}}}";


            // NOTE: Only first search term will be used
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(usertype).build();

            //searchStrings = "{\"from\":0,\"size\":10000, \"sort\": {\"date\": {\"order\": \"desc\"}}}";

            try {
                SearchResult execute = client.execute(search);
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

    /**
     * To get books by specified user from database
     * @param UserProfile search the list of books by user
     * @return ArrayList of Book
     */
    public static class GetBooksbyUserTask extends AsyncTask<UserProfile, Void, ArrayList<Book>> {
        ArrayList<Book> myBookList = new ArrayList<Book>();

        @Override
        protected ArrayList<Book> doInBackground(UserProfile... userProfiles) {
            verifyClient();


            String search_string = "{\"from\":0,\"size\":10000,\"query\":{\"match\":{\"owner.name\":\"" + userProfiles[0].getUserName() + "\"}}}";
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(booktype).build();

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()) {
                    List<Book> bookList = execute.getSourceAsObjectList(Book.class);
                    myBookList.addAll(bookList);
                    return myBookList;
                } else {
                    return myBookList;
                }
            } catch (IOException e) {
                return null;
            }
        }
    }

}
