package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    
    public static List<String[]> lerCSV(String caminhoArquivo) {
        List<String[]> dados = new ArrayList<>();
        String linha;
        
        System.out.println("📄 Tentando ler arquivo CSV: " + caminhoArquivo);
        
        try {
            // Tenta ler do classpath primeiro
            InputStream is = CSVReader.class.getClassLoader().getResourceAsStream("calculos.csv");
            BufferedReader br;
            
            if (is != null) {
                System.out.println("📁 Arquivo encontrado no classpath");
                br = new BufferedReader(new InputStreamReader(is));
            } else {
                System.out.println("📁 Lendo arquivo do caminho: " + caminhoArquivo);
                br = new BufferedReader(new FileReader(caminhoArquivo));
            }
            
            // Pula o cabeçalho
            String cabecalho = br.readLine();
            System.out.println("📋 Cabeçalho CSV: " + cabecalho);
            
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",");
                dados.add(valores);
                System.out.println("📊 Linha carregada: " + String.join(" | ", valores));
            }
            br.close();
            
            System.out.println("✅ CSV carregado com sucesso! Total de linhas: " + dados.size());
            
        } catch (IOException e) {
            System.err.println("❌ Erro ao ler arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
        
        return dados;
    }
}