package br.edu.cs.poo.ac.seguro.mediators;

public class StringUtils {
    private StringUtils() {}

    public static boolean ehNuloOuBranco(String str) {
        if (str == null){
           return true;
        }
        if (str.trim().isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean temSomenteNumeros(String input) {
        for (int i =0; i < input.length(); i++){
            char c = input.charAt(i);
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}