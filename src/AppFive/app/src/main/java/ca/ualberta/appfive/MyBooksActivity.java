package ca.ualberta.appfive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyBooksActivity extends AppCompatActivity implements BView<BModel>{

    BookListAdapter bla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        bla = new BookListAdapter(this, AppFive.getBooks());

        for(Book book : AppFive.getBooks()){
            book.addView(this);
        }

        ListView booksListView = (ListView) findViewById(R.id.listViewBooks);
        booksListView.setAdapter(bla);

        // Behaviour for clicking on a book in the list
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyBooksActivity.this, EditBookActivity.class);
                intent.putExtra("book_index", position);
                startActivity(intent);
            }
        }
        );
    }

    @Override
    public void update(BModel model) {
        bla.notifyDataSetChanged();
    }
}
