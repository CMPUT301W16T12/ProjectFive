package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This activity allows user to search all existing books.
 */
public class SearchActivity extends AppCompatActivity implements BView<BModel>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        final EditText search = (EditText) findViewById(R.id.ETSearch);
        final ListView listView = (ListView) findViewById(R.id.LVSearchList);
        final Button searchButton = (Button) findViewById(R.id.searchButton);
        final String result;
        final ArrayList<Book> books;
        final List<Book> bookList;



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESController.GetBookTask getBookTask = new ESController.GetBookTask();


                // get the books by keywords search from Elasticsearch
                getBookTask.execute(search.getText().toString());

//                // try adding result to book list
//                try {
//                    bookList = new ArrayList<Book>();
//                    bookList.addAll(getBookTask.get());
//                    myBooks = new List<Book>();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    @Override
    public void update(BModel model) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
        fc.notifyViews();
    }
}
