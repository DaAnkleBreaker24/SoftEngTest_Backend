package com.test.part1.dao;
import com.test.part1.domain.Students;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsDao extends CrudRepository<Students, Long> {
    
}
