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
        
        try {
            // Tenta ler do classpath primeiro
            InputStream is = CSVReader.class.getClassLoader().getResourceAsStream("calculos.csv");
            BufferedReader br;
            
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is));
            } else {
                // Se não encontrar no classpath, tenta ler do caminho do arquivo
                br = new BufferedReader(new FileReader(caminhoArquivo));
            }
            
            // Pula o cabeçalho
            br.readLine();
            
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",");
                dados.add(valores);
            }
            br.close();
            
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
        
        return dados;
    }
}