package com.example.studentghtk.student;

import com.example.studentghtk.score.FirstRoundScore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    private @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    Long id;
    private String school;
    private String district;
    private String studentCode;
    private String studentClass;
    private String fullName;
    private Date birthday;
    private String sex;
    private String birthplace;
    private String ethnic;
    private String address;
    private String phone;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    private FirstRoundScore score;

}
