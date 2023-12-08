package api.gamestore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "games")
public class Game {

    @Id
    private Number id; // Adicionando um campo de identificação para ser usado como _id no MongoDB

    @Indexed(unique = true)
    private String name;

    private String genero;

    private String description;

    private Number year;

    private Number price;

    private String urlImage;

    private String available;

    private Date dataCadastro;

    public Game() {

    }

    // Valores não obrigatórios como NULL
    public Game(Number id, String name, String genero, Number year, Number price, String available, Date dataCadastro) {
        this.id = id;
        this.name = name;
        this.genero = genero;
        this.description = null; // Valor não obrigatório
        this.year = year;
        this.price = price;
        this.urlImage = null; // Valor não obrigatório
        this.available = available;
        this.dataCadastro = dataCadastro != null ? dataCadastro : new Date();
    }

    public Game(Number id, String name, String genero, String description, Number year, Number price, String urlImage, String available, Date dataCadastro) {
        this.id = id;
        this.name = name;
        this.genero = genero;
        this.description = description;
        this.year = year;
        this.price = price;
        this.urlImage = urlImage;
        this.available = available;
        this.dataCadastro = dataCadastro != null ? dataCadastro : new Date();
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) { this.id = id; }

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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Método que imprime informações do Game
    @Override
    public String toString() {
        return String.format(
                "Game[id=%s, name=%s, description=%s, year=%s, price=%s, urlImage=%s, available=%s, dataCadastro=%s]",
                id, name, description, year, price, urlImage, available, dataCadastro);
    }
}
