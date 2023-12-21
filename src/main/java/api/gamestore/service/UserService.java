package api.gamestore.service;

import api.gamestore.model.User;
import api.gamestore.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    // Lista para armazenar objetos User
    private List<User> users;

    // // Cria uma instância de 'ArrayList' para armazenar os usuários ('users') se essa lista ainda não foi inicializada
    public void createUserLits() {

        if (users == null) {

            users = new ArrayList<>();
        }
    }

    // Verifica se uma string JSON é válida utilizando a biblioteca Jackson ('ObjectMapper')
    public boolean isJSONValid(String jsonInString) {
        // ObjectMapper = Usado para converter JSON para objetos Java e vice-versa
        // ReadTree = Usado para ler a árvore JSON da String
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (jsonInString != null && objectMapper.readTree(jsonInString) != null) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }


    // Valida o campo "cpf" do objeto JSON
    private void validateCPF(String cpf) {

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.validator(cpf);
    }

    // Valida o campo "name" do objeto JSON
    private void validateName(String name) {

        NameValidator nameValidator = new NameValidator();
        nameValidator.validator(name);
    }

    // Valida o campo "email" do objeto JSON
    private void validateEmail(String email) {

        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validator(email);
    }

    // Valida o campo "password" do objeto JSON
    private void validatePassword(String password) {

        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.validator(password);
    }

    // Valida o campo "urlImage" do objeto JSON
    private void validateURL(String urlImage) {

        URLValidator urlImageValidator = new URLValidator();
        urlImageValidator.validator(urlImage);
    }

    // Valida o campo "phone" do objeto JSON
    private void validatePhone(String phone) {

        PhoneValidator phoneValidator = new PhoneValidator();
        phoneValidator.validator(phone);
    }

    // Este método configura os valores do objeto User com base nos valores do objeto JSON
    public void setUserValues(JSONObject jsonUser, User user) {

        String cpf = (String) jsonUser.get("cpf");
        validateCPF(cpf);

        String name = (String) jsonUser.get("name");
        validateName(name);

        String email = (String) jsonUser.get("email");
        validateEmail(email);

        String password = (String) jsonUser.get("password");
        validatePassword(password);

        String urlImage = (String) jsonUser.get("urlImage");
        validateURL(urlImage);

        String phone = (String) jsonUser.get("phone");
        validatePhone(phone);

        user.setCpf(cpf != null ? cpf : user.getCpf());
        user.setName(name != null ? name : user.getName());
        user.setEmail(email != null ? email : user.getEmail());
        user.setPassword(password != null ? password : user.getPassword());
        user.setUrlImage(urlImage != null ? urlImage : user.getUrlImage());
        user.setPhone(phone != null ? phone : user.getPhone());
        user.setDataCadastro(new Date());
    }

    public User create(JSONObject jsonUser) throws JSONException {

        User user = new User();
        setUserValues(jsonUser, user);

        return user;
    }

    // Método para criar um User com base em um objeto JSON
    public User user(JSONObject jsonUser) throws JSONException {

        User user = new User();
        setUserValues(jsonUser, user);

        return user;
    }

    // Método para atualizar um User com base em um objeto JSON
    public User update(User user, JSONObject jsonUser) throws JSONException {

        setUserValues(jsonUser, user);
        return user;
    }

    // Método para adicionar um User à lista
    public void add(User user) {

        createUserLits();
        users.add(user);
    }

    // Método para encontrar todos os Users na lista
    public List<User> find() {

        createUserLits();
        return users;
    }

    // Método para encontrar um User pelo CPF na lista
    public User findByCpf(String cpf) {

        // Retorna null se nenhum usuário for encontrado com o CPF especificado
        return users.stream().filter(u -> cpf.equals(u.getCpf())).findFirst().orElse(null);
    }

    // Método para limpar a lista de Games
    public void delete() {

        users.clear();
    }

    // Método para limpar as instâncias de objetos
    public void clearObjects() {

        users = null;
    }
}
