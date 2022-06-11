package com.example.studentghtk.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "search";
    }

    @PostMapping("/search")
    public String searchStudent(@RequestParam(name = "studentCode", defaultValue = "") String studentCode, @RequestParam(name = "fullName", defaultValue = "") String fullName, Model model) {
//        System.out.println(studentService.searchStudent(id, name));
        model.addAttribute("students", studentService.searchStudent(studentCode, fullName));
        return "search";
    }
}
