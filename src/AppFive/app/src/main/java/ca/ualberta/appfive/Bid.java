package ca.ualberta.appfive;

import java.util.Currency;

/**
 * Created by Omar on 3/14/2016.
 */
public class Bid {
    private String bidder;
    private float rate;

    public Bid(String bidder, float rate ){
        this.bidder = bidder;
        this.rate = rate;
    }

    public String getBidder() {
        return bidder;
    }

    public float getRate() {
        return rate;
    }

}
