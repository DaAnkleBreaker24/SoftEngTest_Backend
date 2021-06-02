package com.test.part1.service;

import java.util.List;
import java.util.Optional;
import com.test.part1.dao.SubjectsDao;
import com.test.part1.domain.Subjects;

import org.springframework.stereotype.Service;

@Service
public class SubjectService{
  private SubjectsDao subjectsDao;

  SubjectService(SubjectsDao subjectsDao){
    this.subjectsDao=subjectsDao;
  }

  
  /** 
   * @param entity
   * @return Subjects
   */
  public Subjects save(Subjects entity){
    return subjectsDao.save(entity);
  }

  
  /** 
   * @param entity
   */
  public void delete(Subjects entity){
    subjectsDao.delete(entity);
  }
  
  /** 
   * @param id
   */
  public void deleteById(Long id){
    subjectsDao.deleteById(id);
  }
  
  /** 
   * @param Id
   * @return Optional<Subjects>
   */
  public Optional<Subjects> findById(Long Id){
    return subjectsDao.findById(Id);
}
  
  /** 
   * @return List<Subjects>
   */
  public List<Subjects> findAll(){
      return (List<Subjects>) subjectsDao.findAll();
  }
  
  /** 
   * @param id
   * @return boolean
   */
  public boolean existsById(Long id) {
    return subjectsDao.existsById(id);
}
}