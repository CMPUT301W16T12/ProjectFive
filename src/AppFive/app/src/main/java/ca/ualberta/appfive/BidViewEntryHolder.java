package ca.ualberta.appfive;

import android.view.View;
import android.widget.TextView;

/**
 * This class gets bidder text and rate text
 */
public class BidViewEntryHolder {
    private View entry;
    private TextView bidderText = null;
    private TextView rateText = null;

    public BidViewEntryHolder(View entry) {
        this.entry = entry;
    }

    public TextView getBidderText() {
        if(bidderText == null){
            bidderText = (TextView) entry.findViewById(R.id.TVBidderEntry);
        }
        return bidderText;
    }

    public TextView getRateText() {
        if(rateText == null){
            rateText = (TextView) entry.findViewById(R.id.TVRateEntry);
        }
        return rateText;
    }

}
