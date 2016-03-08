package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * All model classes should inherit from this class.
 *
 * The code was borrowed from the FillerCreep app.
 * https://github.com/abramhindle/FillerCreepForAndroid
 */
public class BModel<V extends BView> {
    private ArrayList<V> views;

    public BModel() {
        views = new ArrayList<V>();
    }

    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    public void deleteView(V view) {
        views.remove(view);
    }

    public void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}
