package com.comeon.gameloveservice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreatePlayerDTO {
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;
}
