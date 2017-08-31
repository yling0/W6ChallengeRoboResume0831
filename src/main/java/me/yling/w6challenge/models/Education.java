package me.yling.w6challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eduId;

    @NotEmpty
    @Size(min = 2)
    private String degree;
    @NotEmpty
    @Size(min = 2)
    private String major;
    @NotEmpty
    @Size(min = 2)
    private String uni;
    @NotEmpty
    @Size(min = 2)
    private String graduateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;


    public long getEduId() {
        return eduId;
    }

    public void setEduId(long eduId) {
        this.eduId = eduId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
