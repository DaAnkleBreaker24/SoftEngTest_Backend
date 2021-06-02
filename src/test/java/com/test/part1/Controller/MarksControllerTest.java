package com.test.part1.Controller;

import java.util.List;

import com.test.part1.Part1ApplicationTests;
import com.test.part1.dao.StudentsDao;
import com.test.part1.domain.Students;

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
public class MarksControllerTest extends Part1ApplicationTests {
    private static final String ENTITY_API_URL_ID = "/api/marks/studentId/{id}";

    @Autowired
    private MockMvc mockMvc;
  
    @Autowired
    private StudentsDao studentsDao;
    
    @Test
    void testGetMarksByStudentId() throws Exception {
         List <Students> st =(List<Students>) studentsDao.findAll();
         
        mockMvc.perform(get(ENTITY_API_URL_ID, st.get(0).getId())).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}
