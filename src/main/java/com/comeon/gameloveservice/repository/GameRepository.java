package com.comeon.gameloveservice.repository;

import com.comeon.gameloveservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
