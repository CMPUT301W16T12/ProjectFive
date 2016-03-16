package ca.ualberta.appfive;

import android.graphics.Bitmap;

/**
 * Controller class is implemented in this.
 *
 */
public interface BController {
    //Should we get this to exit the program??
    public boolean isExit();

    // do we get book and user like this?
    public Book[] getBook();

    // do we get user specifically of name and email
    // or just this getUser??
    public UserProfile[] getUser();

    // this is what we learn from Lab yesterday
    // needs to do more later
    public Bitmap getMapBitmap();
}

