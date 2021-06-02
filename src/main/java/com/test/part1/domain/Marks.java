package com.test.part1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.*;

/**
 * A Marks.
 */
@Entity
@Table(name = "marks")
public class Marks implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column(name = "mark")
    private Integer mark;

    @ManyToOne
    @JsonIgnoreProperties(value = { "marks", "subjectTeacher" }, allowSetters = true)
    private Subjects subject;

    @ManyToOne
    @JsonIgnoreProperties(value = { "group", "marks" }, allowSetters = true)
    private Students students;

    
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
     * @return Marks
     */
    public Marks id(Long id) {
        this.id = id;
        return this;
    }

    
    /** 
     * @return Date
     */
    public Date getDate() {
        return this.date;
    }

    
    /** 
     * @param date
     * @return Marks
     */
    public Marks date(Date date) {
        this.date = date;
        return this;
    }

    
    /** 
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    
    /** 
     * @return Integer
     */
    public Integer getMark() {
        return this.mark;
    }

    
    /** 
     * @param mark
     * @return Marks
     */
    public Marks mark(Integer mark) {
        this.mark = mark;
        return this;
    }

    
    /** 
     * @param mark
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    
    /** 
     * @return Subjects
     */
    public Subjects getSubject() {
        return this.subject;
    }

    
    /** 
     * @param subjects
     * @return Marks
     */
    public Marks subject(Subjects subjects) {
        this.setSubject(subjects);
        return this;
    }

    
    /** 
     * @param subjects
     */
    public void setSubject(Subjects subjects) {
        this.subject = subjects;
    }

    
    /** 
     * @return Students
     */
    public Students getStudents() {
        return this.students;
    }

    
    /** 
     * @param students
     * @return Marks
     */
    public Marks students(Students students) {
        this.setStudents(students);
        return this;
    }

    
    /** 
     * @param students
     */
    public void setStudents(Students students) {
        this.students = students;
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
        if (!(o instanceof Marks)) {
            return false;
        }
        return id != null && id.equals(((Marks) o).id);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    
    /** 
     * @return String
     */
    // prettier-ignore
    @Override
    public String toString() {
        return "Marks{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", mark=" + getMark() +
            "}";
    }
}
