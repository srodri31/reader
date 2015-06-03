/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lector;

/**
 *
 * @author JuanDiego
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Lector {

    public Lector() {
    }
    
    public String leerArchivo(File f) {
        String aux;
        try {
            FileReader FReader = new FileReader(f);
            BufferedReader BReader = new BufferedReader(FReader);
            String texto = "";
            while ((aux = BReader.readLine()) != null) {
                texto = texto + aux + "\n";
            }
            BReader.close();
            FReader.close();
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public ArrayList<Integer> matching(String texto, String patron) {
        String t = texto.toLowerCase();
        String p = patron.toLowerCase();
        int n = t.length();
        int m = p.length();
        int c;
        ArrayList<Integer> apareos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            c = 0;
            for (int j = 0; j < m; j++) {
                if (t.charAt(i + j) == p.charAt(j)) {
                    c++;
                }else{
                    break;
                }
            }
            if (c == m) {
                apareos.add(i);
            }
        }
        return apareos;
    }

    private int minimo(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public int Levenshtein(String str1, String str2) {
        int[][] distancia = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distancia[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distancia[0][j] = j;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    distancia[i][j] = distancia[i - 1][j - 1];
                } else {
                    distancia[i][j] = minimo(distancia[i - 1][j] + 1,
                            distancia[i][j - 1] + 1,
                            distancia[i - 1][j - 1] + 1);
                }
            }
        }
        return distancia[str1.length()][str2.length()];
    }
}
