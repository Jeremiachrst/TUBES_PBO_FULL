package com.iseng.web.service;

import com.iseng.web.dto.ParticipantDto;
import com.iseng.web.models.Peserta;
import com.iseng.web.models.Quiz;
import com.iseng.web.models.Result;
import com.iseng.web.repository.RepositoriPeserta;
import com.iseng.web.repository.RepositoriQuiz;
import com.iseng.web.repository.RepositoriResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePartisipan {

    @Autowired
    private RepositoriPeserta participantRepository;

    @Autowired
    private RepositoriQuiz quizRepository;

    @Autowired
    private RepositoriResult resultRepository;


    // Mendapatkan nilai peserta berdasarkan quiz
    public Integer getScoreForQuiz(Peserta peserta, String quizName) {
        Optional<Quiz> quiz = quizRepository.findByName(quizName); // Cari quiz berdasarkan nama
        if (quiz != null) {
            List<Result> results = resultRepository.findByPeserta(peserta);
            for (Result result : results) {
                if (result.getQuiz().equals(quiz)) {
                    return result.getScore(); // Mengembalikan nilai peserta untuk quiz ini
                }
            }
        }
        return null; // Jika tidak ditemukan, kembalikan null
    }

    // Menyimpan nilai peserta untuk quiz
    public void saveResult(Peserta peserta, Quiz quiz, Integer score) {
        Result result = new Result();
        result.setPeserta(peserta);
        result.setQuiz(quiz);
        result.setScore(score);
        resultRepository.save(result);
    }

    public boolean isNimExist(String nim) {
        Peserta peserta = participantRepository.findByNim(nim);
        return peserta != null;
    }

    // Method untuk menyimpan data peserta
    public void saveParticipant(ParticipantDto participantDto) {
        // Membuat objek Participant dari ParticipantDto
        Peserta participant = new Peserta();
        participant.setName(participantDto.getName());
        participant.setEmail(participantDto.getEmail());
        participant.setNim(participantDto.getNim());

        // Menyimpan ke database
        participantRepository.save(participant);
    }

    public boolean isNimValid(String nim) {
        try {
            // Mengonversi nim ke Long dan mengecek apakah ada di database
            return participantRepository.existsByNim(nim);
        } catch (NumberFormatException e) {
            // Jika NIM tidak valid (bukan angka), kembalikan false
            return false;
        }
    }


    public String getUserNameByNim(String nim) {
        Peserta peserta = participantRepository.findByNim(nim);  // Cari peserta berdasarkan NIM
        if (peserta != null) {
            return peserta.getName();  // Kembalikan nama jika ditemukan
        } else {
            return null;  // Kembalikan null jika peserta tidak ditemukan
        }
    }

}
