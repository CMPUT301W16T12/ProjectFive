package ca.ualberta.appfive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class EditBookActivity extends AppCompatActivity {

    /**
     * Book object to be edited
     */
    private Book myBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Call to commit the changes made to the book
     * @param title Edited title
     * @param description Edited description
     * @param genre Edited genre
     * @throws DatabaseConnectException
     */
    protected void commitBookEdits(String title, String description, String genre)
            throws DatabaseConnectException{

        throw new DatabaseConnectException();

    }

}
