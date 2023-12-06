package api.gamestore.service;

import api.gamestore.model.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private List<Game> games;

    // Cria uma instância de 'ArrayList' para armazenar os jogos ('games') se essa lista ainda não foi inicializada
    public void createGameList() {

        games = (games == null) ? new ArrayList<>() : null;
    }

    // Verifica se uma string JSON é válida utilizando a biblioteca Jackson ('ObjectMapper')
    public boolean isJSONValid(String jsonInString) {

        // ObjectMapper = Usado para converter JSON para objetos Java e vice-versa
        // ReadTree = Usado para ler a árvore JSON da String
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    // Este método configura os valores do objeto Game com base nos valores do objeto JSON
    private void setGameValues(JSONObject jsonGame, Game game) {
        String id = (String) jsonGame.get("id");
        String name = (String) jsonGame.get("name");
        String genero = (String) jsonGame.get("genero");
        String description = (String) jsonGame.get("description");
        Number year = (Number) jsonGame.get("year");
        Number price = (Number) jsonGame.get("price");
        String urlImage = (String) jsonGame.get("urlImage");
        String available = (String) jsonGame.get("available");



    }



}
