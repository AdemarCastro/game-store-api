package api.gamestore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Id
    private String cpf;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String urlImage;

    @Indexed(unique = true)
    private String phone;

    public User() {
    }

    // Valores não obrigatórios como NULL
    public User(String cpf, String name, String email, String password) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlImage = null; // Valor não obrigatório
        this.phone = null; // Valor não obrigatório
    }

    public User(String cpf, String name, String email, String password, String urlImage, String phone) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlImage = urlImage;
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Método que imprime informações do User
    @Override
    public String toString() {
        return String.format(
                "User[cpf=%s, name=%s, email=%s, password=%s, urlImage=%s, phone=%s]",
                cpf, name, email, password, urlImage, phone);
    }
}
