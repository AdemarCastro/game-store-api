package api.gamestore.service;

import api.gamestore.exception.IdRegisteredException;
import api.gamestore.model.Game;
import api.gamestore.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    // Lista para armazenar objetos Game
    private List<Game> games;

    // Cria uma instância de 'ArrayList' para armazenar os jogos ('games') se essa lista ainda não foi inicializada
    public void createGameList() {

        if (games == null) {

            games = new ArrayList<>();
        }
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

    // Converte o campo "id" de um objeto JSON para um inteiro
    private int parseId(JSONObject game) {

        return Integer.valueOf((int) game.get("id"));
    }

    // Converte o campo "year" de um objeto JSON para um BigDecimal
    private BigDecimal parseYear(JSONObject game) {

        BigDecimal year = new BigDecimal((String) game.get("year"));
        validateYear(year);
        return year;
    }

    // Converte o campo "price" de um objeto JSON para um BigDecimal
    private BigDecimal parsePrice(JSONObject game) {

        BigDecimal price = new BigDecimal((String) game.get("price"));
        validatePrice(price);
        return price;
    }

    // Valida o campo "name" do objeto JSON
    private void validateName(String name) {

        NameValidator nameValidator = new NameValidator();
        nameValidator.validator(name);
    }

    // Valida o campo "genero" do objeto JSON
    private void validateGenero(String genero) {

        GeneroValidator generoValidator = new GeneroValidator();
        generoValidator.validator(genero);
    }

    // Valida o campo "description" do objeto JSON
    private void validateDescription(String description) {

        DescriptionValidator descriptionValidator = new DescriptionValidator();
        descriptionValidator.validator(description);
    }

    // Valida o campo "year" do objeto JSON
    private void validateYear(BigDecimal year) {

        YearValidator yearValidator = new YearValidator();
        yearValidator.validator(year);
    }

    // Valida o campo "price" do objeto JSON
    private void validatePrice(BigDecimal price) {

        PriceValidator priceValidator = new PriceValidator();
        priceValidator.validator(price);
    }

    // Valida o campo "url" do objeto JSON
    private void validateUrl(String url) {

        // Não trabalho com o valor Boolean, mas sim com uma respota de erro caso a url não passe no validador!
        URLValidator urlValidator = new URLValidator();
        urlValidator.validator(url);
    }

    // Valida o campo "available" do objeto JSON -> Pendente


    // Este método configura os valores do objeto Game com base nos valores do objeto JSON
    private void setGameValues(JSONObject jsonGame, Game game) {

        String name = (String) jsonGame.get("name");
        validateName(name);

        String genero = (String) jsonGame.get("genero");
        validateGenero(genero);

        String description = (String) jsonGame.get("description");
        validateDescription(description);

        String urlImage = (String) jsonGame.get("urlImage");
        validateUrl(urlImage);

        String available = (String) jsonGame.get("available");
        // Validação pendente

//        game.setId(jsonGame.get("id") != null ? checkId(parseId(jsonGame)) : game.getId());
        game.setId(jsonGame.get("id") != null ? parseId(jsonGame) : game.getId());
        game.setName(name != null ? name : game.getName());
        game.setGenero(genero != null ? genero : game.getGenero());
        game.setDescription(description != null ? description : game.getDescription());
        game.setYear(jsonGame.get("year") != null ? parseYear(jsonGame) : game.getYear());
        game.setPrice(jsonGame.get("price") != null ? parsePrice(jsonGame) : game.getPrice());
        game.setUrlImage(urlImage != null ? urlImage : game.getUrlImage());
        game.setAvailable(available != null ? available : game.getAvailable());
        game.setDataCadastro(new Date()); // Necessário fazer um tratamento dessa data para o formato (dd, MMMM, yyyy, EEEE)
    }

    public Game create(JSONObject jsonGame) throws JSONException {

        Game game = new Game();
        setGameValues(jsonGame, game);

        // Verificar se o ID já está em uso
        Integer gameId = game.getId();
        if (gameId != null && isIdAlreadyInUse(gameId)) {
            throw new IdRegisteredException("Erro: ID já está em uso. Escolhe um ID único.");
        }

        return game;
    }

    // Método para criar um Game com base em um objeto JSON
    public Game game(JSONObject jsonGame) throws JSONException {

        Game game = new Game();
        setGameValues(jsonGame, game);

        return game;
    }

    // Método para atualizar um Game com base em um objeto JSON
    public Game update(Game game, JSONObject jsonGame) throws  JSONException {

        setGameValues(jsonGame, game);
        return game;
    }

    // Método para adicionar um Game à lista
    public void add(Game game) {

        createGameList();
        games.add(game);
    }

    // Método para encontrar todos os games na lista
    public List<Game> find() {

        createGameList();
        return games;
    }

    // Método para encontrar um Game pelo ID na lista
    public Game findById(Integer id) {

        List<Game> gameList = games.stream().filter(g -> id == g.getId()).collect(Collectors.toList());
        return gameList.isEmpty() ? null : gameList.get(0);
    }

    // Método para verificar se já um cadastro com o mesmo ID
    private boolean isIdAlreadyInUse(Integer id) {

        // Inicializando a lista de games
        createGameList();

        if (games.isEmpty()) {
            return false;
        } else {
            return games.stream().anyMatch(game -> id.equals(game.getId()));
        }
    }

    // Método para limpar a lista de Games
    public void delete() {

        games.clear();
    }

    // Método para limpar as instâncias de objetos
    public void clearObjects() {

        games = null;
    }
}
