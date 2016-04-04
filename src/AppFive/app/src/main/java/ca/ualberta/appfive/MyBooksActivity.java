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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * My books activity shows the user's books in a list
 */
public class MyBooksActivity extends AppCompatActivity implements BView<BModel>{

    private BookListAdapter bla;
    private RadioGroup radioButtonGroup;
    private final ArrayList<Book> displayBooks = new ArrayList<Book>();
    private final ArrayList<Book> tempBookArray = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();


        // get the books by username from Elasticsearch

        displayBooks.addAll(ac.getMyBookArray());
        tempBookArray.addAll(ac.getMyBookArray());

        Button addBookButton = (Button) findViewById(R.id.addBookButton);
        radioButtonGroup = (RadioGroup) findViewById(R.id.radioDisplayGroup);
        bla = new BookListAdapter(this, displayBooks);

        final ListView booksListView = (ListView) findViewById(R.id.LVMyBooks);
        booksListView.setAdapter(bla);


        // Behaviour for clicking on a book in the list
        //Regular Click views book
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyBooksActivity.this, BookDisplayActivity.class);
                intent.putExtra("INDEX", position);
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
                         deleteBookTask.execute(ac.getMyBook(position));
                         try {
                             result = deleteBookTask.get();
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         } catch (ExecutionException e) {
                             e.printStackTrace();
                         }

                         if (result == null) {
                             // Could not connect to database

                         } else if (result) {
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
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBooksActivity.this, EditBookActivity.class);
                startActivity(intent);
            }
        });

        //Behavior for clicking something in radio button group
        radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                switch (rb.getId()) {
                    case R.id.radioButtonAll:
                        displayBooks.clear();
                        displayBooks.addAll(tempBookArray);
                        bla.notifyDataSetChanged();
                        break;
                    case R.id.radioButtonAvailable:
                        displayBooks.clear();
                        for(Book book:tempBookArray) {
                            if(book.getStatus().toString().contentEquals("AVAILABLE")){
                                displayBooks.add(book);
                            }
                        }
                        bla.notifyDataSetChanged();
                        break;
                    case R.id.radioButtonBidded:
                        displayBooks.clear();
                        for(Book book:tempBookArray) {
                            if(book.getStatus().toString().contentEquals("BIDDED")){
                                displayBooks.add(book);
                            }
                        }
                        bla.notifyDataSetChanged();
                        break;
                    case R.id.radioButtonBorrowed:
                        displayBooks.clear();
                        for(Book book:tempBookArray) {
                            if(book.getStatus().toString().contentEquals("BORROWED")){
                                displayBooks.add(book);
                            }
                        }
                        bla.notifyDataSetChanged();
                        break;
                }
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

    /**
     * For updating model
     * @param BModel model
     * @return void
     */
    @Override
    public void update(BModel model) {
        AppController ac = AppFiveApp.getAppController();
        radioButtonGroup.check(R.id.radioButtonAll);
        displayBooks.clear();
        displayBooks.addAll(ac.getMyBookArray());
        tempBookArray.clear();
        tempBookArray.addAll(ac.getMyBookArray());
        bla.notifyDataSetChanged();
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
    }
}
