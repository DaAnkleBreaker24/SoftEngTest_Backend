package com.test.part1.Config;

import java.time.Instant;
import java.util.Date;

import com.test.part1.dao.*;

import com.test.part1.domain.Group;
import com.test.part1.domain.Marks;
import com.test.part1.domain.Students;
import com.test.part1.domain.SubjectTeacher;
import com.test.part1.domain.Subjects;
import com.test.part1.domain.Teacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner{
    private final Logger log = LoggerFactory.getLogger(DataInit.class);
    private StudentsDao studentsDao;
    private MarksDao marksDao;
    private GroupsDao groupsDao;
    private  SubjectsDao subjectDao;
    private  SubjectTeacherDao subjectTeacherDao;
 
    
 
    @Autowired
    public DataInit(StudentsDao studentsDao, MarksDao marksDao, GroupsDao groupsDao, SubjectsDao subjectDao, SubjectTeacherDao subjectTeacherDao) {
        this.studentsDao = studentsDao;
        this.marksDao=marksDao;
        this.groupsDao=groupsDao;
        this.subjectDao=subjectDao;
        this.subjectTeacherDao=subjectTeacherDao;
    }

    
    /** 
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = studentsDao.count();
 
        if (count == 0) {
            Group gp1 =new Group();
            gp1.setName("Red1");
           groupsDao.save(gp1);

           Group gp2 =new Group();
           gp2.setName("Blue");
          groupsDao.save(gp2);

            Students p1 = new Students();
 
            p1.firstName("John");
            p1.setLastName("Smith");
           p1.setGroups(gp1);
            studentsDao.save(p1);

            Students p2 = new Students();
 
            p2.firstName("Jane");
            p2.setLastName("Doe");
           p2.setGroups(gp2);
            studentsDao.save(p2);

            

            
            
            Subjects sub1= new Subjects();
            sub1.setTitle("Maths");
        
            subjectDao.save(sub1);
            Marks m1 = new Marks();
            m1.setDate(new Date());
            m1.setMark(99);
            m1.setStudents(p1);
            m1.setSubject(sub1);
            marksDao.save(m1);

            SubjectTeacher st1=new SubjectTeacher();
            SubjectTeacher st2=new SubjectTeacher();
            Teacher tc1=new Teacher();
            tc1.setTeacherId(Long.valueOf(1L));
            st1.setTeacherId(tc1);
            st1.setGroup(gp1);
            st1.setSubjects(sub1);
            subjectTeacherDao.save(st1);

            Teacher tc2=new Teacher();
            tc2.setTeacherId(Long.valueOf(1L));
            st2.setTeacherId(tc2);
            st2.setGroup(gp2);
            st2.setSubjects(sub1);
            subjectTeacherDao.save(st2);
           
        }
    }
}
