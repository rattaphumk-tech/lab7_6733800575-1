package com.example.lab7.service;

import com.example.lab7.model.Game;
import com.example.lab7.repository.GameRepository;
import com.example.lab7.strategy.DiscountContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    // Constructor Injection
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        List<Game> games = gameRepository.findAll();
        for (Game game : games) {
            applyDiscount(game);
        }
        return games;
    }

    public Optional<Game> getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        game.ifPresent(this::applyDiscount);
        return game;
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    private void applyDiscount(Game game) {
        if (game.getPrice() != null) {
            DiscountContext context = new DiscountContext(game.getDiscountType());
            double finalPrice = context.executeStrategy(game.getPrice());
            game.setFinalPrice(finalPrice);
        }
    }
}