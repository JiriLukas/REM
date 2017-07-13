package structures;

import javafx.beans.property.SimpleDoubleProperty;

import java.time.LocalDate;

/**
 * Created by George on 09.07.2017.
 */
public class Person {

    private String firstName;

    private LocalDate birth;

    private double rating;


    public Person(String firstName, LocalDate birth, double rating) {
        this.firstName = firstName;
        this.birth = birth;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public double getRating() {
        return rating;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", birth=" + birth +
                '}';
    }
}
