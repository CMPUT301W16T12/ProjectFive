package ca.ualberta.appfive;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Omar on 3/14/2016.
 */
public class BidViewEntryHolder {
    private View entry;
    private TextView bidderText = null;
    private TextView rateText = null;
    //TODO: Add other views to book list entry

    public BidViewEntryHolder(View entry) {
        this.entry = entry;
    }

    public TextView getBidderText() {
        if(bidderText == null){
            bidderText = (TextView) entry.findViewById(R.id.entry_bidder);
        }
        return bidderText;
    }

    public TextView getRateText() {
        if(rateText == null){
            rateText = (TextView) entry.findViewById(R.id.entry_rate);
        }
        return rateText;
    }

}
