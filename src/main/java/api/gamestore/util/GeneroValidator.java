package api.gamestore.util;

import java.util.regex.Pattern;

public class GeneroValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o name seja uma String
        String text = (String) value;

        // Validador para garantir que o text contenha apenas Letras, Espaços, Acentos e Pontos
        if (!Pattern.matches("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ .]+", text)) {

            throw new RuntimeException("Genero inválido: Somente pode conter letras, espaços, acentos e pontos.");
        }
    }
}
