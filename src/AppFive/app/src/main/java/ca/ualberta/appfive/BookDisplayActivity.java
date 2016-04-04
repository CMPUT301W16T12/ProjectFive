package ca.ualberta.appfive;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

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
     * uses myBookList
     */
    public static final int DISPLAY_EDIT_MODE = 1;

    /**
     * The mode for showing a borrowed item to borrower
     * uses BookList
     */
    public static final int DISPLAY_BORROWED_MODE = 2;

    /**
     * The mode for showing a book that the user can bid on
     * uses Booklist
     */
    public static final int DISPLAY_AVAILABLE_MODE = 3;

    /**
     * The mode for showing a book that the user has bid on
     * uses Booklist
     */
    public static final int DISPLAY_BIDED_MODE = 4;

    /**
     * The mode for allowing the user to view the book if borrowed
     * uses MyBookList
     */
    public static final int DISPLAY_OWNER_BORROWED_MODE = 5;

    private Button ownerButton;
    private Button addBidButton;
    private Button editBookButton;
    private Button deleteBookButton;
    private Button returnButton;
    private Button bidInfoButton;
    private Button bidsButton;
    private Button locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_display);


        final int index = initialize();

        final AppController ac = AppFiveApp.getAppController();
        final AppFive fc = AppFiveApp.getAppFive();

        ownerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDisplayActivity.this, OwnerInfoActivity.class);
                intent.putExtra("INDEX",index);
                startActivity(intent);
            }
        });

        addBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recieveBid(myBook, index);
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
                fc.deleteView(BookDisplayActivity.this);
                Boolean result = null;
                ESController.DeleteBookTask deleteBookTask = new ESController.DeleteBookTask();
                deleteBookTask.execute(ac.getMyBook(index));
                try {
                    result = deleteBookTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(result == null){
                    // Could not connect to database

                } else if(result){
                    // Successfully deleted book in database
                    Log.d("MyBooksActivity", "onMenuItemClick: Deleted book successfully in database");
                } else {
                    // This is reached when the database is not synced with the device
                    Log.d("MyBooksActivity", "onMenuItemClick: connected but could not delete book in database");
                }
                // Delete book locally
                ac.deleteBook(index);
                finish();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBook.deleteBids();
                myBook.updateStatus();
                ac.editBook(index, myBook, 0);
                update(AppFiveApp.getAppFive());
            }
        });
        bidInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBid(myBook);
            }
        });
        bidsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDisplayActivity.this, BidsDisplayActivity.class);
                intent.putExtra("INDEX", index);
                startActivity(intent);
            }
        });
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bid bid = ac.getMyBook(index).getBid(0);
                showMap(bid.getLatitude(), bid.getLongitude());
            }
        });

    }

    private int initialize (){
        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();
        int mode = getIntent().getIntExtra("MODE", DISPLAY_EDIT_MODE);
        final int index = getindex();

        if(mode == DISPLAY_EDIT_MODE || mode == DISPLAY_OWNER_BORROWED_MODE) {
            myBook = ac.getMyBook(index);
        } else {
            myBook = ac.getBook(index);
        }

        if (myBook.getOwner().getUserName().contentEquals(ac.getUserName())){
            if(myBook.getStatus() == Book.Status.BORROWED){
                mode = DISPLAY_OWNER_BORROWED_MODE;
            }else {
                mode = DISPLAY_EDIT_MODE;
            }
        }else{
            if(myBook.getStatus() == Book.Status.BORROWED){
                mode = DISPLAY_BORROWED_MODE;
            }else if( hasBiddedOn(myBook)) {
                mode = DISPLAY_BIDED_MODE;
            }else{
                mode = DISPLAY_AVAILABLE_MODE;
            }
        }

        ownerButton = (Button) findViewById(R.id.ownerButton);
        addBidButton = (Button) findViewById(R.id.addBidButton);
        editBookButton = (Button) findViewById(R.id.editBookButton);
        deleteBookButton = (Button) findViewById(R.id.deleteButton);
        returnButton = (Button) findViewById(R.id.returnButton);
        bidInfoButton = (Button) findViewById(R.id.bidInfoButton);
        bidsButton = (Button) findViewById(R.id.bidsButton);
        locationButton = (Button) findViewById(R.id.locationButton);


        ImageView thumbnail = (ImageView) findViewById(R.id.thumbnailBookDisplay);
        TextView bookTitle = (TextView) findViewById(R.id.TVBookTitle);
        TextView bookdescription = (TextView) findViewById(R.id.TVDescription);
        TextView bookGenre = (TextView) findViewById(R.id.TVGenre);
        TextView bookAuthor = (TextView) findViewById(R.id.TVAuthor);


        bookGenre.setText(myBook.getGenre());
        bookdescription.setText(myBook.getDescription());
        bookTitle.setText(myBook.getTitle());
        bookAuthor.setText(myBook.getAuthor());

        if(index == -1){
            thumbnail.setImageResource(R.drawable.not_available);
        } else {
            thumbnail.setImageBitmap(myBook.getThumbnail());
        }


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
        locationButton.setVisibility(View.INVISIBLE);
        locationButton.setActivated(false);


        switch (mode){
            case DISPLAY_EDIT_MODE:
                bidsButton.setActivated(true);
                bidsButton.setVisibility(View.VISIBLE);
                editBookButton.setActivated(true);
                editBookButton.setVisibility(View.VISIBLE);
                deleteBookButton.setVisibility(View.VISIBLE);
                deleteBookButton.setActivated(true);
                break;
            case DISPLAY_BORROWED_MODE:
                ownerButton.setActivated(true);
                ownerButton.setVisibility(View.VISIBLE);
                bidInfoButton.setActivated(true);
                bidInfoButton.setVisibility(View.VISIBLE);
                locationButton.setVisibility(View.VISIBLE);
                locationButton.setActivated(true);
                break;
            case DISPLAY_AVAILABLE_MODE:
                ownerButton.setActivated(true);
                ownerButton.setVisibility(View.VISIBLE);
                addBidButton.setVisibility(View.VISIBLE);
                addBidButton.setActivated(true);
                break;
            case DISPLAY_BIDED_MODE :
                bidInfoButton.setActivated(true);
                bidInfoButton.setVisibility(View.VISIBLE);
                ownerButton.setVisibility(View.VISIBLE);
                ownerButton.setActivated(true);
                break;
            case DISPLAY_OWNER_BORROWED_MODE:
                returnButton.setActivated(true);
                returnButton.setVisibility(View.VISIBLE);
                bidsButton.setActivated(true);
                bidsButton.setVisibility(View.VISIBLE);
                bidsButton.setText(R.string.borrower);
            default:
                break;
        }

        return index;
    }

    /**
     * View all the bids of a book
     * @param myBook of Book
     */
    private void showBid (Book myBook){
        AppController ac = AppFiveApp.getAppController();
        for (Bid bid : myBook.getBids()){
            if (ac.getUserName().contentEquals( bid.getBidder())){
                Toast.makeText(this, "I bid $" + Float.toString(bid.getRate()) + "/hr", Toast.LENGTH_LONG).show();
            }
        }
    }

	 /**
     * Get index from intent
     * @return index of the book from the booklist
     */
    private int getindex (){
        int index = getIntent().getIntExtra("INDEX", -1);
        return index;
    }

    /**
     * Check whether the book has bidded on
     * @param myBook of Book
     * @return Boolean
     */
    private Boolean hasBiddedOn (Book myBook){
        AppController ac = AppFiveApp.getAppController();
        for (Bid bid : myBook.getBids()){
            if (ac.getUserName().contentEquals(bid.getBidder())){
                return true;
            }
        }
        return false;
    }


    /**
     * To receive bid, we either addbid or update status
     * @param myBook of Book
     * see reference from http://stackoverflow.com/questions/10903754/input-text-dialog-android by Aaron
     *
     */
    private void recieveBid(final Book myBook, final int index) {
        final AppController ac = AppFiveApp.getAppController();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your Bid");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Bid", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String rate = input.getText().toString();
                if (!rate.trim().contentEquals("")) {
                    myBook.addBid(new Bid(ac.getUserName(), Float.parseFloat(rate)));
                    myBook.updateStatus();
                    // creating string notification
                    String notificationText = UserProfile.getInstance().getUserName() + " has made a bid of $" + rate + "/hr  on your book, " + myBook.getTitle();

                    //getting user profile being sent to
                    ESController.GetUserProfileTask getUserProfileTask = new ESController.GetUserProfileTask();
                    getUserProfileTask.execute(myBook.getOwner().getUserName());

                    try {
                        UserProfile ownerProfile = getUserProfileTask.get();
                        ac.addNotification(notificationText,ownerProfile);
                        ESController.EditUserTask editUserTask = new ESController.EditUserTask();
                        editUserTask.execute(ownerProfile);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    ac.editBook(index, myBook,1);

                    update(AppFiveApp.getAppFive());
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showMap (double latitude, double longitude){
        String uriString = String.format("geo:%f,%f?q=%f,%f,(%s)",latitude, longitude, latitude, longitude,"Pickup Location");
        Uri gmmIntentUri = Uri.parse(uriString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        fc.notifyViews();

    }

    /**
     * Updates the book's attributes in the View
     * @param model A model object
     */
    @Override
    public void update(BModel model) {
        initialize();
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
    }
}
