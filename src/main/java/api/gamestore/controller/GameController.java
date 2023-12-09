package api.gamestore.controller;

import api.gamestore.model.Game;
import api.gamestore.service.GameService;
import org.apache.coyote.Response;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api-gamestore/games")
public class GameController {

    private static final Logger logger = Logger.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> find() {
        // 1. Chama o método 'find()' do serviço 'gameService' para obter uma lista de games
        if (gameService.find().isEmpty()) {
            // 2. Se a lista estiver vazia, retorna uma resposta 404 (Not Found)
            return ResponseEntity.notFound().build();
        }

        // 3. Registra as informações da lista de games no log
        logger.info(gameService.find());

        // 4. Retorna uma resposta 200 (OK) contendo a lista de games no corpo da resposta
        return ResponseEntity.ok(gameService.find());
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
}
