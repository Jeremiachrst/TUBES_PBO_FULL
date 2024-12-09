package com.iseng.web.dto;

import lombok.Getter;
import lombok.Setter;

public class ParticipantDto {

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String nim;

    // Constructor
    public ParticipantDto() {}

    public ParticipantDto(String name, String email, String nim) {
        this.name = name;
        this.email = email;
        this.nim = nim;
    }

}
