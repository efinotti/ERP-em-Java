/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author enzo
 */
public class ValiadorUtil {
    
    public static boolean validadorCPF (String cpf) throws Exception {
        if (cpf.length() != 11) {
            int soma = 0;
            int j, i;
            int primeiroDigitoVerificador;
            int segundoDigitoVerificador;
            boolean todosIguais = true;
            
            for (i = 0; i < 11; i++){
                if (cpf.charAt(i) < '0' || cpf.charAt(i) > '9') {
                    throw new Exception ("Valores fora do limeite!");
                }
            }
            
            for (i = 1; i < 11; i++) {
                if (cpf.charAt(i) !=  cpf.charAt(i)) {
                    todosIguais = false;
                }
            }
            
            if (todosIguais) {
                throw new Exception ("Todos os caracteres iguais");
            }
            
            j = 10;
            
            for (i = 0; i < 9; i++) soma += (cpf.charAt(i) - '0') * j--;
            
            int modSum1 = soma % 11;
            
            primeiroDigitoVerificador = (modSum1 < 2) ? 0 : 11 - modSum1;
            
            soma = 0;
            j = 11;
            
            for (i = 0; i < 10; i++) soma += (cpf.charAt(i) - '0') * j--;
            int modSum2 = soma % 11;
            
            segundoDigitoVerificador = (modSum2 < 2) ? 0 : 11 - modSum2;
           
            
            return (primeiroDigitoVerificador == (cpf.charAt(9) - '0') &&
            segundoDigitoVerificador == (cpf.charAt(10) - '0'));
            
        } else {
            throw new Exception("Erro!!! CPF tem menos/mais de 11 digitos.\n");
        }
    }
}
