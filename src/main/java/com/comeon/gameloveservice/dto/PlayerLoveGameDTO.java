package com.comeon.gameloveservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PlayerLoveGameDTO {
    @NonNull
    private Long playerId;
}