package ca.ualberta.appfive;

/**
 * Class for the book object.
 * Contains data for a book, status and history
 */
public class Book {
    /**
     * Kinds of status that the book can have
     */
    public enum Status {AVAILABLE, BIDDED, BORROWED}

    private String title;
    private String description;
    private String genre;
    private Status status;

}
