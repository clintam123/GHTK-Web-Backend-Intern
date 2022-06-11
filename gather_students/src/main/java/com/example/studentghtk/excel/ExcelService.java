package com.example.studentghtk.excel;

import com.example.studentghtk.student.Student;
import com.example.studentghtk.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelService {
    @Autowired
    StudentRepository studentRepository;

    public void save(MultipartFile file) {
        try {
            List<Student> students = ExcelHelper.excelToStudents(file.getInputStream());
            studentRepository.saveAll(students);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}