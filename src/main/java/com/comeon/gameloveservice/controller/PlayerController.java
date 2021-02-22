package com.comeon.gameloveservice.controller;

import com.comeon.gameloveservice.dto.CreatePlayerDTO;
import com.comeon.gameloveservice.model.Game;
import com.comeon.gameloveservice.model.Player;
import com.comeon.gameloveservice.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping()
    public Player createPlayer(@RequestBody CreatePlayerDTO playerDTO) {
        return playerService.createPlayer(playerDTO);
    }

    @GetMapping("/{id}/loved-games")
    public List<Game> playerLovedGames(@PathVariable Long id) {
        return playerService.getAllPlayerLovedGames(id);
    }
}
