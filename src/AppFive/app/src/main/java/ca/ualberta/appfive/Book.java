package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * Class for the book object.
 * Contains data for a book, status and history
 *
 * Model
 */
public class Book{
    /**
     * Kinds of status that the book can have
     */
    public enum Status {AVAILABLE, BIDDED, BORROWED}

    private String id;
    private String title;
    private String description;
    private String genre;
    private Status status;
    private OwnerInfo owner;
    private String thumbnail;
    private ArrayList<String> bids;

    public  Book() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OwnerInfo getOwner() {
        return owner;
    }

    public void setOwner(OwnerInfo owner) {
        this.owner = owner;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
