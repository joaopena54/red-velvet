package com.redvelvet.redvelvet.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvelvet.redvelvet.business.services.ListService;

@RestController
@RequestMapping("/api/user/lists")
public class ListController {

    @Autowired
    private ListService listService;
    
    @PostMapping("/{userid}")
    public ResponseEntity<?> createList(@RequestBody String listName , @PathVariable Long userid){

        try {

            listService.createList(listName, userid);

            return ResponseEntity.ok().body("List sucessfully created");
            

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping("delete/{listid}")
    public ResponseEntity<?> deleteList(@PathVariable Long listid){

        try {

            listService.deleteList(listid);

            return ResponseEntity.ok().body("List sucessfully deleted");


        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    
}
