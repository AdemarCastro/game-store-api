package util;

public class EmailValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o value seja uma String
        String email = (String) value;

        // Verifica se o email está no formato correto
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$")) {

            throw new RuntimeException("Email inválido, insira no formato: exemplo@exemplo.com.");
        }
    }
}
