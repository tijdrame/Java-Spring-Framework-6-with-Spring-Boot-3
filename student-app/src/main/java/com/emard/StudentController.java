package com.emard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StudentController {
    private final StudentRepo studentRepo;
    public StudentController(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }
    @GetMapping("getStudents")
    public List<Student> getStudents(){
        log.info("all = {}", studentRepo.findAll());
        return studentRepo.findAll();
    }
}
