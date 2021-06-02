package com.test.part1.dao;

import com.test.part1.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsDao extends CrudRepository<Group, Long> {
   
}
