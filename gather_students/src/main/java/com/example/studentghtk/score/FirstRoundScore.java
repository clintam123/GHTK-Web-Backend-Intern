package com.example.studentghtk.score;

import com.example.studentghtk.student.Student;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "first_round_score")
public class FirstRoundScore
{
    private @Id
    @SequenceGenerator(
            name = "score_sequence",
            sequenceName = "score_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "score_sequence"
    )
    @Column(name = "id")
    Long id;
    private int firstYear;
    private int secondYear;
    private int thirdYear;
    private int fourthYear;
    private int fifthYear;
    private int priorityScore;
    @OneToOne(mappedBy = "score")
    private Student student;
}
