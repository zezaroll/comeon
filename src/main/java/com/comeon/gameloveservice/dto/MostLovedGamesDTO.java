package com.comeon.gameloveservice.dto;

import com.comeon.gameloveservice.model.Game;
import lombok.Data;

import java.math.BigInteger;

@Data
public class MostLovedGamesDTO {
    Game lovedGame;
    BigInteger loves;

    public MostLovedGamesDTO(Game lovedGame, BigInteger loves) {
        this.lovedGame = lovedGame;
        this.loves = loves;
    }
}
