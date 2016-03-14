package ca.ualberta.appfive;

/**
 * This is an interface all views need to have
 */
public interface BView<M> {
    /**
     * This update method takes in a model object and updates the view
     * @param model A model object
     */
    public void update(M model);

}
