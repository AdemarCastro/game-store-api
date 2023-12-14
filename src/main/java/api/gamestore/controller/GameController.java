package api.gamestore.controller;

import api.gamestore.model.Game;
import api.gamestore.service.GameService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api-gamestore/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> find() {
        try {
            // Chama o método 'find()' do serviço 'gameService' para obter uma lista de games
            if (gameService.find().isEmpty()) {
                // 2. Se a lista estiver vazia, retorna uma resposta 404 (Not Found)
                return ResponseEntity.notFound().build();
            }

            // Retorna uma resposta 200 (OK) contendo a lista de games no corpo da resposta
            return ResponseEntity.ok(gameService.find());
        } catch (Exception e) {

        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {

        try {
            // 1. Chama o método 'delete()' do serviço 'travelService' para excluir games
            gameService.delete();

            // 2. Retorna uma resposta HTTP 204 (No Content) indicando sucesso sem conteúdo no corpo da resposta
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // 3. Se ocorrer uma exceção durante a exclusão, registra o erro no log
            logger.error(e);

            // 4. Retorna uma resposta HTTP 500 (Internal Server Error) com a exceção no corpo da resposta
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Game> create(@RequestBody JSONObject game) {

        try {

            if (gameService.isJSONValid(game.toString())) {

                Game gameCreated = gameService.create(game);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(gameCreated.getId().toString()).build().toUri();

                gameService.add(gameCreated);
                return ResponseEntity.created(uri).body(null);
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {

            logger.error("Os campos JSON não são analisáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<Game> update(@PathVariable("id") Integer id, @RequestBody JSONObject game) {

        try {

            if (gameService.isJSONValid(game.toString())) {

                Game gameToUpdate = gameService.findById(id);

                if (gameToUpdate == null) {

                    logger.error("Game não encontrado.");
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

            logger.error("Os campos JSON não são analisáveis. " + e);
            System.out.println("Os campos JSON não são analisáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
