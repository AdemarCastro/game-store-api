package api.gamestore.util;

public class URLValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garantir que o url seja do tipo String
        String url = (String) value;

        // Validar se o url é válido
        if (!url.matches("^(http|https)://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(/[a-zA-Z0-9_.~-]+)*(/[a-zA-Z0-9_.~-]+\\.[a-zA-Z]{2,})*$")) {

            throw new RuntimeException("URL inválido. Insira no formato: http(s)://www.exemplo.com");
        }
    }
}
