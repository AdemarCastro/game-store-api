package api.gamestore.util;

import java.util.regex.Pattern;

public class TextValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o name seja uma String
        String text = (String) value;

        // Validador para garantir que o text contenha penas Letras, Espaços e Acentos
        if (!Pattern.matches("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+", text)) {

            throw new RuntimeException("Nome inválido: Somente pode conter letras, espaços e acentos.");
        }
    }
}
