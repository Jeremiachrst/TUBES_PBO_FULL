package com.iseng.web.repository;

import com.iseng.web.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iseng.web.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoriSoal extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz); // Mencari soal berdasarkan quiz
    List<Question> findByQuizId(Long quizId); // Ambil soal berdasarkan ID quiz
}