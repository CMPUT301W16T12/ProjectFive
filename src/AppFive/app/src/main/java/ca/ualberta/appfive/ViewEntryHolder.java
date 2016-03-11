package ca.ualberta.appfive;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

// Strategy for displaying list items borrowed from
// http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
// by Sajmon on 1 July 2012
// accessed 29 January 2016
public class ViewEntryHolder {
    private View entry;
    private RadioButton radio = null;
    private TextView titleText = null;
    private TextView genreText = null;
    private TextView descriptionText = null;
    //TODO: Add other views to book list entry

    public ViewEntryHolder(View entry) {
        this.entry = entry;
    }

    public TextView getTitleText() {
        if(titleText == null){
            titleText = (TextView) entry.findViewById(R.id.entry_title);
        }
        return titleText;
    }

    public TextView getGenreText() {
        if(genreText == null){
            genreText = (TextView) entry.findViewById(R.id.entry_genre);
        }
        return genreText;
    }

    public TextView getDescriptionText() {
        if(descriptionText == null){
            descriptionText = (TextView) entry.findViewById(R.id.entry_description);
        }
        return descriptionText;
    }

    public RadioButton getRadio(){
        if(this.radio == null){
            this.radio = (RadioButton) entry.findViewById(R.id.radioButton);
        }
        entry.findViewById(R.id.book_entry_layout);

        return this.radio;
    }
}
