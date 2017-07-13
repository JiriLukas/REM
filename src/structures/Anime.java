package structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Class Anime
 * is structure witch all information about one anime.
 * @author George Lukas
 * @version 1.0
 */
public class Anime {

    /* Anime ID in database*/
    private int ID;

    /* Anime picture in ByteArray */
    private ByteArrayOutputStream picture;

    /* Anime name */
    private String name;

    /* Anime genres separated by ','*/
    private ArrayList<Genres> genres;

    /* My personal rating about this anime*/
    private double personalRating;

    /* Internet rating about this anime*/
    private double internetRating;

    /* Date when I watched this anime*/
    private String date;

    /* Anime is recommended by */
    private String recomended;

    /* Anime description */
    private String description;

    /* anime status */
    private Status status;


    /**
     * Constructor
     * @param name - anime name
     * @param genres - anime genres
     * @param personalRating - my rating about this anime
     * @param internetRating - internet rating
     * @param date - date when I watched this anime
     * @param recomended - name of person who recommended this anime
     * @param description - anime description
     * @param status  - anime status
     */
    public Anime(String name, ArrayList<Genres> genres, double personalRating, double internetRating, String date, String recomended, String description, Status status) {
        this.ID = -1;
        this.name = name;
        this.genres = genres;
        this.personalRating = personalRating;
        this.internetRating = internetRating;
        this.date = date;
        this.recomended = recomended;
        this.description = description;
        this.status = status;
    }

    /* Getters */

    public int getID() {
        return ID;
    }

    public ByteArrayOutputStream getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getGenres() {
        String result = "";
        for(Genres genre: genres){
            result += genre.name() + ",";
        }
        return result;
    }

    public double getPersonalRating() {
        return personalRating;
    }

    public double getInternetRating() {
        return internetRating;
    }

    public String getDate() {
        return date;
    }

    public String getRecomended() {
        return recomended;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    /* Setters */

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPicture(ByteArrayOutputStream picture) {
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }

    public void setPersonalRating(double personalRating) {
        this.personalRating = personalRating;
    }

    public void setInternetRating(double internetRating) {
        this.internetRating = internetRating;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRecomended(String recomended) {
        this.recomended = recomended;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
