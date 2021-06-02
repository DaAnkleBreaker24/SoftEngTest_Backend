package com.test.part1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Subjects.
 */
@Entity
@Table(name = "subject")
public class Subjects implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "subject")
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
     * @return Subjects
     */
    public Subjects id(Long id) {
        this.id = id;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    
    /** 
     * @param title
     * @return Subjects
     */
    public Subjects title(String title) {
        this.title = title;
        return this;
    }

    
    /** 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /** 
     * @return Set<Marks>
     */
    public Set<Marks> getMarks() {
        return this.marks;
    }

    
    /** 
     * @param marks
     * @return Subjects
     */
    public Subjects marks(Set<Marks> marks) {
        this.setMarks(marks);
        return this;
    }

    
    /** 
     * @param marks
     * @return Subjects
     */
    public Subjects addMarks(Marks marks) {
        this.marks.add(marks);
        marks.setSubject(this);
        return this;
    }

    
    /** 
     * @param marks
     * @return Subjects
     */
    public Subjects removeMarks(Marks marks) {
        this.marks.remove(marks);
        marks.setSubject(null);
        return this;
    }

    
    /** 
     * @param marks
     */
    public void setMarks(Set<Marks> marks) {
        if (this.marks != null) {
            this.marks.forEach(i -> i.setSubject(null));
        }
        if (marks != null) {
            marks.forEach(i -> i.setSubject(this));
        }
        this.marks = marks;
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
        if (!(o instanceof Subjects)) {
            return false;
        }
        return id != null && id.equals(((Subjects) o).id);
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
        return "Subjects{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
