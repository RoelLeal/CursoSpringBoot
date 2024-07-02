package com.controlescolar.benitojuarez.controllers;

import com.controlescolar.benitojuarez.domain.Students;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
public class StudentsController {

    List<Students> students = new ArrayList<>(List.of(
            new Students(1L, "Juan", "juan@gmail.com", 20, "Math"),
            new Students(2L, "Pedro", "pedro@gmail.com", 21, "Science"),
            new Students(3L, "Maria", "maria@gmail.com", 22, "History"),
            new Students(4L, "Jose", "jose@gmail.com", 23, "Geography")
    ));

    @GetMapping
    public List<Students> getStudents() {
        return students;
    }

    @GetMapping("/{email}")
    public Students getStudentByEmail(@PathVariable String email) {
        for (Students s: students) {
            if(s.getEmail().equals(email)) {
                return s;
            }
        }
        return null;
    }

    @PostMapping
    public Students createStudent(@RequestBody Students student) {
        students.add(student);
        return student;
    }

    @PutMapping
    public Students editStudent(@RequestBody Students student) {
        for(Students s: students) {
            if(Objects.equals(s.getId(), student.getId())) {
                s.setId(student.getId());
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());
                return student;
            }
        }
        return null;
    }

    @PatchMapping()
    public Students editPartialStudent(@RequestBody Students student) {
        for (Students s: students) {
            if(s.getId().equals(student.getId())) {
                if(student.getName() != null) {
                    s.setName(student.getName());
                }
                if(student.getEmail() != null) {
                    s.setEmail(student.getEmail());
                }
                if(student.getAge() != 0) {
                    s.setAge(student.getAge());
                }
                if(student.getCourse() != null) {
                    s.setCourse(student.getCourse());
                }
                return s;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Students deleteStudent(@PathVariable Long id) {
        for (Students s: students) {
            if(s.getId().equals(id)) {
                students.remove(s);
                return s;
            }
        }
        return null;
    }

}
