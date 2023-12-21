package api.gamestore.util;

import java.util.regex.Pattern;

public class DescriptionValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o text seja uma String
        String text = (String) value;

        // Validador para garantir que o text contenha qualquer caractere, incluindo especiais
        if (!Pattern.matches(".*", text)) {

            throw new RuntimeException("Description inválido: Pode conter qualquer caractere, incluindo especiais.");
        }
    }
}
