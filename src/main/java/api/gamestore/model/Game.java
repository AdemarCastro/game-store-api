package api.gamestore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

    @Id
    private String id; // Adicionando um campo de identificação para ser usado como _id no MongoDB

    @Indexed(unique = true)
    private String name;

    private String genero;

    private String description;

    private Number year;

    private Number price;

    private String urlImage;

    private String available;

    public Game() {

    }

    // Valores não obrigatórios como NULL
    public Game(String name, String genero, Number year, Number price, String available) {
        this.name = name;
        this.genero = genero;
        this.description = null; // Valor não obrigatório
        this.year = year;
        this.price = price;
        this.urlImage = null; // Valor não obrigatório
        this.available = available;
    }

    public Game(String name, String genero, String description, Number year, Number price, String urlImage, String available) {
        this.name = name;
        this.genero = genero;
        this.description = description;
        this.year = year;
        this.price = price;
        this.urlImage = urlImage;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getYear() {
        return year;
    }

    public void setYear(Number year) {
        this.year = year;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    // Método que imprime informações do Game
    @Override
    public String toString() {
        return String.format(
                "Game[id=%s, name=%s, description=%s, year=%s, price=%s, urlImage=%s, available=%s]",
                id, name, description, year, price, urlImage, available);
    }
}
