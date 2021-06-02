package com.test.part1.dao;
import java.util.List;

import com.test.part1.domain.Marks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksDao extends CrudRepository<Marks, Long> {
    List <Marks> findByStudentsId(Long studentsId);
}
