package com.test.part1.mapper;

import com.test.part1.domain.Marks;
import com.test.part1.domain.SubjectMark;

public class MarkMapper {
    
    public SubjectMark mapMarks(Marks mark){
        SubjectMark sm= new SubjectMark();
      if(mark.getDate()!=null){
        sm.setDate(mark.getDate());
      }
      if(mark.getMark()!=null){
        sm.setMark(mark.getMark());
      }
      if(mark.getSubject()!=null && mark.getSubject().getTitle()!=null){
        sm.setSubject(mark.getSubject().getTitle());
      }

    return sm;

    }
}
