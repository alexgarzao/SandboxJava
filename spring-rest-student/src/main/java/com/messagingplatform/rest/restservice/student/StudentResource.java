package com.messagingplatform.rest.restservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentResource {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id)  {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new StudentNotFoundException("id-" + id);
        }

        return student.get();
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new StudentNotFoundException("id-" + id);
        }

        studentRepository.delete(student.get());
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id);
        studentRepository.save(student);
        return ResponseEntity.noContent().build();
    }
}
