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
 
        Espera modelo = new Espera();

        Inicializacion.Inicia(modelo, 1000);
        char aux = Tiempos.busca(modelo);
        while (modelo.getReloj() < modelo.getTFS()) {  
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
        Reporte.funcion(modelo);
    }
}