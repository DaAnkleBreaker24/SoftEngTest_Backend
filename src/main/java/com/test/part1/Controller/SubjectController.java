package com.test.part1.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.test.part1.domain.Subjects;
import com.test.part1.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Transactional
@Api( tags = "Subject")
public class SubjectController {
    
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
       
        this.subjectService=subjectService;
    }

      /**
     * {@code POST  /subjects} : Create a new subjects.
     *
     * @param subjects the subjects to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subjects, or with status {@code 400 (Bad Request)} if the subjects has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subjects")
    public ResponseEntity<Subjects> createSubject(@RequestBody Subjects subjects) throws URISyntaxException {

        Subjects result = subjectService.save(subjects);
        return ResponseEntity
            .created(new URI("/api/subjects/" + result.getId()))
            .body(result);
    }
  /**
     * {@code DELETE  /subjects/:id} : delete the "id" subjects.
     *
     * @param id the id of the subjects to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
      
        subjectService.deleteById(id);
        return  ResponseEntity.noContent().header("Content-Length", "0").build();
    }

    
    /** 
     * @return ResponseEntity<Object>
     */
    @PutMapping("/subjects/{id}")
    public ResponseEntity<Object> updateSubject(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Subjects subjects
    ) {
       
        if (subjects.getId() == null || !Objects.equals(id, subjects.getId()) || !subjectService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    
        Subjects result = subjectService.save(subjects);
        return  ResponseEntity
            .ok(result);
            
    }

       /**
     * {@code GET  /subjects} : get all the subjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjects in body.
     */
    @GetMapping("/subjects")
    public List<Subjects> getAllSubject() {
       
        return subjectService.findAll();
    }

    
    
    /** 
     * @param id
     * @return ResponseEntity<Subjects>
     */
    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subjects> getSubjects(@PathVariable Long id) {
      
        Optional<Subjects> subjects = subjectService.findById(id);

       if( !subjects.isPresent()){
      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return ResponseEntity.ok(subjects.get());
       }
    }

    

    
}
