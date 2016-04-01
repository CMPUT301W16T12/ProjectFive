package ca.ualberta.appfive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * This activity edits book and save it.
 */
public class EditBookActivity extends AppCompatActivity implements BView<BModel> {

    private Bitmap thumbnail;
    static final int REQUEST_IMAGE_CAPTURE = 1234;
    Book myBook;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppFive af = AppFiveApp.getAppFive();
        af.addView(this);
        final AppController ac = AppFiveApp.getAppController();

        Button saveBookEdit = (Button) findViewById(R.id.editbookSaveButton);


        final Button deleteImage = (Button) findViewById(R.id.deleteImage);

        final ImageButton editImage = (ImageButton) findViewById(R.id.editThumbnailButton);
        final EditText editTitle = (EditText) findViewById(R.id.ETTitle);
        final EditText editGenre = (EditText) findViewById(R.id.ETGenre);
        final EditText editDesc = (EditText) findViewById(R.id.ETDescription);
        final EditText editAuthor = (EditText)findViewById(R.id.ETAuthor);

        //editImage.setImageResource(R.drawable.not_available);

        TextView title = (TextView) findViewById(R.id.TVEditBookTitle);

        index = getIntent().getIntExtra("INDEX", -2);
        if (index != -2) {
            myBook = ac.getMyBook(index);
            //Set text space with current values
            editTitle.setText(myBook.getTitle(), TextView.BufferType.EDITABLE);
            editGenre.setText(myBook.getGenre(), TextView.BufferType.EDITABLE);
            editDesc.setText(myBook.getDescription(), TextView.BufferType.EDITABLE);
            editAuthor.setText(myBook.getAuthor(), TextView.BufferType.EDITABLE);
            thumbnail = myBook.getThumbnail();
            editImage.setImageBitmap(thumbnail);

        } else {
            title.setText("Add Book");

        }
        saveBookEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get new data
                String titleEdit = editTitle.getText().toString();
                String genreEdit = editGenre.getText().toString();
                String descEdit = editDesc.getText().toString();
                String authorEdit = editAuthor.getText().toString();


                if (index != -2) {
                    Book newBook = new Book(titleEdit, authorEdit, descEdit, genreEdit, myBook.getThumbnail());
                    ac.editBook(index, newBook);
                } else {
                    Book newBook = new Book(titleEdit, authorEdit, descEdit, genreEdit, thumbnail);
                    ac.addBook(newBook);
                }

                finish();
            }

        });
        //Implement image Button
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbnail = null;
                myBook.deleteThumbnail();
                editImage.setImageBitmap(myBook.getThumbnail());

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
    public void onDestroy() {
        super.onDestroy();
        AppFive fc = AppFiveApp.getAppFive();
        fc.deleteView(this);
        //FileParser parser = new FileParser(this.getApplicationContext());
        //parser.saveInFile();
        fc.notifyViews();
    }

    @Override
    public void update(BModel model) {

    }

    /**
     * for book image
     * @param requestCode code for check intent
     * @param resultCode to check if intent return successfully
     * @param data data received from intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ImageButton editImage = (ImageButton) findViewById(R.id.editThumbnailButton);
        //final AppController ac = AppFiveApp.getAppController();
        //final int index = getIntent().getIntExtra("INDEX", -2);

       // Book myBook = ac.getMyBook(index);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            thumbnail =(Bitmap) extras.get("data");
            if(index != -2) {
                myBook.addThumbnail(thumbnail);
            }
            editImage.setImageBitmap(thumbnail);
        }

    }

}
