package ca.ualberta.appfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * This BidsList Adapter displays items from array list to view
 *
 */
public class BidsListAdapter extends ArrayAdapter<Bid> {

    public BidsListAdapter(Context context, List<Bid> objects) {
        super(context, R.layout.bid_entry, objects);
    }

    /** This method gets the view
     * @param position int for specific book
     * @return convertView
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BidViewEntryHolder holder;
        LayoutInflater inflater = LayoutInflater.from(getContext());


        // Create the holder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bid_entry, null, false);
            holder = new BidViewEntryHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BidViewEntryHolder) convertView.getTag();
        }

        holder.getBidderText().setText(getItem(position).getBidder());
        holder.getRateText().setText(String.format("%s$/hr",Float.toString(getItem(position).getRate())));
        return convertView;
    }
}
