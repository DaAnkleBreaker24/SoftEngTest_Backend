package com.test.part1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.test.part1.dao.MarksDao;
import com.test.part1.dao.StudentsDao;
import com.test.part1.domain.Marks;
import com.test.part1.domain.Students;
import com.test.part1.domain.SubjectMark;
import com.test.part1.dto.SubjectMarks;
import com.test.part1.mapper.MarkMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentsService{
  private StudentsDao studentsDao;
  private MarksDao markDao;

  StudentsService(StudentsDao studentsDao, MarksDao markDao){
    this.studentsDao=studentsDao;
    this.markDao=markDao;
  }

  
  /** 
   * @param entity
   * @return Students
   */
  public Students save(Students entity){
    return studentsDao.save(entity);
  }

  
  /** 
   * @param entity
   */
  public void delete(Students entity){
    studentsDao.delete(entity);
  }
  
  /** 
   * @param id
   */
  public void deleteById(Long id){
    studentsDao.deleteById(id);
  }
  
  /** 
   * @param Id
   * @return Optional<Students>
   */
  public Optional<Students> findById(Long Id){
    return studentsDao.findById(Id);
}
  
  /** 
   * @return List<Students>
   */
  public List<Students> findAll(){
      return (List<Students>) studentsDao.findAll();
  }


/** 
 * @param id
 * @return boolean
 */
public boolean existsById(Long id) {
    return studentsDao.existsById(id);
}


public SubjectMarks getSubjectMarksByStudentId(Long Id){
  List<Marks> marks=markDao.findByStudentsId(Id);
  List<SubjectMark> sm =new ArrayList<SubjectMark>();

  for (Marks mark : marks) {
    sm.add(new MarkMapper().mapMarks(mark));
  }

  SubjectMarks sms= new SubjectMarks();
  sms.setSubjectMarks(sm);
  sms.setStudentId(Id);
  return sms;
}
}