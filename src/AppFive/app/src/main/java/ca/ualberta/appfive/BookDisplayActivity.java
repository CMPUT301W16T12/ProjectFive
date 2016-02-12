package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * This activity displays a book and all of it's data.
 * The mode will control the view the user has of the book and the options to edit it.
 */
public class BookDisplayActivity extends AppCompatActivity {

    /**
     * The book that is being displayed
     */
    private Book myBook;

    /**
     *  Key for storing the mode of how the book should be displayed
     */
    public static final String BOOK_DISPLAY_MODE_KEY = "ca.ualberta.appfive.book_display_mode_key";

    /**
     * The mode for allowing the user to view and possibly edit/delete the book
     */
    public static final int DISPLAY_EDIT_MODE = 1;

    /**
     * The mode for showing a borrowed item
     */
    public static final int DISPLAY_BORROWED_MODE = 2;

    /**
     * The mode for showing a book that the user can bid on
     */
    public static final int DISPLAY_BID_MODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
