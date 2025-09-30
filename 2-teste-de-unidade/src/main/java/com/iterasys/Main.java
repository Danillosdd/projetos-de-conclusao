// 1 - Bibliotecas / Imports
// 1.1 - Pacotes
package com.iterasys;

// 2 - Classes
public class Main {
    // 2.1 - Atributos

    // 2.2 - Métodos e Funções
    public static void main(String[] args) {
        System.out.println("Teste de Áreas: ");
    }

    public static float quadrado(float lado) {
        System.out.println("Calculando a área do quadrado com lado " + lado + " .Resultado: " + (lado * lado));
        return lado * lado;
    }

    public static float retangulo(float base, float altura) {
        System.out.println("Calculando a área do retângulo com base " + base + " e altura " + altura + " .Resultado: " + (base * altura));
        return base * altura;
    }

    public static float circulo(float raio) {
        System.out.println("Calculando a área do círculo com raio " + raio + " .Resultado: " + (float) (Math.PI * Math.pow(raio, 2)));
        return (float) (Math.PI * Math.pow(raio, 2));
    }

    public static float triangulo(float base, float altura) {
        System.out.println("Calculando a área do triângulo com base " + base + " e altura " + altura + " .Resultado: " + (base * altura) / 2);
        return (base * altura) / 2;
    }

}
