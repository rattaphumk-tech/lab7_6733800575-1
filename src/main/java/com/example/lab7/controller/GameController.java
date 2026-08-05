package com.example.lab7.controller;

import com.example.lab7.model.Game;
import com.example.lab7.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    // Constructor Injection
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // READ All
    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "games/list";
    }

    // CREATE Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/add";
    }

    // CREATE Save
    @PostMapping("/add")
    public String saveGame(@ModelAttribute("game") Game game) {
        gameService.saveGame(game);
        return "redirect:/games";
    }

    // UPDATE Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Game game = gameService.getGameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        model.addAttribute("game", game);
        return "games/edit";
    }

    // UPDATE Save
    @PostMapping("/edit/{id}")
    public String updateGame(@PathVariable("id") Long id, @ModelAttribute("game") Game game) {
        game.setId(id);
        gameService.saveGame(game);
        return "redirect:/games";
    }

    // DELETE Form/Confirm
    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable("id") Long id, Model model) {
        Game game = gameService.getGameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        model.addAttribute("game", game);
        return "games/delete";
    }

    // DELETE Action
    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameService.deleteGame(id);
        return "redirect:/games";
    }
}