package me.yling.w6challenge.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(min = 2)
    private String fName;

    @NotEmpty
    @Size(min = 2)
    private String lName;

    @Email
    private String email;

    @OneToMany (mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Education> educationSet;
    @OneToMany (mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Experience> experienceSet;
    @OneToMany (mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Skill> skillSet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Education> getEducationSet() {
        return educationSet;
    }

    public void setEducationSet(Set<Education> educationSet) {
        this.educationSet = educationSet;
    }

    public Set<Experience> getExperienceSet() {
        return experienceSet;
    }

    public void setExperienceSet(Set<Experience> experienceSet) {
        this.experienceSet = experienceSet;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public void addEducation(Education edu)
    {
        edu.setPerson(this);
        this.educationSet.add(edu);
    }

    public void addExperience(Experience exp)
    {
        exp.setPerson(this);
        this.experienceSet.add(exp);
    }

    public void addSkill(Skill ski)
    {
        ski.setPerson(this);
        this.skillSet.add(ski);
    }

    public void delEdu (Education education)
    {
        this.getEducationSet().remove(education);
    }
    public void delExp (Experience experience)
    {
        this.getExperienceSet().remove(experience);
    }
    public void delSki (Skill skill) { this.getSkillSet().remove(skill); }
}
