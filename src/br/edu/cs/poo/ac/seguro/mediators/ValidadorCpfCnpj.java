package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {
    private static int calcularDigitoVerificador(String base, int[] pesos){
        int soma = 0;
        for (int i = 0; i < base.length(); i++){
            int digito = base.charAt(i) - '0';
            int peso = pesos[i];
            soma += digito * peso;
        }
        int resto = soma % 11;
        if (resto < 2){
            return 0;
        }
        return 11 - resto;
    }
    public static boolean ehCnpjValido(String cnpj) {
        boolean todosIguais = true;
        for (int i = 1; i < cnpj.length(); i++) {
            if (cnpj.charAt(i) != cnpj.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) return false;

        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        String base1 = cnpj.substring(0, 12);
        int digitoCalculado1 = calcularDigitoVerificador(base1, pesosPrimeiroDigito);
        int digitoReal1 = cnpj.charAt(12) - '0';
        if (digitoCalculado1 != digitoReal1) return false;
        String base2 = cnpj.substring(0, 13);
        int digitoCalculado2 = calcularDigitoVerificador(base2, pesosSegundoDigito);
        int digitoReal2 = cnpj.charAt(13) - '0';
        if(digitoCalculado2 != digitoReal2) return false;
        return true;
    }
    public static boolean ehCpfValido(String cpf) {
        boolean todosIguais = true;
        for (int i = 0; i < cpf.length(); i++){
            if (cpf.charAt(i) != cpf.charAt(0)){
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) return false;

        int[] pesosPrimeiroDigito = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        String base1 = cpf.substring(0, 9);
        int digitoCalculado1 = calcularDigitoVerificador(base1, pesosPrimeiroDigito);
        int digitoReal1 = cpf.charAt(9) - '0';
        if (digitoCalculado1 != digitoReal1) return false;
        String base2 = cpf.substring(0, 10);
        int digitoCalculado2 = calcularDigitoVerificador(base2, pesosSegundoDigito);
        int digitoReal2 = cpf.charAt(10) - '0';
        if(digitoCalculado2 != digitoReal2) return false;
        return true;
    }
}
