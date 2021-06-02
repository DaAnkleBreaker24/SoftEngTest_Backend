package com.test.part1.dao;
import java.util.List;
import java.util.Optional;

import com.test.part1.domain.SubjectTeacher;
import com.test.part1.domain.Teacher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTeacherDao extends CrudRepository<SubjectTeacher, Long> {
    public  Optional<SubjectTeacher> findByTeacherId(Teacher teacherId);
    public  List<SubjectTeacher> findByTeacherIdTeacherId(Long teacherId);
    @Query("select count(distinct s.id) from Students s"
    +" join Group g on s.groups.id=g.id "
    +"join SubjectTeacher st on g.id=st.group.id "+
    "where st.teacherId.teacherId= :teacherId")
    public int getStudentCount(Long teacherId);
}
