package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * This activity displays a book and all of it's data.
 * The mode will control the view the user has of the book and the options to edit it.
 */
public class BookDisplayActivity extends AppCompatActivity implements BView<BModel> {

    /**
     * The book that is being displayed
     */
    private Book myBook;

    /**
     *  Key for storing the mode of how the book should be displayed
     */
    public static final String BOOK_DISPLAY_MODE_KEY = "ca.ualberta.appfive.book_display_mode_key";

    /**
     * The mode for allowing the user to view and possibly edit/delete the book if not borrowed
     */
    public static final int DISPLAY_EDIT_MODE = 1;

    /**
     * The mode for showing a borrowed item to borrower
     */
    public static final int DISPLAY_BORROWED_MODE = 2;

    /**
     * The mode for showing a book that the user can bid on
     */
    public static final int DISPLAY_AVAILABLE_MODE = 3;

    /**
     * The mode for showing a book that the user has bid on
     */
    public static final int DISPLAY_BIDED_MODE = 4;

    /**
     * The mode for allowing the user to view and possibly edit/delete the book if borrowed
     */
    public static final int DISPLAY_OWNER_BORROWED_MODE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        final int index = getIntent().getIntExtra("INDEX", -1);
        final int mode = getIntent().getIntExtra("MODE", DISPLAY_AVAILABLE_MODE);
        myBook = ac.getMyBook(index);

        Button ownerButton = (Button) findViewById(R.id.owner);
        Button addBidButton = (Button) findViewById(R.id.addBid);
        Button editBookButton = (Button) findViewById(R.id.editBook);
        Button deleteBookButton = (Button) findViewById(R.id.deleteButton);
        Button returnButton = (Button) findViewById(R.id.returnButton);
        Button bidInfoButton = (Button) findViewById(R.id.bidInfoButton);
        Button bidsButton = (Button) findViewById(R.id.bidsButton);

        ownerButton.setVisibility(View.INVISIBLE);
        ownerButton.setActivated(false);
        addBidButton.setVisibility(View.INVISIBLE);
        addBidButton.setActivated(false);
        editBookButton.setVisibility(View.INVISIBLE);
        editBookButton.setActivated(false);
        deleteBookButton.setVisibility(View.INVISIBLE);
        deleteBookButton.setActivated(false);
        returnButton.setVisibility(View.INVISIBLE);
        returnButton.setActivated(false);
        bidInfoButton.setVisibility(View.INVISIBLE);
        bidInfoButton.setActivated(false);
        bidsButton.setVisibility(View.INVISIBLE);
        bidsButton.setActivated(false);


        switch (mode){
            case 1:
                bidsButton.setActivated(true);
                bidsButton.setVisibility(View.VISIBLE);
                editBookButton.setActivated(true);
                editBookButton.setVisibility(View.VISIBLE);
                deleteBookButton.setVisibility(View.VISIBLE);
                deleteBookButton.setActivated(true);
                break;
            case 2:
                ownerButton.setActivated(true);
                ownerButton.setVisibility(View.VISIBLE);
                bidInfoButton.setActivated(true);
                bidInfoButton.setVisibility(View.VISIBLE);
                break;
            case 3:
                ownerButton.setActivated(true);
                ownerButton.setVisibility(View.VISIBLE);
                addBidButton.setVisibility(View.VISIBLE);
                addBidButton.setActivated(true);
                break;
            case 4:
                bidInfoButton.setActivated(true);
                bidInfoButton.setVisibility(View.VISIBLE);
                ownerButton.setVisibility(View.VISIBLE);
                ownerButton.setActivated(true);
                break;
            case 5:
                returnButton.setActivated(true);
                returnButton.setVisibility(View.VISIBLE);
            default:
                break;
        }


        ownerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDisplayActivity.this, OwnerInfoActivity.class);
                startActivity(intent);
            }
        });

        addBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //TODO: create NewBidPopup before uncommenting below
            }
        });

        editBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDisplayActivity.this, EditBookActivity.class);
                intent.putExtra("INDEX", index);
                startActivity(intent);
            }
        });

        deleteBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.deleteBook(index);
                finish();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBook.setStatus(Book.Status.AVAILABLE);
                ac.editBook(index, myBook);
                finish();
            }
        });
        bidInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO SHOW POPUP WITH BID RATE
            }
        });
        bidsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO SHOW NEW SCREEN WITH ALL BIDS AND OPTIONS TO ACCEPT OR DECLINE BIDS
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
    }

    @Override
    public void update(BModel model) {

    }
}
