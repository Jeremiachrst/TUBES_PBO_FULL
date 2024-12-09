package com.iseng.web.repository;

import com.iseng.web.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoriQuiz extends JpaRepository<Quiz, Long>{
    Optional<Quiz> findByName(String name);
}
