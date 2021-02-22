package com.comeon.gameloveservice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateGameDTO {
    @NonNull()
    private String name;

    @NonNull()
    private String description;
}
