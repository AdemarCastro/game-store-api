package api.gamestore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "games")
public class Game {

    @Id
    private Integer id; // Adicionando um campo de identificação para ser usado como _id no MongoDB

    @Indexed(unique = true)
    private String name;

    private String genero;

    private String description;

    private BigDecimal year;
    // Preciso consumir um Validador que confirme o formato de String "XXXX" e recuse valores abaixo de 1958 e acima de um ano 1 ano + ano atual

    private BigDecimal price;
    // Transformar o formato para "0.000,00" padrão Brasileiro

    private String urlImage;

    private String available;

    private Date dataCadastro;
    // Preciso de um Tratamento que transforme a Data Criada para um formato padrão de "dd, MM, yyyy, EEEE, H:i:s"

    public Game() {

    }

    // Valores não obrigatórios como NULL
    public Game(Integer id, String name, String genero, BigDecimal year, BigDecimal price, String available) {
        this.id = id;
        this.name = name;
        this.genero = genero;
        this.description = null; // Valor não obrigatório
        this.year = year;
        this.price = price;
        this.urlImage = null; // Valor não obrigatório
        this.available = available;
        this.dataCadastro = new Date();
    }

    public Game(Integer id, String name, String genero, String description, BigDecimal year, BigDecimal price, String urlImage, String available) {
        this.id = id;
        this.name = name;
        this.genero = genero;
        this.description = description;
        this.year = year;
        this.price = price;
        this.urlImage = urlImage;
        this.available = available;
        this.dataCadastro = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

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

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
