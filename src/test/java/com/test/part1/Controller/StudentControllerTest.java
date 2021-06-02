package com.test.part1.Controller;

import java.util.Date;
import static org.hamcrest.Matchers.hasSize;
import com.test.part1.Part1ApplicationTests;
import com.test.part1.dao.GroupsDao;
import com.test.part1.dao.MarksDao;
import com.test.part1.dao.StudentsDao;
import com.test.part1.dao.SubjectTeacherDao;
import com.test.part1.dao.SubjectsDao;
import com.test.part1.domain.Group;
import com.test.part1.domain.Marks;
import com.test.part1.domain.Students;
import com.test.part1.domain.SubjectTeacher;
import com.test.part1.domain.Subjects;
import com.test.part1.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@WithMockUser
public class StudentControllerTest extends Part1ApplicationTests{
    private static final String ENTITY_API_URL_ID = "/api/students/{id}";
    @Autowired
    private StudentsDao studentsDao;
    @Autowired
    private MarksDao marksDao;
    @Autowired
    private GroupsDao groupsDao;
    @Autowired
    private  SubjectsDao subjectDao;
    @Autowired
    private  SubjectTeacherDao subjectTeacherDao;
	@Autowired
    private MockMvc mockMvc;

    Group gp1 =new Group();
    Group gp2 =new Group();
    Students p1 = new Students();
    Students p2 = new Students();
    Subjects sub1= new Subjects();
    SubjectTeacher st1=new SubjectTeacher();
    SubjectTeacher st2=new SubjectTeacher();

	@BeforeEach
	public void setup() {
        initEntity();

    }

    public void initEntity(){
        long count = studentsDao.count();
 
        if (count == 0) {
           
            gp1.setName("Red1");
           groupsDao.save(gp1);         
           gp2.setName("Blue");
          groupsDao.save(gp2);
            p1.firstName("John");
            p1.setLastName("Smith");
           p1.setGroups(gp1);
            studentsDao.save(p1);
            p2.firstName("Jane");
            p2.setLastName("Doe");
           p2.setGroups(gp2);
            studentsDao.save(p2);      
            sub1.setTitle("Maths");
            subjectDao.save(sub1);
            Marks m1 = new Marks();
            m1.setDate(new Date());
            m1.setMark(99);
            m1.setStudents(p1);
            m1.setSubject(sub1);
            marksDao.save(m1);     
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

    @Test
    void testGetResultByIdNonExistingStudent() throws Exception {
      
        mockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void testGetResultByIdExistingStudent() throws Exception {
      
        mockMvc.perform(get(ENTITY_API_URL_ID, p1.getId())).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(2)));}
}
