package com.test.part1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SubjectTeacher entity.\n@author Didier Labour.
 */
@ApiModel(description = " SubjectTeacher entity.\n@author Didier Labour.")
@Entity
@Table(name = "subject_teacher")
public class SubjectTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private Teacher teacherId =new Teacher();



    @MapsId("groupId") 
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Group group;

    @MapsId("subjectId")
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Subjects subjects;

    public SubjectTeacher(){}

    public SubjectTeacher(Teacher teacherId){
        this.teacherId=teacherId;

    }

  

    
    /** 
     * @return Teacher
     */
    public Teacher getTeacherId() {
        return teacherId;
    }

    
    /** 
     * @param teacherId
     */
    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    
    /** 
     * @return Group
     */
    public Group getGroup() {
        return group;
    }

    
    /** 
     * @param group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    
    /** 
     * @return Subjects
     */
    public Subjects getSubjects() {
        return subjects;
    }

    
    /** 
     * @param subjects
     */
    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }
    
    /** 
     * @param group
     * @return SubjectTeacher
     */
    public SubjectTeacher group(Group group) {
        this.teacherId.setGroupId(group.getId());
        return this;
    }
    
    /** 
     * @param subjects
     * @return SubjectTeacher
     */
    public SubjectTeacher group(Subjects subjects) {
        this.teacherId.setSubjectId(subjects.getId());
        return this;
    }
   
}
