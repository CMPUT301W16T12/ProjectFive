package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditBookActivity extends AppCompatActivity implements BView<BModel> {

    /**
     * Book object to be edited
     */
    private Book myBook;

    private EditText editTitle, editGenre, editDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button saveBookEdit = (Button) findViewById(R.id.editbookSave);

        editTitle = (EditText) findViewById(R.id.edittitle);
        editGenre = (EditText) findViewById(R.id.editgenre);
        editDesc = (EditText) findViewById(R.id.editDescription);

        final Intent index = getIntent();
        Book myBook = AppController.getMyBook(index);
        //Set text space with current values
        editTitle.setText(myBook.getTitle(), TextView.BufferType.EDITABLE);
        editGenre.setText(myBook.getGenre(), TextView.BufferType.EDITABLE);
        editDesc.setText(myBook.getDescription(), TextView.BufferType.EDITABLE);

        saveBookEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get new data
                String titleEdit = editTitle.getText().toString();
                String genreEdit = editGenre.getText().toString();
                String descEdit = editDesc.getText().toString();

                Book newBook = new Book(titleEdit, genreEdit, descEdit, "Thumbnail");
                AppController.editBook(index, newBook);
            }

        });
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

    @Override
    public void update(BModel model) {

    }
}
