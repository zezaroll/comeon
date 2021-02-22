package com.comeon.gameloveservice.controller;

import com.comeon.gameloveservice.dto.CreateGameDTO;
import com.comeon.gameloveservice.dto.MostLovedGamesDTO;
import com.comeon.gameloveservice.dto.PlayerLoveGameDTO;
import com.comeon.gameloveservice.model.Game;
import com.comeon.gameloveservice.model.LovedGame;
import com.comeon.gameloveservice.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Game createGamePlayer(@RequestBody CreateGameDTO createGameDTO) {
        return gameService.createGame(createGameDTO);
    }

    @PostMapping("/{id}")
    public LovedGame setPlayerLoveGame(@PathVariable("id") Long gameId,
                                       @RequestBody() PlayerLoveGameDTO playerLoveGameDTO) {
        return gameService.switchPlayerLoveGame(gameId, playerLoveGameDTO);
    }

    @GetMapping()
    public List<MostLovedGamesDTO> getMostLovedGame(@RequestParam("top") Integer top) {
        return gameService.getMostLovedGames(top);
    }
}
