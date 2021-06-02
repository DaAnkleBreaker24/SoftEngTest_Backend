package com.test.part1.dao;
import com.test.part1.domain.Subjects;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsDao extends CrudRepository<Subjects, Long> {
    
}
