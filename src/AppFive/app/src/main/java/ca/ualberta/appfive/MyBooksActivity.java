package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * My books activity shows the user's books in a list
 */
public class MyBooksActivity extends AppCompatActivity implements BView<BModel>{

    BookListAdapter bla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();


        // ac.addBook(new Book("test", "this is a test", "testing","thumbnail"));



        //TODO: Send to AppController
        //TODO: correct or not if I added this ES getbooktask, but not deleting adapter?
        // get the books by username from Elasticsearch

        bla = new BookListAdapter(this, ac.getMyBookArray());


        Button addBookButton = (Button) findViewById(R.id.addBookButton);
        ListView booksListView = (ListView) findViewById(R.id.LVMyBooks);

        booksListView.setAdapter(bla);

        // Behaviour for clicking on a book in the list
        //Regular Click views book
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyBooksActivity.this, BookDisplayActivity.class);
                intent.putExtra("INDEX",position);
                intent.putExtra("MODE", BookDisplayActivity.DISPLAY_EDIT_MODE);
                startActivity(intent);
            }
        });
        //Long click provides menu to edit or delete
        booksListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                Menu editMenu = popup.getMenu();
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.editmenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Boolean result = null;
                        ESController.DeleteBookTask deleteBookTask = new ESController.DeleteBookTask();
                        deleteBookTask.execute(ac.getBook(position));
                        try {
                            result = deleteBookTask.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        if(result == null){
                            // Could not connect to database
                            // TODO: Offline behaviour, store task and do when online again

                        } else if(result){
                            // Successfully deleted book in database
                            Log.d("MyBooksActivity", "onMenuItemClick: Deleted book successfully in database");
                        } else {
                            // This is reached when the database is not synced with the device
                            Log.d("MyBooksActivity", "onMenuItemClick: connected but could not delete book in database");
                        }
                        // Delete book locally
                        ac.deleteBook(position);
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        }
        );
        // Behaviour for clicking on add book button
        addBookButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBooksActivity.this, EditBookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        fc.notifyViews();
    }

    @Override
    public void update(BModel model) {
        bla.notifyDataSetChanged();
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
    }
}
