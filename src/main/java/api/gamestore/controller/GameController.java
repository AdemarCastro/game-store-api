package api.gamestore.controller;

import lombok.extern.slf4j.Slf4j;
import api.gamestore.exception.GameDeleteException;
import api.gamestore.exception.GameNotFoundException;
import api.gamestore.model.Game;
import api.gamestore.service.GameService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api-gamestore/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> find() {
        try {
            // Fazendo a varredura de todos os jogos
            List<Game> games = gameService.find();

            // Verificando se há jogos na lista
            if (games.isEmpty()) {

                // Lançamento de exceção para caso não encontre nenhum jogo cadastrado
                throw new GameNotFoundException("Nenhum jogo encontrado.");
            }

            // Retorna a lista com os jogos
            return ResponseEntity.ok(games);
        } catch (GameNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        try {

            // Deletando todos os jogos cadastrados
            gameService.delete();

            // Verificar se ainda há jogos cadastrados
            List<Game> games = gameService.find();

            if (!games.isEmpty()) {
                // Lançamento de exceção para caso não encontre nenhum jogo cadastrado
                throw new GameDeleteException("Ainda há jogos cadastrados.");
            }

            // Retorna uma resposta HTTP 204 (No Content) indicando sucesso sem um conteúdo no corpo da resposta
            return ResponseEntity.noContent().build();
        } catch (GameDeleteException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {

            log.error("ERRO: Exclusão de games falhou!", e);
            // Retorna uma resposta HTTP 500 (Internal Server Error) com a exceção no corpo da resposta
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Game> create(@RequestBody JSONObject game) {

        try {

            // Verifica se o JSON é válido
            if (gameService.isJSONValid(game.toString())) {

//                // Verifica se há um cadastro com este ID
//                Integer gameId = (Integer) game.get("id");
//                System.out.println(gameId);
//
//                // Verifica se o ID já está em uso
//                if (gameService.isIdAlreadyInUse(gameId)) {
//
//                    System.out.println("O ID já está em uso.");
//                    log.error("O ID já está em uso.");
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//                }

                Game gameCreated = gameService.create(game);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(gameCreated.getId().toString()).build().toUri();

                gameService.add(gameCreated);
                return ResponseEntity.created(uri).body(null);
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {

            log.error("Os campos JSON não são analisáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<Game> update(@PathVariable("id") Integer id, @RequestBody JSONObject game) {

        try {

            if (gameService.isJSONValid(game.toString())) {

                Game gameToUpdate = gameService.findById(id);

                if (gameToUpdate == null) {

                    log.error("Game não encontrado.");
                    System.out.println("Game não encontrado.");
                    return ResponseEntity.notFound().build();
                } else {

                    Game gameUpdate = gameService.update(gameToUpdate, game);
                    return ResponseEntity.ok(gameUpdate);
                }
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {

            log.error("Os campos JSON não são analisáveis. " + e);
            System.out.println("Os campos JSON não são analisáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
