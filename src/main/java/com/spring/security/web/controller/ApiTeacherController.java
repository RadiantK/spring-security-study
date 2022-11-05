package com.spring.security.web.controller;

import com.spring.security.web.domain.Student;
import com.spring.security.web.domain.StudentManager;
import com.spring.security.web.domain.Teacher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/teacher")
public class ApiTeacherController {

    private final StudentManager studentManager;

    public ApiTeacherController(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    @GetMapping(value = "/students")
    public List<Student> main(@AuthenticationPrincipal Teacher teacher){
        System.out.println("teacher" + teacher);
        return studentManager.myStudentList(teacher.getId());
    }

}
