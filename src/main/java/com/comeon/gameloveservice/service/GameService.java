package com.comeon.gameloveservice.service;

import com.comeon.gameloveservice.dto.CreateGameDTO;
import com.comeon.gameloveservice.dto.MostLovedGamesDTO;
import com.comeon.gameloveservice.dto.PlayerLoveGameDTO;
import com.comeon.gameloveservice.model.Game;
import com.comeon.gameloveservice.model.LovedGame;
import com.comeon.gameloveservice.model.Player;
import com.comeon.gameloveservice.repository.GameRepository;
import com.comeon.gameloveservice.repository.LovedGameRepository;
import com.comeon.gameloveservice.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    final GameRepository gameRepository;
    final LovedGameRepository lovedGameRepository;
    final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository,
                       LovedGameRepository lovedGameRepository,
                       PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.lovedGameRepository = lovedGameRepository;
        this.playerRepository = playerRepository;
    }

    public Game createGame(CreateGameDTO newPlayer) {
        Game game = new Game();
        game.setName(newPlayer.getName());
        game.setDescription(newPlayer.getDescription());
        return gameRepository.save(game);
    }

    public List<MostLovedGamesDTO> getMostLovedGames(Integer numberOfTopLoved) {
        List<Object> topLovedGames = lovedGameRepository.findTopLovedGames(numberOfTopLoved);
        return getMostLovedIds(topLovedGames);
    }

    public LovedGame switchPlayerLoveGame(Long gameId, PlayerLoveGameDTO playerLoveGameDTO) {
        Optional<Player> player = playerRepository.findById(playerLoveGameDTO.getPlayerId());
        Optional<Game> game = gameRepository.findById(gameId);

        if (player.isPresent() && game.isPresent()) {
            Optional<LovedGame> lovedGame = lovedGameRepository
                    .findPlayerLovedGame(gameId, playerLoveGameDTO.getPlayerId());

            if (lovedGame.isPresent()) {
                lovedGameRepository.delete(lovedGame.get());
            } else {
                LovedGame newLovedGame = new LovedGame();
                newLovedGame.setGame(game.get());
                newLovedGame.setPlayer(player.get());
                return lovedGameRepository.save(newLovedGame);
            }
        }
        return new LovedGame();
    }

    private List<MostLovedGamesDTO> getMostLovedIds(List<Object> topLovedGames) {
        List<MostLovedGamesDTO> mostLovedGames = new ArrayList<>();

        for (Object item : topLovedGames) {
            Object[] tuple = (Object[]) item;
            BigInteger game = (BigInteger) tuple[0];
            BigInteger loves = (BigInteger) tuple[1];

            gameRepository.findById(game.longValue())
                    .ifPresent(value -> mostLovedGames.add(new MostLovedGamesDTO(value, loves)));
        }
        return mostLovedGames;
    }
}
