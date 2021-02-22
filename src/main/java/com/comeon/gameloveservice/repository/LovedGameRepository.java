package com.comeon.gameloveservice.repository;

import com.comeon.gameloveservice.model.LovedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LovedGameRepository extends JpaRepository<LovedGame, Long> {
    @Query(value = "SELECT * FROM LOVED_GAME" +
            " WHERE LOVED_GAME.GAME_ID = ?1 AND LOVED_GAME.PLAYER_ID = ?2", nativeQuery = true)
    Optional<LovedGame> findPlayerLovedGame(Long gameId, Long playedId);

    @Query(value = "SELECT GAME.ID, COUNT(GAME.ID) FROM LOVED_GAME" +
            " INNER JOIN GAME ON LOVED_GAME.GAME_ID = GAME.ID" +
            " GROUP BY (GAME.ID) LIMIT ?1", nativeQuery = true)
    List<Object> findTopLovedGames(Integer numberOfTopLoved);
}
