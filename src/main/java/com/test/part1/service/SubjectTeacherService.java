package com.test.part1.service;

import java.util.List;
import java.util.Optional;
import com.test.part1.dao.SubjectTeacherDao;
import com.test.part1.domain.SubjectTeacher;
import com.test.part1.domain.Teacher;
import com.test.part1.dto.StudentCount;

import org.springframework.stereotype.Service;

@Service
public class SubjectTeacherService{
  private SubjectTeacherDao subjectTeacherDao;

  SubjectTeacherService(SubjectTeacherDao subjectTeacherDao){
    this.subjectTeacherDao=subjectTeacherDao;
  }

  
  /** 
   * @param entity
   * @return SubjectTeacher
   */
  public SubjectTeacher save(SubjectTeacher entity){
    return subjectTeacherDao.save(entity);
  }

  
  /** 
   * @param entity
   */
  public void delete(SubjectTeacher entity){
    subjectTeacherDao.delete(entity);
  }
  
  /** 
   * @param id
   */
  public void deleteById(Teacher id){
    Optional<SubjectTeacher> st= subjectTeacherDao.findByTeacherId(id);
    if(st.isPresent()){
      subjectTeacherDao.delete(st.get());
    }
    
  }
  
  /** 
   * @param id
   * @return Optional<SubjectTeacher>
   */
  public Optional<SubjectTeacher> findById(Teacher id){
    return subjectTeacherDao.findByTeacherId(id);
}
  
  /** 
   * @return List<SubjectTeacher>
   */
  public List<SubjectTeacher> findAll(){
      return (List<SubjectTeacher>) subjectTeacherDao.findAll();
  }
  
  /** 
   * @param id
   * @return boolean
   */
  public boolean existsById(Teacher id) {
    return subjectTeacherDao.findByTeacherId(id).isPresent();
}


/** 
 * @param id
 * @return int
 */
public StudentCount studentCount(Long id) {
  StudentCount stc= new StudentCount();
  stc.setTeacherId(id);
  stc.setNoOfStudents(subjectTeacherDao.getStudentCount(id));
   return stc ;
}
}