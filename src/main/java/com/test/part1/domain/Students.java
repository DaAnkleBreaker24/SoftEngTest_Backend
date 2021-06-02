package com.test.part1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Students.
 */
@Entity
@Table(name = "students")
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    

    @ManyToOne
    @JsonIgnoreProperties(value = { "group", "marks" }, allowSetters = true)
    private Group groups;


    @OneToMany(mappedBy = "students")
   @JsonIgnore
    private Set<Marks> marks = new HashSet<>();

  
    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    /** 
     * @param id
     * @return Students
     */
    public Students id(Long id) {
        this.id = id;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    
    /** 
     * @param firstName
     * @return Students
     */
    public Students firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    
    /** 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    /** 
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    
    /** 
     * @param lastName
     * @return Students
     */
    public Students lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    
    /** 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    
    /** 
     * @return Set<Marks>
     */
    public Set<Marks> getMarks() {
        return this.marks;
    }

    
    /** 
     * @param marks
     * @return Students
     */
    public Students marks(Set<Marks> marks) {
        this.setMarks(marks);
        return this;
    }

    
    /** 
     * @param marks
     * @return Students
     */
    public Students addMarks(Marks marks) {
        this.marks.add(marks);
        marks.setStudents(this);
        return this;
    }

    
    /** 
     * @param marks
     * @return Students
     */
    public Students removeMarks(Marks marks) {
        this.marks.remove(marks);
        marks.setStudents(null);
        return this;
    }

    
    /** 
     * @param marks
     */
    public void setMarks(Set<Marks> marks) {
        if (this.marks != null) {
            this.marks.forEach(i -> i.setStudents(null));
        }
        if (marks != null) {
            marks.forEach(i -> i.setStudents(this));
        }
        this.marks = marks;
    }



    
    /** 
     * @return Group
     */
    public Group getGroups() {
        return groups;
    }

    
    /** 
     * @param groups
     */
    public void setGroups(Group groups) {
        this.groups = groups;
    }

    
    /** 
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Students)) {
            return false;
        }
        return id != null && id.equals(((Students) o).id);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    
    /** 
     * @return String
     */
    // prettier-ignore
    @Override
    public String toString() {
        return "Students{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            "}";
    }
}
