package com.test.part1.Controller;

import java.util.List;

import com.test.part1.Part1ApplicationTests;
import com.test.part1.dao.SubjectTeacherDao;
import com.test.part1.domain.SubjectTeacher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WithMockUser
public class SubjectTeacherControllerTest extends Part1ApplicationTests {
    private static final String ENTITY_API_URL_ID = "/api/subjectTeachers/{teacherId}";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private  SubjectTeacherDao subjectTeacherDao;
    
    @Test
    void testGetStudentCount() throws Exception{
        List <SubjectTeacher>stList =  (List<SubjectTeacher>) subjectTeacherDao.findAll();
        Long teacherId= stList.get(0).getTeacherId().getTeacherId();
        mockMvc.perform(get(ENTITY_API_URL_ID, teacherId)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}
