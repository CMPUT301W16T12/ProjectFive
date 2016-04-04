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
    private BookListAdapter bla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        final EditText searchET = (EditText) findViewById(R.id.ETSearch);
        final ListView searchLV = (ListView) findViewById(R.id.LVSearchList);
        final Button searchButton = (Button) findViewById(R.id.searchButton);
        //ac.setBookArray(new ArrayList<Book>());
        bla = new BookListAdapter(this, ac.getBookArray());
        searchLV.setAdapter(bla);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchET.getText().toString();
                ac.search(search);
                //TODO: display the search result for the list of books (notify)
            }
        });

    }

    @Override
    public void update(BModel model) {
        bla.notifyDataSetChanged();
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
