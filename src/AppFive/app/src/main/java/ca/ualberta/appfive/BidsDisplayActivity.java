package ca.ualberta.appfive;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

/**
 * This Activity has BidsListAdapter to get to view bidList
 */
public class BidsDisplayActivity extends AppCompatActivity implements BView<BModel> {

    BidsListAdapter bla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids_display);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();
        int index = getIntent().getIntExtra("INDEX",-1);

        bla = new BidsListAdapter(this, ac.getMyBook(index).getBids());

        ListView bidsListView = (ListView) findViewById(R.id.bidList);
        bidsListView.setAdapter(bla);


    }

    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        fc.notifyViews();
    }

    @Override
    public void update(BModel model) {
        bla.notifyDataSetChanged();
        FileParser parser = new FileParser(this.getApplicationContext());
        parser.saveInFile();
    }
}
