package api.gamestore.util;

import java.math.BigDecimal;
import java.time.Year;

public class YearValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garantir que o year é BigDecimal
        BigDecimal year = (BigDecimal) value;

        // Validar se o ano é nulo
        if (year == null) {

            throw new IllegalArgumentException("Ano inválido: Ano não pode ser nulo.");
        }

        // Validar se o ano é um número inteiro
        if (year.scale() > 0) {

            throw new IllegalArgumentException("Ano inválido: Insira um ano inteiro.");
        }

        // O primeiro jogo eletrônico foi criado em 1958
        BigDecimal startingYear = BigDecimal.valueOf(1958);
        BigDecimal currentYear = BigDecimal.valueOf(Year.now().getValue());

        // Validar se o ano está dentro do escopo do projeto
        if (year.compareTo(startingYear) < 0 || year.compareTo(currentYear) > 0) {

            throw new RuntimeException("Ano inválido: Insira um ano entre 1958 e " + currentYear.intValue());
        }
    }
}
