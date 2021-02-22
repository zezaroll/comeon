package com.comeon.gameloveservice.repository;

import com.comeon.gameloveservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
