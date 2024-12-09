package com.iseng.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id; // ID untuk hasil quiz

    // Relasi ManyToOne ke Peserta (peserta yang mengikuti quiz)
    @ManyToOne
    @JoinColumn(name = "nim", referencedColumnName = "nim")
    @Setter
    @Getter
    private Peserta peserta; // Peserta yang mengikuti quiz

    // Relasi ManyToOne ke Quiz (quiz yang diikuti)
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    @Setter
    @Getter
    private Quiz quiz; // Quiz yang diikuti

    @Setter
    @Getter
    private Integer score; // Skor peserta untuk quiz ini

    // Hapus atribut quizId, karena sudah ada relasi dengan entitas Quiz
}

