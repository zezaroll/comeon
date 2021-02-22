package com.comeon.gameloveservice.service;

import com.comeon.gameloveservice.dto.CreatePlayerDTO;
import com.comeon.gameloveservice.model.Game;
import com.comeon.gameloveservice.model.LovedGame;
import com.comeon.gameloveservice.model.Player;
import com.comeon.gameloveservice.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(CreatePlayerDTO createPlayerDTO) {
        Player player = new Player();
        player.setFirstName(createPlayerDTO.getFirstName());
        player.setLastName(createPlayerDTO.getLastName());
        player.setEmail(createPlayerDTO.getEmail());
        return playerRepository.save(player);
    }

    public List<Game> getAllPlayerLovedGames(Long id) {
        Optional<Player> playerGames = playerRepository.findById(id);
        return playerGames.map(player -> player.getLovedGames()
                .stream().map(LovedGame::getGame)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }
}
