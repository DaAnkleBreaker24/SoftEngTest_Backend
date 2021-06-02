package com.test.part1.domain;

import java.io.Serializable;

import javax.persistence.*;


@Embeddable
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Long teacherId;

    @Column(name = "group_Id")
    private Long groupId;
    @Column(name = "subject_Id")
    private Long subjectId;
    
    public Teacher() {
    }

    public Teacher(Long teacherId, Long groupId, Long subjectId) {
        this.subjectId = subjectId;
        this.groupId = groupId;
        this.teacherId=teacherId;
    }


    
    /** 
     * @return Long
     */
    public Long getGroupId() {
        return groupId;
    }
    
    /** 
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    /** 
     * @return Long
     */
    public Long getSubjectId() {
        return subjectId;
    }
    
    /** 
     * @param subjectId
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    
    /** 
     * @return Long
     */
    public Long getTeacherId() {
        return teacherId;
    }
    
    /** 
     * @param teacherId
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

   

    
}
