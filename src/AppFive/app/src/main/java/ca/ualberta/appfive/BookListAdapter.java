package ca.ualberta.appfive;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import java.util.List;

// Strategy for displaying list items borrowed from
// http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
// by Sajmon on 1 July 2012
// accessed 29 January 2016
public class BookListAdapter extends ArrayAdapter<Book> {

    //private int selectedRadioIndex = -1;
    //private RadioButton selectedRadio;

    public BookListAdapter(Context context, List<Book> objects) {
        super(context, R.layout.book_entry, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewEntryHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int statusResource = -1;

        // Create the holder
        if(convertView == null){
            convertView = inflater.inflate(R.layout.book_entry, null, false);
            holder = new ViewEntryHolder(convertView){};
            convertView.setTag(holder);
        } else {
            holder = (ViewEntryHolder)convertView.getTag();
        }

        if (getItem(position).getStatus() == Book.Status.AVAILABLE){
            statusResource = R.drawable.ic_status_available;
        } else if(getItem(position).getStatus() == Book.Status.BIDDED){
            statusResource = R.drawable.ic_status_bidded;
        }else {
            statusResource = R.drawable.ic_status_borrowed;
        }

        holder.getTitleText().setText(getItem(position).getTitle());
        holder.getGenreText().setText(getItem(position).getGenre());
        holder.getStatusImage().setImageResource(statusResource);

        /*
        // Strategy for radio button behaviour borrowed from
        // http://stackoverflow.com/questions/7329856/how-to-use-radiogroup-in-listview-custom-adapter
        // by Inon Stelman on 14 September 2011
        // Accessed 29 January 2016
        // When a button is selected, unselect others
        holder.getRadio().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (position != selectedRadioIndex && selectedRadio != null) {
                    selectedRadio.setChecked(false);
                }

                selectedRadioIndex = position;
                selectedRadio = (RadioButton) v;
            }
        });


        if(selectedRadioIndex != position){
            holder.getRadio().setChecked(false);
        }else{
            holder.getRadio().setChecked(true);
            if(selectedRadio != null && holder.getRadio() != selectedRadio){
                selectedRadio = holder.getRadio();
            }
        }
        */

        return convertView;
    }

    /**
     * Returns the index of the selected radio button
     */
    //public int getSelectedIndex() {
    //    return selectedRadioIndex;
    //}
}
