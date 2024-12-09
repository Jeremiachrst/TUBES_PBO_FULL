package com.iseng.web.controller;

import com.iseng.web.models.Peserta;
import com.iseng.web.models.Question;
import com.iseng.web.models.Quiz;
import com.iseng.web.models.Result;
import com.iseng.web.repository.RepositoriResult;
import com.iseng.web.service.ServicePartisipan;
import com.iseng.web.service.ServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/quiz")
public class QuizController {


    @Autowired
    private ServiceQuiz serviceQuiz;

    @Autowired
    private RepositoriResult repositoryResult;

    // Ambil soal untuk quiz tertentu
    @GetMapping("/{quizId}/questions")
    public List<Question> getQuestions(@PathVariable Long quizId) {
        return serviceQuiz.getQuestionsForQuiz(quizId);
    }

    @PostMapping("/{quizId}/submit")
    public Result submitQuiz(@PathVariable Long quizId, @RequestBody List<String> userAnswers) {
        // Ambil soal berdasarkan quizId
        List<Question> questions = serviceQuiz.getQuestionsForQuiz(quizId);

        // Hitung skor berdasarkan jawaban pengguna
        int score = serviceQuiz.calculateScore(questions, userAnswers);

        // Ambil Quiz berdasarkan quizId
        Quiz quiz = serviceQuiz.getQuizById(quizId); // Menambahkan method untuk mengambil quiz

        // Simpan hasil quiz ke database
        Result result = new Result();
        result.setScore(score);
        result.setQuiz(quiz); // Set relasi Quiz
        result.setPeserta(new Peserta("some-participant-id")); // Anda dapat mengambil ID peserta dari session/token

        // Simpan result ke dalam database
        result = repositoryResult.save(result);

        return result; // Kembalikan hasil yang sudah disimpan
    }

    // Endpoint untuk mendapatkan daftar quiz yang tersedia
    @GetMapping("/get-available-quizzes")
    public List<Quiz> getAvailableQuizzes() {
        return serviceQuiz.getAllQuizzes();  // Mengambil semua quiz dari service
    }

    @GetMapping("/questions/{quizName}")
    public List<Question> getQuestionsByQuizName(@PathVariable String quizName) {
        // Mencari quiz berdasarkan nama
        Quiz quiz = serviceQuiz.getQuizByName(quizName); // Menambahkan method untuk mengambil quiz berdasarkan nama
        if (quiz != null) {
            // Mengambil soal berdasarkan quizId
            return serviceQuiz.getQuestionsForQuiz(quiz.getId());
        } else {
            // Jika quiz tidak ditemukan, kembalikan status 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with name " + quizName + " not found");
        }
    }
}
