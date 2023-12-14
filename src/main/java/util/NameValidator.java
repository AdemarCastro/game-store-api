package util;

import java.util.regex.Pattern;

public class NameValidator {

    public void validator(Object value) {

        // Garante que o name seja uma String
        String name = (String) value;

        // Validador para garantir que o name contenha penas Letras, Espaços e Acentos
        if (!Pattern.matches("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+", name)) {

            throw new RuntimeException("Nome inválido: Somente pode conter letras, espaços e acentos.");
        }
    }
}
