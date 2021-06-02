package com.test.part1.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.test.part1.domain.Group;
import com.test.part1.service.GroupsService;
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
@Api( tags = "Groups")
public class GroupController {
 
    private final GroupsService groupService;

    public GroupController(GroupsService groupService) {
       
        this.groupService=groupService;
    }

      /**
     * {@code POST  /groups} : Create a new groups.
     *
     * @param groups the groups to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groups, or with status {@code 400 (Bad Request)} if the groups has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/groups")
    public ResponseEntity<Group> createGroups(@RequestBody Group groups) throws URISyntaxException {

        Group result = groupService.save(groups);
        return ResponseEntity
            .created(new URI("/api/groups/" + result.getId()))
            .body(result);
    }
  /**
     * {@code DELETE  /groups/:id} : delete the "id" groups.
     *
     * @param id the id of the groups to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroups(@PathVariable Long id) {
      
        groupService.deleteById(id);
        return  ResponseEntity.noContent().header("Content-Length", "0").build();
    }

    
    /** 
     * @return ResponseEntity<Object>
     */
    @PutMapping("/groups/{id}")
    public ResponseEntity<Object> updateGroups(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Group groups
    ) {
       
        if (groups.getId() == null || !Objects.equals(id, groups.getId()) || !groupService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    
        Group result = groupService.save(groups);
        return  ResponseEntity
            .ok(result);
            
    }

       /**
     * {@code GET  /groups} : get all the groups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groups in body.
     */
    @GetMapping("/groups")
    public List<Group> getAllGroups() {
       
        return groupService.findAll();
    }

    
    
    /** 
     * @param id
     * @return ResponseEntity<Group>
     */
    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getSubjects(@PathVariable Long id) {
      
        Optional<Group> group = groupService.findById(id);

       if( !group.isPresent()){
      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return ResponseEntity.ok(group.get());
       }
    }

    
}
