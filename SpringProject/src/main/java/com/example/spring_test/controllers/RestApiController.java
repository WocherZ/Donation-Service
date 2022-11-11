package com.example.spring_test.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @PostMapping("/create")
    public ResponseEntity<?> create() {
        // ...
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getEntity")
    public ResponseEntity<?> getEntity() {
        // ...
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getEntity/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable(name = "id") int id) {
        // ...
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
