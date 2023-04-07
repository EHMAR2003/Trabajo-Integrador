/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package javaargenprog.tpijava;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ehmar
 */
public class TPIJava {

    public static void main(String[] args) {        
        //System.out.println("empiezo a leer archivo resultados.csv");
        //Path rutaResultados = Paths.get("C:/Users/ehmar/Documents/ARGENTINA PROGRAMA/JAVA/TPIJava/src/test/java/resultados.csv");
        Path rutaResultados = Paths.get(args[0]);
        List<String> lineasResultados = null;
        List<Partido> partidos = new ArrayList<Partido>();
        
        try{
            lineasResultados = Files.readAllLines(rutaResultados);           
        }
        catch(IOException e){
            System.out.println("Eror en lectura archivo: " + e.getMessage());
            System.exit(1);
        }
        boolean primerLinea = true;
        for(String lineaResultado: lineasResultados){
            if (primerLinea){  
                primerLinea = false;
            }
            else {
                String[] vectorResultados = lineaResultado.split(";");
                //System.out.println(lineaResultado);
                Equipo equipo1 = new Equipo(vectorResultados[0]);
                Equipo equipo2 = new Equipo(vectorResultados[4]);
                Partido partido = new Partido(equipo1, equipo2);
                partido.setGolesEquipo1(Integer.parseInt(vectorResultados[2]));
                partido.setGolesEquipo2(Integer.parseInt(vectorResultados[3]));
                partidos.add(partido);                
            }
        }
        // lee archivo pronostico.csv
        //Path rutaPronostico = Paths.get("C:/Users/ehmar/Documents/ARGENTINA PROGRAMA/JAVA/TPIJava/src/test/java/pronostico.csv");
        Path rutaPronostico = Paths.get(args[1]);
        List<String> lineasPronostico = null;
        List<Pronostico> pronosticos = new ArrayList<Pronostico>();
        
        try{
            lineasPronostico = Files.readAllLines(rutaPronostico);           
        }
        catch(IOException e){
            System.out.println("Eror en lectura archivo: " + e.getMessage());
            System.exit(1);
        }
        primerLinea = true;
        int puntos = 0;
        for(String lineaPronostico: lineasPronostico){
            if (primerLinea){  
                primerLinea = false;
            }
            else {
                String[] vectorPronostico = lineaPronostico.split(";");
                Equipo equipo1 = new Equipo(vectorPronostico[0]);
		Equipo equipo2 = new Equipo(vectorPronostico[4]);
		Partido partido = null;

		for (Partido partidovect : partidos) {
                    if (partidovect.getEquipo1().getNombre().equals(equipo1.getNombre())
			&& partidovect.getEquipo2().getNombre().equals(equipo2.getNombre())){						
			partido = partidovect;						
                    }
		}
                Equipo equipo = null;
                EnumResultado resultado = null;
                if(vectorPronostico[1].equals("X")){
                    equipo = equipo1;
                    resultado = EnumResultado.GANADOR;                    
                }
                if(vectorPronostico[2].equals("X")){
                    equipo = equipo1;
                    resultado = EnumResultado.EMPATE;                    
                }
                if(vectorPronostico[3].equals("X")){
                    equipo = equipo1;
                    resultado = EnumResultado.PERDEDOR;
                }
                Pronostico pronostico = new Pronostico(partido, equipo, resultado);        
                puntos += pronostico.puntos();
            }                                
        }
        System.out.println("El puntaje obtenido es de: " + puntos + " puntos");
    }
}
