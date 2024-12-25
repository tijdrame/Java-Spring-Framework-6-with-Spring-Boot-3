package com.emard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class StudentController {
    private final StudentRepo studentRepo;
    
    @GetMapping("getStudents")
    public List<Student> getStudents(){
        log.info("all = {}", studentRepo.findAll());
        return studentRepo.findAll();
    }

    @GetMapping("addStudent")
    public Student addStudent(){
        Student student = new Student();
        student.setName("Emmanuel");
        student.setAge(25);
        studentRepo.save(student);
        return student;
    }
}
