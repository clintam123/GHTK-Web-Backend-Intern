package com.example.studentghtk.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRoundScoreRepository extends JpaRepository<FirstRoundScore, Long> {
}
