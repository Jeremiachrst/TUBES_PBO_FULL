package com.iseng.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @Setter
    @Getter
    private Quiz quiz; // Quiz yang terkait dengan soal ini

    @Setter
    @Getter
    private String questionText; // Teks soal

    @Setter
    @Getter
    private String optionA; // Opsi A
    @Setter
    @Getter
    private String optionB; // Opsi B
    @Setter
    @Getter
    private String optionC; // Opsi C
    @Setter
    @Getter
    private String optionD; // Opsi D

    @Setter
    @Getter
    private String correctAnswer; // Jawaban yang benar
}
