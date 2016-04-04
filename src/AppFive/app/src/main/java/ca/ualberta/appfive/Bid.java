package ca.ualberta.appfive;

import com.google.android.gms.location.places.Place;

/**
 * This Bid class has bidder and bid rate
 */
public class Bid {
    private String bidder;
    private float rate;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private double longitude;


    /**
     * This contructs a bid object
     * @param bidder The username of the bidder
     * @param rate Hourly rate at which bidder is willing to borrow item for
     */
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
