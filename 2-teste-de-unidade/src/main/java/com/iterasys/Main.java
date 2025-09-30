// 1 - Bibliotecas / Imports
// 1.1 - Pacotes
package com.iterasys;

// 2 - Classes
public class Main {
    // 2.1 - Atributos

    // 2.2 - Métodos e Funções
    public static void main(String[] args) {
        System.out.println("Teste de Unidade: ");
    }

    public static float volumeCubo(float lado) {
        System.out
                .println("Calculando o volume do cubo com lado " + lado + " .Resultado: " + (float) Math.pow(lado, 3));
        return (float) Math.pow(lado, 3);
    }

    public static float volumePararelelepipedo(float comprimento, float largura, float altura) {
        System.out.println("Calculando o volume do paralelepípedo com comprimento " + comprimento +
                ", largura " + largura + " e altura " + altura + " .Resultado: " + (comprimento * largura * altura));
        return comprimento * largura * altura;
    }

    public static float volumeEsfera(float raio) {
        float volume = (float) ((4.0 / 3.0) * Math.PI * Math.pow(raio, 3));
        System.out.println("Calculando o volume da esfera com raio " + raio + " .Resultado: " + volume);
        return volume;
    }

}
