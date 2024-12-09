package com.iseng.web.service;

import com.iseng.web.exception.QuizNotFoundException;
import com.iseng.web.models.Question;
import com.iseng.web.models.Quiz;
import com.iseng.web.models.Result;
import com.iseng.web.repository.RepositoriQuiz;
import com.iseng.web.repository.RepositoriResult;
import com.iseng.web.repository.RepositoriSoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceQuiz {

    @Autowired
    private RepositoriSoal questionRepository;

    @Autowired
    private RepositoriResult resultRepository;

    @Autowired
    private RepositoriQuiz quizRepository;

    // Ambil soal berdasarkan ID quiz
    public List<Question> getQuestionsForQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    // Validasi jawaban dan hitung skor
    public int calculateScore(List<Question> questions, List<String> userAnswers) {
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectAnswer().equals(userAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }

    // Simpan hasil ke database
    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    // Mendapatkan semua quiz yang tersedia
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();  // Mengambil semua quiz dari database
    }

    public Quiz getQuizById(Long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            return quiz.get(); // Mengembalikan quiz yang ditemukan
        } else {
            throw new RuntimeException("Quiz with id " + quizId + " not found");
        }
    }

    // Method untuk mengambil quiz berdasarkan nama
    public Quiz getQuizByName(String quizName) {
        // Find quiz by name and throw exception if not found
        return quizRepository.findByName(quizName)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with name " + quizName + " not found"));
    }



}
