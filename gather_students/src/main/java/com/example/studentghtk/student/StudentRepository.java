package com.example.studentghtk.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName%")
    List<Student> findStudentsByFullNameContaining(@Param("fullName") String fullName);

    @Query("SELECT s FROM Student s WHERE s.studentCode LIKE %:studentCode%")
    List<Student> findStudentsByStudentCodeContaining(@Param("studentCode") String studentCode);

    @Query("SELECT s FROM Student s WHERE s.fullName LIKE '%:fullName%' OR s.studentCode LIKE %:studentCode%")
    List<Student> findStudentsByFullNameContainingAndStudentCodeContaining(@Param("fullName") String fullName, @Param("studentCode") String studentCode);
}
