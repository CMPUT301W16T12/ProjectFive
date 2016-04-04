package ca.ualberta.appfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/** Strategy for displaying list items borrowed from
 * http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
 * by Sajmon on 1 July 2012, accessed 29 January 2016
 */
public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(Context context, List<Book> objects) {
        super(context, R.layout.book_entry, objects);
    }

    /**
     * get View of a book
     * @param position int of the Book
     * @return convertView
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BookViewEntryHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int statusResource = -1;

        // Create the holder
        if(convertView == null){
            convertView = inflater.inflate(R.layout.book_entry, null, false);
            holder = new BookViewEntryHolder(convertView){};
            convertView.setTag(holder);
        } else {
            holder = (BookViewEntryHolder)convertView.getTag();
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

        return convertView;
    }

}
