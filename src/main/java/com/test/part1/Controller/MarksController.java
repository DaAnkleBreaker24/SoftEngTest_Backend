package com.test.part1.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.test.part1.domain.Marks;
import com.test.part1.service.MarksService;
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
@Transactional
@RequestMapping("/api")
@Api( tags = "Marks")
public class MarksController {
    private final MarksService markService;

    public MarksController(MarksService markService) {
       
        this.markService=markService;
    }

      /**
     * {@code POST  /marks} : Create a new marks.
     *
     * @param marks the marks to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new marks, or with status {@code 400 (Bad Request)} if the marks has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/marks")
    public ResponseEntity<Marks> createMarks(@RequestBody Marks marks) throws URISyntaxException {

        Marks result = markService.save(marks);
        return ResponseEntity
            .created(new URI("/api/marks/" + result.getId()))
            .body(result);
    }
  /**
     * {@code DELETE  /marks/:id} : delete the "id" marks.
     *
     * @param id the id of the marks to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/marks/{id}")
    public ResponseEntity<Void> deleteMarks(@PathVariable Long id) {
      
        markService.deleteById(id);
        return  ResponseEntity.noContent().header("Content-Length", "0").build();
    }

    
    /** 
     * @return ResponseEntity<Object>
     */
    @PutMapping("/marks/{id}")
    public ResponseEntity<Object> updateMarks(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Marks marks
    ) {
       
        if (marks.getId() == null || !Objects.equals(id, marks.getId()) || !markService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    
        Marks result = markService.save(marks);
        return  ResponseEntity
            .ok(result);
            
    }

       /**
     * {@code GET  /marks} : get all the marks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of marks in body.
     */
    @GetMapping("/marks")
    public List<Marks> getAllMarks() {
       
        return markService.findAll();
    }

    
    
    /** 
     * @param id
     * @return ResponseEntity<Marks>
     */
    @GetMapping("/marks/{id}")
    public ResponseEntity<Marks> getMarksById(@PathVariable Long id) {
      
        Optional<Marks> mark = markService.findById(id);

       if( !mark.isPresent()){
      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return ResponseEntity.ok(mark.get());
       }
    }

    
    /** 
     * @param id
     * @return List<Marks>
     */
    @GetMapping("/marks/studentId/{id}")
    public List<Marks> getMarksByStudentId(@PathVariable Long id) {

       return markService.getMarksByStudentId(id);

       
          
       
    }

    
}
