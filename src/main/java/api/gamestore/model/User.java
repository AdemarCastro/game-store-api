package api.gamestore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user")
public class User {

    @Id
    private String cpf;
    // Precisarei tratar o dado recebido com um transformador de String "000.000.000-00" para Number "00000000000" e consumir um Validador que confirme que é um CPF válido

    private String name;

    @Indexed(unique = true)
    private String email;
    // Preciso consumir um Validador que confirme o formato de String "exemplo@exemplo.com"

    private String password;
    // Preciso consumir um Validador que confirme não receber espaços em branco na senha

    private String urlImage;

    @Indexed(unique = true)
    private String phone;
    // Preciso de um Validador que confirme o formato "(00) 0 0000-0000"

    private Date dataCadastro;
    // Preciso de um Tratamento que transforme a Data Criada para um formato padrão de "dd, MM, yyyy, EEEE, H:i:s"

    public User() {
    }

    // Valores não obrigatórios como NULL
    public User(String cpf, String name, String email, String password, Date dataCadastro) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlImage = null; // Valor não obrigatório
        this.phone = null; // Valor não obrigatório
        this.dataCadastro = dataCadastro != null ? dataCadastro : new Date();
    }

    public User(String cpf, String name, String email, String password, String urlImage, String phone, Date dataCadastro) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlImage = urlImage;
        this.phone = phone;
        this.dataCadastro = dataCadastro != null ? dataCadastro : new Date();
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

    public Date getDataCadastro() {

        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {

        this.dataCadastro = dataCadastro;
    }

    // Método que imprime informações do User
    @Override
    public String toString() {
        return String.format(
                "User[cpf=%s, name=%s, email=%s, password=%s, urlImage=%s, phone=%s, dataCadastro=%s]",
                cpf, name, email, password, urlImage, phone, dataCadastro);
    }
}
