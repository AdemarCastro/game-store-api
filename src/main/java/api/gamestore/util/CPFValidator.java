package api.gamestore.util;

import java.util.logging.Logger;

public class CPFValidator implements Validator {

    @Override
    public void validator(Object value) throws IllegalArgumentException {
        // Logger
        final Logger logger = Logger.getLogger(CPFValidator.class.getName());

        String cpf = (String) value;

        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            logger.severe("ERRO: CPF não possui 11 dígitos.");
            throw new IllegalArgumentException("CPF não possui 11 dígitos.");
        }

        // Verificar se todos os dígitos são iguais (CPF inválido)
        boolean allDigitsEqual = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allDigitsEqual = false;
                break;
            }
        }
        if (allDigitsEqual) {
            logger.severe("ERRO: CPF não pode possuir todos os dígitos iguais.");
            throw new IllegalArgumentException("CPF não pode possuir todos os dígitos iguais.");
        }

        // Calcular o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Verificar o primeiro dígito verificador
        if (cpf.charAt(9) - '0' != firstDigit) {
            logger.severe("ERRO: Insira um CPF válido.");
            throw new IllegalArgumentException("Insira um CPF válido.");
        }

        // Calcular o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        // Verificar o segundo dígito verificador
        if (cpf.charAt(10) - '0' != secondDigit) {
            logger.severe("ERRO: Insira um CPF válido.");
            throw new IllegalArgumentException("Insira um CPF válido.");
        }
    }
}
