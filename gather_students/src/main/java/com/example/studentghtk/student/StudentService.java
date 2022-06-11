package com.example.studentghtk.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> searchStudent(String studentCode, String fullName) {
        studentCode = formatString(studentCode);
        fullName = formatString(fullName);

        if(studentCode.equals("")){
            return studentRepository.findStudentsByFullNameContaining(fullName);
        } else if(fullName.equals("")) {
            return studentRepository.findStudentsByStudentCodeContaining(studentCode);
        } else {
            return studentRepository.findStudentsByFullNameContainingAndStudentCodeContaining(fullName, studentCode);
        }
    }

    private String formatString(String str) {
        return str.toLowerCase().replaceAll("\\s+", " ").trim();
    }

}
