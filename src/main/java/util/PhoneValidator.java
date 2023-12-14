package util;

public class PhoneValidator implements Validator{

    @Override
    public void validator(Object value) {

        // Garante que o formato do value seja String
        String num = (String) value;

        if (!num.matches("^\\+\\d{2} \\(\\d{2}\\) \\d{5}-\\d{4}$")) {

            throw new RuntimeException("Telefone inválido, insira no formato: +DD (XX) XXXXX-XXXX.");
        }
    }
}
