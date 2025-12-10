package com.example.sms.controller;

import com.example.sms.model.Student;
import com.example.sms.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
  private final StudentService service;
  public StudentController(StudentService service){ this.service = service; }

  @GetMapping
  public List<Student> list(){ return service.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Student> get(@PathVariable Long id){
    return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Student> create(@RequestBody Student s){
    Student saved = service.save(s);
    return ResponseEntity.ok(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student s){
    return service.findById(id).map(existing -> {
      existing.setFirstName(s.getFirstName());
      existing.setLastName(s.getLastName());
      existing.setEmail(s.getEmail());
      existing.setRollNo(s.getRollNo());
      existing.setClassName(s.getClassName());
      return ResponseEntity.ok(service.save(existing));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
