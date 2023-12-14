package api.gamestore.util;

import java.math.BigDecimal;

public class PriceValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Gantir que o price seja do tipo BigDecimal
        BigDecimal price = (BigDecimal) value;

        // Valida se o price está com números positivos
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException("Preço inválido: Insira um valor não negativo.");
        }
    }
}
