package me.yling.w6challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long skiID;

    @NotEmpty
    @Size(min = 2)
    private String skiName;
    @NotEmpty
    @Size(min = 2)
    private String skiLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;


    public long getSkiID() {
        return skiID;
    }

    public void setSkiID(long skiID) {
        this.skiID = skiID;
    }

    public String getSkiName() {
        return skiName;
    }

    public void setSkiName(String skiName) {
        this.skiName = skiName;
    }

    public String getSkiLevel() {
        return skiLevel;
    }

    public void setSkiLevel(String skiLevel) {
        this.skiLevel = skiLevel;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
