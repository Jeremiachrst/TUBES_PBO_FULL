package com.iseng.web.repository;

import com.iseng.web.models.Peserta;
import com.iseng.web.models.Quiz;
import com.iseng.web.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoriResult extends JpaRepository<Result, Long>{
    List<Result> findByPeserta(Peserta peserta); // Mencari hasil berdasarkan peserta
    List<Result> findByQuiz(Quiz quiz); // Mencari hasil berdasarkan quiz

}
