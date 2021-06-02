package com.test.part1.service;

import java.util.List;
import java.util.Optional;

import com.test.part1.dao.GroupsDao;
import com.test.part1.domain.Group;

import org.springframework.stereotype.Service;

@Service
public class GroupsService{
  private GroupsDao groupsDao;

  GroupsService(GroupsDao groupsDao){
    this.groupsDao=groupsDao;
  }

  
  /** 
   * @param entity
   * @return Group
   */
  public Group save(Group entity){
    return groupsDao.save(entity);
  }

  
  /** 
   * @param entity
   */
  public void delete(Group entity){
    groupsDao.delete(entity);
  }
  
  /** 
   * @param id
   */
  public void deleteById(Long id){
    groupsDao.deleteById(id);
  }
  
  /** 
   * @param Id
   * @return Optional<Group>
   */
  public Optional<Group> findById(Long Id){
    return groupsDao.findById(Id);
}
  
  /** 
   * @return List<Group>
   */
  public List<Group> findAll(){
      return (List<Group>) groupsDao.findAll();
  }
  
  /** 
   * @param id
   * @return boolean
   */
  public boolean existsById(Long id) {
    return groupsDao.existsById(id);
}
}