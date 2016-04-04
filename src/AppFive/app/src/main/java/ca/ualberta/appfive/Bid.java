package ca.ualberta.appfive;

/**
 * This Bid class has bidder and bid rate
 */
public class Bid {
    private String bidder;
    private float rate;

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
