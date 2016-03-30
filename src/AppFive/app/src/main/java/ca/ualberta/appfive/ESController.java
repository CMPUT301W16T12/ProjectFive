package ca.ualberta.appfive;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;


public class ESController {
    private static JestDroidClient client;

    private static String teamdir = "team12";
    private static String booktype = "book";
    private static String usertype = "user";

    public static void verifyClient(){
        // Verify client exists
        if(client == null){
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


    public static class AddBookTask extends AsyncTask<Book, Void, Void> {
        @Override
        protected Void doInBackground(Book... books){
            verifyClient();

            for(Book book : books){
                Index index = new Index.Builder(book).index(teamdir).type(booktype).build();
                try {
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded()){
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

    public static class GetBookTask extends AsyncTask<String, Void, ArrayList<Book>>{
        @Override
        protected ArrayList<Book> doInBackground(String... searchStrings){
            verifyClient();

            ArrayList<Book> books = new ArrayList<>();

            // NOTE: Only first search term will be used
            Search search = new Search.Builder(searchStrings[0]).addIndex(teamdir).addType(booktype).build();

            try {
                SearchResult execute = client.execute(search);
                if(execute.isSucceeded()){
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
    public static class AddUserTask extends AsyncTask<UserProfile, Void, Void> {
        @Override
        protected Void doInBackground(UserProfile... userProfile){
            verifyClient();

                Index index = new Index.Builder(userProfile[0]).index(teamdir).type(usertype).build();
                try {
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded()){
                        // Set ID
                       //TODO: what to do with userProfile database,  userProfile.setId(result.getId());
                    } else {
                        Log.i("TODO", "doInBackground: Add user did not succeed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return null;
        }
    }

    public static class GetUserTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... usernames){
            verifyClient();

            //TODO: look up exact matching query
            String search_string = "{\"from\":0,\"size\":10000,\"query\":{\"match\":{\"message\":\"" + usernames[0] + "\"}}, \"sort\": {\"date\": {\"order\": \"desc\"}}}";


            // NOTE: Only first search term will be used
            Search search = new Search.Builder(search_string).addIndex(teamdir).addType(usertype).build();

            //searchStrings = "{\"from\":0,\"size\":10000, \"sort\": {\"date\": {\"order\": \"desc\"}}}";

            try {
                SearchResult execute = client.execute(search);
                if(execute.isSucceeded()){
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
}
