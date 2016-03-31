package ca.ualberta.appfive;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// Strategy for displaying list items borrowed from
// http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
// by Sajmon on 1 July 2012
// accessed 29 January 2016

/**
 * This book view class gets title, genre, and status of a book
 */
public class BookViewEntryHolder {

    private View entry;
    //private RadioButton radio = null;
    private TextView titleText = null;
    private TextView genreText = null;
    private ImageView statusImage = null;
    //TODO: Add other views to book list entry

    public BookViewEntryHolder(View entry) {
        this.entry = entry;
    }

    public TextView getTitleText() {
        if(titleText == null){
            titleText = (TextView) entry.findViewById(R.id.TVTitleEntry);
        }
        return titleText;
    }

    public TextView getGenreText() {
        if(genreText == null){
            genreText = (TextView) entry.findViewById(R.id.TVGenreEntry);
        }
        return genreText;
    }

    public ImageView getStatusImage(){
        if(statusImage == null){
            statusImage = (ImageView) entry.findViewById(R.id.IVStatus);
        }
        return statusImage;
    }

    /*
    public RadioButton getRadio(){
        if(this.radio == null){
            this.radio = (RadioButton) entry.findViewById(R.id.radioButton);
        }
        entry.findViewById(R.id.book_entry_layout);

        return this.radio;
    }*/
}
