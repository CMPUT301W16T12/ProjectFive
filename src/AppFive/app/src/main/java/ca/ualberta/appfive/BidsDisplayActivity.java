package ca.ualberta.appfive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

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
        final int index = getIntent().getIntExtra("INDEX", -1);
        final Book myBook = ac.getMyBook(index);

        bla = new BidsListAdapter(this, myBook.getBids());

        ListView bidsListView = (ListView) findViewById(R.id.bidList);
        bidsListView.setAdapter(bla);

        //Long click provides menu delete
        bidsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                Menu bidsMenu = popup.getMenu();
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.bidsmenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.acceptBid) {
                            Bid acceptedBid = myBook.getBid(position);
                            myBook.deleteBids();
                            myBook.setStatus(Book.Status.BORROWED);
                            myBook.addBid(acceptedBid);
                            finish();
                        } else {
                            myBook.deleteBid(myBook.getBid(position));
                            myBook.updateStatus();
                            bla.notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });
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
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
    }
}
