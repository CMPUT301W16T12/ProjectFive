package ca.ualberta.appfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Omar on 3/14/2016.
 */
public class BidsListAdapter extends ArrayAdapter<Bid> {

    //private int selectedRadioIndex = -1;
    //private RadioButton selectedRadio;

    public BidsListAdapter(Context context, List<Bid> objects) {
        super(context, R.layout.bid_entry, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BidViewEntryHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int statusResource = -1;

        // Create the holder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bid_entry, null, false);
            holder = new BidViewEntryHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BidViewEntryHolder) convertView.getTag();
        }

        holder.getBidderText().setText(getItem(position).getBidder());
        holder.getRateText().setText(Float.toString(getItem(position).getRate()));
        return convertView;
    }
}
