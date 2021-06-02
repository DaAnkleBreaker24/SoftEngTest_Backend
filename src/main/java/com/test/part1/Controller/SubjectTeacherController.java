package com.test.part1.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.test.part1.domain.Group;
import com.test.part1.domain.SubjectTeacher;
import com.test.part1.domain.Subjects;
import com.test.part1.domain.Teacher;
import com.test.part1.service.GroupsService;
import com.test.part1.service.SubjectService;
import com.test.part1.service.SubjectTeacherService;
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
@Api( tags = "SubjectTeacher")
public class SubjectTeacherController {
  
    private final SubjectTeacherService subjectTeacherService;
    private final SubjectService subjectService;
    private final GroupsService groupService;
    public SubjectTeacherController(SubjectTeacherService subjectTeacherService,SubjectService subjectService,GroupsService groupService) {
       
        this.subjectTeacherService=subjectTeacherService;
        this.groupService=groupService;
        this.subjectService=subjectService;
    }

      /**
     * {@code POST  /subjectTeachers} : Create a new subjectTeachers.
     *
     * @param subjectTeachers the subjectTeachers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subjectTeachers, or with status {@code 400 (Bad Request)} if the subjectTeachers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subjectTeachers")
    public ResponseEntity<SubjectTeacher> createSubjectTeacher(@RequestBody SubjectTeacher subjectTeacher) throws URISyntaxException {
       if(subjectTeacher ==null || subjectTeacher.getTeacherId() ==null || subjectTeacher.getTeacherId().getGroupId()==null ||  subjectTeacher.getTeacherId().getSubjectId()==null){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        Optional<Group> group= groupService.findById(subjectTeacher.getTeacherId().getGroupId());
        Optional<Subjects> subjects= subjectService.findById(subjectTeacher.getTeacherId().getSubjectId());
        if(!(group.isPresent() && subjects.isPresent())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        subjectTeacher.setGroup(group.get());
        subjectTeacher.setSubjects(subjects.get());
        SubjectTeacher result = subjectTeacherService.save(subjectTeacher);
         return ResponseEntity
             .created(new URI("/api/subjectTeachers/" + result.getTeacherId()))
             .body(result);

       
        
     
    }
  /**
     * {@code DELETE  /subjectTeachers/:groupId/:subjectId/:teacherId} : delete the "id" subjectTeachers.
     *
     * @param id the id of the subjectTeachers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subjectTeachers/{groupId}/{subjectId}/{teacherId}")
    public ResponseEntity<Void> deleteSubjectTeacher(@PathVariable Long groupId,@PathVariable Long subjectId,@PathVariable Long teacherId) {
      Teacher teacher= new Teacher(teacherId, groupId, subjectId);
        subjectTeacherService.deleteById(teacher);
        return  ResponseEntity.noContent().header("Content-Length", "0").build();
    }

    
    /** 
     * @return ResponseEntity<Object>
     */
    @PutMapping("/subjectTeachers/{id}")
    public ResponseEntity<Object> updateSubjectTeacher(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SubjectTeacher subjectTeachers
    ) {
       
        if ((subjectTeachers.getTeacherId()== null && subjectTeachers.getTeacherId().getTeacherId()== null ) || !Objects.equals(id, subjectTeachers.getTeacherId().getTeacherId()) ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    
        SubjectTeacher result = subjectTeacherService.save(subjectTeachers);
        return  ResponseEntity
            .ok(result);
            
    }

       /**
     * {@code GET  /subjectTeachers} : get all the subjectTeachers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjectTeachers in body.
     */
    @GetMapping("/subjectTeachers")
    public List<SubjectTeacher> getAllSubjectTeacher() {
       
        return subjectTeacherService.findAll();
    }

       /**
     * {@code GET  /subjectTeachers by Id } : get the subjectTeachers by its composite key.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjectTeachers in body.
     */
    
    @GetMapping("/subjectTeachers/{groupId}/{subjectId}/{teacherId}")
    public ResponseEntity<Object> getSubjectTeacherById(@PathVariable Long groupId,@PathVariable Long subjectId,@PathVariable Long teacherId) {
        Teacher teacher= new Teacher(teacherId, groupId, subjectId);
        Optional <SubjectTeacher> st1=subjectTeacherService.findById(teacher);
        if(st1.isPresent()){
            return ResponseEntity
            .ok(st1.get());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       

      
    }

    
       /**
     * {@code GET  /subjectTeachers:teacherId} : get the number of students by it teacher id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjectTeachers in body.
     */
    @GetMapping("/subjectTeachers/{teacherId}")
    public ResponseEntity<Object> getStudentCount(@PathVariable Long teacherId) {
    
            return ResponseEntity
            .ok(subjectTeacherService.studentCount(teacherId));
      
    }


    
}
