package com.comeon.gameloveservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "loved_game")
public class LovedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
}