package api.gamestore.util;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o password seja uma String
        String password = (String) value;

        // Validador para garantir que o password contenha apenas letras, números e caracteres especiais
        if (!Pattern.matches("^[a-zA-Z0-9!@#$%^&*()-_+=<>?/{}|:;]+$", password)) {

            throw new RuntimeException("Password inválido: Pode conter apenas letras, números e caracteres especiais.");
        }
    }
}
