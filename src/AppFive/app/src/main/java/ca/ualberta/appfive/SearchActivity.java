package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
    private final AppController ac = AppFiveApp.getAppController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);

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
            }
        });

        searchLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, BookDisplayActivity.class);
                intent.putExtra("INDEX",position);
                intent.putExtra("MODE", BookDisplayActivity.DISPLAY_BIDED_MODE);
                startActivity(intent);
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
        ac.setBookArray(new ArrayList<Book>());
        fc.notifyViews();
    }
}
