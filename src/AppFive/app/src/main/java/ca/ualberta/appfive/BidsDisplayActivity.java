package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

/**
 * This Activity has BidsListAdapter to get to view bidList
 */
public class BidsDisplayActivity extends AppCompatActivity implements BView<BModel> {

    private BidsListAdapter bla;
    private int PLACE_PICKER_REQUEST = 1;
    private Place place;
    private Book myBook;
    private int bidPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids_display);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();
        final int index = getIntent().getIntExtra("INDEX", -1);
        myBook = ac.getMyBook(index);

        bla = new BidsListAdapter(this, myBook.getBids());

        ListView bidsListView = (ListView) findViewById(R.id.LVBidList);
        bidsListView.setAdapter(bla);

        /** For the list view of bids, set on Long click to provide menu
         * @param menuitem for item
         * @return boolean true of false, which will deleteBids/setStatus/addbid
         */

        bidsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                Menu bidsMenu = popup.getMenu();
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.bidsmenu, bidsMenu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.acceptBid) {
                            bidPosition = position;
                            goToMap();

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

    public void goToMap(){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(this, data);
                String toastMsg = "Location for pickup set";
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                Bid acceptedBid = myBook.getBid(bidPosition);
                acceptedBid.setLatitude(place.getLatLng().latitude);
                acceptedBid.setLongitude(place.getLatLng().longitude);
                myBook.deleteBids();
                myBook.setStatus(Book.Status.BORROWED);
                myBook.addBid(acceptedBid);
                ESController.EditBookTask editBookTask = new ESController.EditBookTask();
                editBookTask.execute(myBook);
                finish();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        fc.notifyViews();
    }

    /** to update model
     * @param model for BModel
     */
    @Override
    public void update(BModel model) {
        bla.notifyDataSetChanged();
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
    }
}
