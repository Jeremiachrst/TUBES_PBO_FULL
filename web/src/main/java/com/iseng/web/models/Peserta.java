package com.iseng.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "peserta")
public class Peserta {
    @Id
    @Setter
    @Getter
    private String nim;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String email;
    private Integer score;

    // Konstruktor tanpa argumen (akan digunakan oleh JPA)
    public Peserta() {
    }

    // Konstruktor dengan argumen nim atau participantId
    public Peserta(String nim) {
        this.nim = nim;
    }


    // Getters and Setters
}
