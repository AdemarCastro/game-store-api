package api.gamestore.util;

import java.util.regex.Pattern;

public class DescriptionValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o text seja uma String
        String text = (String) value;

        // Validador para garantir que o text contenha caracteres alfanuméricos, espaços, pontuações e acentos
        if (!Pattern.matches("[\\p{L}0-9 .,;:'\"!?()-]+", text)) {

            throw new RuntimeException("Description inválido: Pode conter letras, números, espaços, pontuações, acentos e caracteres especiais.");
        }
    }
}
