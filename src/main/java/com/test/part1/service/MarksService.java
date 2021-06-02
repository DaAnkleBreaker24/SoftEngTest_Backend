package com.test.part1.service;


import java.util.List;
import java.util.Optional;


import com.test.part1.dao.MarksDao;
import com.test.part1.domain.Marks;

import org.springframework.stereotype.Service;

@Service

public class MarksService{
  private MarksDao marksDao;

  MarksService(MarksDao marksDao){
    this.marksDao=marksDao;
  }

  
  /** 
   * @param entity
   * @return Marks
   */
  public Marks save(Marks entity){
    return marksDao.save(entity);
  }

  
  /** 
   * @param entity
   */
  public void delete(Marks entity){
    marksDao.delete(entity);
  }
  
  /** 
   * @param id
   */
  public void deleteById(Long id){
    marksDao.deleteById(id);
  }
  
  /** 
   * @param Id
   * @return Optional<Marks>
   */
  public Optional<Marks> findById(Long Id){
    return marksDao.findById(Id);
}
  
  /** 
   * @return List<Marks>
   */
  public List<Marks> findAll(){
      return (List<Marks>) marksDao.findAll();
  }

  
  /** 
   * @param Id
   * @return List<Marks>
   */
  public List<Marks> getMarksByStudentId(Long Id){
    return (List<Marks>) marksDao.findByStudentsId(Id);
}



  
  /** 
   * @param id
   * @return boolean
   */
  public boolean existsById(Long id) {
    return marksDao.existsById(id);
}

}