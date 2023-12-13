package api.gamestore.service;

import api.gamestore.model.Game;
import api.gamestore.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    // Este método configura os valores do objeto User com base nos valores do objeto JSON
    public void setUserValues(JSONObject jsonUser, User user) {

        String cpf = (String) jsonUser.get("cpf");
        String name = (String) jsonUser.get("name");
        String email = (String) jsonUser.get("email");
        String password = (String) jsonUser.get("password");
        String urlImage = (String) jsonUser.get("urlImage");
        String phone = (String) jsonUser.get("phone");
        Date dataCadastro = (Date) jsonUser.get("dataCadastro");

        user.setCpf(cpf != null ? cpf : user.getCpf());
        user.setName(name != null ? name : user.getName());
        user.setEmail(email != null ? email : user.getEmail());
        user.setPassword(password != null ? password : user.getPassword());
        user.setUrlImage(urlImage != null ? urlImage : user.getUrlImage());
        user.setPhone(phone != null ? phone : user.getPhone());
        user.setDataCadastro(dataCadastro != null ? dataCadastro : user.getDataCadastro());
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

        return users.stream().filter(u -> cpf == u.getCpf()).collect(Collectors.toList()).get(0);
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
