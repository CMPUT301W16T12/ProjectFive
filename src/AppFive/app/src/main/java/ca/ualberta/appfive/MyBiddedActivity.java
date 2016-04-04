package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This activity shows the books that the user has bidded on.
 */
public class MyBiddedActivity extends AppCompatActivity implements BView<BModel> {
    private BookListAdapter bla;
    private final AppController ac = AppFiveApp.getAppController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bidded);


        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);

        bla = new BookListAdapter(this, ac.getBookArray());

        ListView mybidsListView = (ListView) findViewById(R.id.LVMyBids);
        mybidsListView.setAdapter(bla);


        mybidsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyBiddedActivity.this, BookDisplayActivity.class);
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
