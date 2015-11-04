/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import espera.*;
import generadores.Libreria;
/**
 *
 * @author MaximilianoDaniel
 */
public class principal {
    public static void main(String[] args) {

        double[] auxMedia;//recibe las medias de los parametros desde el reporte
        
        /* inicio primera replica*/
            Espera modelo = new Espera();

            Inicializacion.Inicia(modelo, 1200);
            char aux = Tiempos.busca(modelo);
            while (modelo.getReloj() <= modelo.getTFS()) {  
                System.out.println(modelo.getReloj());
                if (aux  != 'z') {
                    switch(aux){
                        case 'A': {Arribo.funcion(modelo);}
                            //System.out.println("Arribo: "+ modelo.getReloj());
                            break;
                        case 'P': {Partida.funcion(modelo);}
                           // System.out.println("Partida: " + modelo.getReloj());
                            break;
                        default: break;
                    }
                }
                else{
                    System.out.println("Error");
                }
            modelo.setT(modelo.getReloj());
            aux = Tiempos.busca(modelo);
            }
            //agregamos las medias de la réplica
            auxMedia = Reporte.funcion(modelo);
            muestra(1,auxMedia[0],auxMedia[1],auxMedia[2]);
        /* fin primera replica*/
    }
    
    public static void muestra (int replicas, double media0, double media1, double media2) {
        
        System.out.print("--------Media de las "+replicas+" réplicas-------\n");  
        System.out.println("número medio en cola " + Libreria.darFormato(media0, 5));
        System.out.println("utilización del servidor " + Libreria.darFormato(media1, 5));
        System.out.println("demora promedio " + Libreria.darFormato(media2, 5));
    }
    
}