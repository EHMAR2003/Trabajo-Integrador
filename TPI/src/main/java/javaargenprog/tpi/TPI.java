/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package javaargenprog.tpi;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author ehmar
 */
public class TPI {

    public static void main(String[] args) {
        String rutaResultados = "resultados.csv";
        String rutaPronostico = "pronostico.csv";
        int puntaje;
        puntaje = Calcular(rutaResultados, rutaPronostico);
        System.out.println("La cantidad de puntos obtenidos es de: " + puntaje);
    }    
    
    static int Calcular(String rutaResultados, String rutaPronostico){
        int puntos = 0;
        try{
            //FileInputStream fisPronostico = new FileInputStream(rutaPronostico);       
            //DataInputStream dataPronostico = new DataInputStream(fisPronostico);
            String renglonPronostico;
            for(String renglonResultados : Files.readAllLines(Paths.get(rutaResultados)) ){
                FileInputStream fisPronostico = new FileInputStream(rutaPronostico);       
                DataInputStream dataPronostico = new DataInputStream(fisPronostico);
                
                String vectorResultados[] = renglonResultados.split(";");
                renglonPronostico = dataPronostico.readLine();
                while (renglonPronostico != null){
                    String vectorPronostico[] = renglonPronostico.split(";");
                    if((vectorPronostico[0].equals(vectorResultados[0])) & (vectorPronostico[4].equals(vectorResultados[5]))){
                        if(vectorPronostico[1].equals("X") & Integer.parseInt(vectorResultados[3]) > Integer.parseInt(vectorResultados[4] )){
                           puntos += 1; 
                           //System.out.println("local");
                        }
                        if(vectorPronostico[3].equals("X") & Integer.parseInt(vectorResultados[3]) < Integer.parseInt(vectorResultados[4] )){
                           //System.out.println("visitante");
                            puntos += 1;                            
                        }
                        if(vectorPronostico[2].equals("X") & Integer.parseInt(vectorResultados[3]) == Integer.parseInt(vectorResultados[4] )){
                           puntos += 1; 
                           //System.out.println("empate");
                        }  
                        break;
                    }
                    renglonPronostico = dataPronostico.readLine();    
                }
                fisPronostico.close();
            }  
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return puntos;
    }

}
