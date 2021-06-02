package com.test.part1.dto;

import java.util.ArrayList;
import java.util.List;

import com.test.part1.domain.SubjectMark;

public class SubjectMarks {
    private Long studentId;
    private List<SubjectMark> subjectMarks = new ArrayList<SubjectMark>();
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public List<SubjectMark> getSubjectMarks() {
        return subjectMarks;
    }
    public void setSubjectMarks(List<SubjectMark> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }


}
