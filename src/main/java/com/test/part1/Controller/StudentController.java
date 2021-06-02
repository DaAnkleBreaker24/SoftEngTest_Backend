package com.test.part1.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.test.part1.domain.Students;
import com.test.part1.dto.SubjectMarks;
import com.test.part1.service.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api( tags = "Students")
public class StudentController {
    private final StudentsService studentService;

    public StudentController(StudentsService studentService) {
       
        this.studentService=studentService;
    }

      /**
     * {@code POST  /students} : Create a new students.
     *
     * @param students the students to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new students, or with status {@code 400 (Bad Request)} if the students has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/students")
    @ApiOperation(value = "" )
    public ResponseEntity<Students> createStudents(@RequestBody Students students) throws URISyntaxException {

        Students result = studentService.save(students);
        return ResponseEntity
            .created(new URI("/api/students/" + result.getId()))
            .body(result);
    }
  /**
     * {@code DELETE  /students/:id} : delete the "id" students.
     *
     * @param id the id of the students to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudents(@PathVariable Long id) {
      
        studentService.deleteById(id);
        return  ResponseEntity.noContent().header("Content-Length", "0").build();
    }

    /**
     * {@code PUT  /students/:id} : Updates an existing students.
     *
     * @param id the id of the students to save.
     * @param students the students to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated students,
     * or with status {@code 400 (Bad Request)} if the students is not valid,
     * or with status {@code 500 (Internal Server Error)} if the students couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudents(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Students students
    ) {
       
        if (students.getId() == null || !Objects.equals(id, students.getId()) || !studentService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    
        Students result = studentService.save(students);
        return  ResponseEntity
            .ok(result);
            
    }

       /**
     * {@code GET  /students} : get all the students.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of students in body.
     */
    @GetMapping("/students")
    public List<Students> getAllStudents() {
       
        return studentService.findAll();
    }

       /**
     * {@code GET  /students/:id} : get the "id" subjects.
     *
     * @param id the id of the students to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subjects, or with status {@code 404 (Not Found)}.
     */
    
    @GetMapping("/students/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable Long id) {
      
        Optional<Students> student = studentService.findById(id);

       if( !student.isPresent()){
      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return ResponseEntity.ok(student.get());
       }
    }

    @GetMapping("/students/getResult/{id}")
    public ResponseEntity<SubjectMarks> getResultById(@PathVariable Long id) {
      
       
       if( !studentService.existsById(id)){
      
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }else{

           return ResponseEntity.ok(studentService.getSubjectMarksByStudentId(id));
       }
    }

    
}
