/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import espera.*;
import generadores.Libreria;
import java.util.ArrayList;
/**
 *
 * @author MaximilianoDaniel
 */
public class principal {
    public static void main(String[] args) {
        
        ArrayList<Double> mediaServicio = new ArrayList<> (); //utilización de servidor aux[1]
        ArrayList<Double> mediaDemora = new ArrayList<> (); // demora promedio aux[2]
        ArrayList<Double> mediaCola = new ArrayList<> (); // numero medio en cola aux[0]
        double[] auxMedia = new double[3]; //recibe las medias de los parametros desde el reporte
        double[] medias = new double[3]; //acumula las medias de todas las réplicas
        
        int replicas = 1000;// hacer un for con mil para hacer 1000 réplicas automaticamente
        
        for (int i = 0; i < replicas; i++) { 
        Espera modelo = new Espera();
        
        //El primer argumento es el modelo y el segundo el tiempo final de simulación
        //Para este caso práctico lo setie en 10
        //En un futuro se debería poder cambiar.   
        System.out.print("---------------\n");    
        System.out.print("replica: " + (i+1) +"\n");
        
        Inicializacion.Inicia(modelo, 500);
        char aux = Tiempos.busca(modelo);
        while (modelo.getReloj() <= modelo.getTFS()) {  
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
        mediaCola.add(auxMedia[0]);
        mediaServicio.add(auxMedia[1]);
        mediaDemora.add(auxMedia[2]);
        }
        
        for (int i = 0; i < mediaCola.size() ; i += 1) {
            medias[0] += mediaCola.get(i); //número medio en cola
            medias[1] += mediaServicio.get(i); //utilización del servidor
            medias[2] += mediaDemora.get(i); //demora promedio
        }
        
        System.out.print("--------Media de las "+replicas+" réplicas-------\n");  
        System.out.println("número medio en cola " + Libreria.darFormato(medias[0] / replicas, 5));
        System.out.println("utilización del servidor " + Libreria.darFormato(medias[1] / replicas, 5));
        System.out.println("demora promedio " + Libreria.darFormato(medias[2] / replicas, 5));
        
        /*System.out.println(auxMedia[0]);0
        System.out.println(auxMedia[1]);
        System.out.println(auxMedia[2]);*/
    }
    
}

/*
tiempo medio de espera en cola= tiempo en cola / n
tiempo medio en el sistema= tiempo en sistema / n

Para regularizar 
tiempo medio en cola sobre tiempo en medio del sistema eje y
número de observaciones( que son las réplicas ) en el eje x 

para la réplica 1 
tenemos sum(tc/ts) / n

para réplica 2
tenemos i= 1 hasta 2 sum(tc/ts) / n

largar todos los valores en una fila separados por coma y que empieze con llave
*/
 //generar clases para histograma
       /* double vector[] = {0, 0, 0, 0, 0};
        double min = 10000; 
        double max = 0;
        for (int i = 0; i < modelo.getDemora().size(); i++) {
            if (modelo.getDemora().get(i) > max) {
                max = modelo.getDemora().get(i);
            }
            if (modelo.getDemora().get(i) < min) {
                min = modelo.getDemora().get(i);
            }
        }
        double incrementos = (max - min) / 5;
        double[] intervalos = new double[5];
        intervalos[0] = incrementos;
        for (int i = 1; i < intervalos.length; i++) {
            intervalos[i] = intervalos[i-1] + incrementos;
        }
        for (int i = 0; i < modelo.getDemora().size(); i++) {
            for (int j = 0; j < intervalos.length; j++) {
                if (modelo.getDemora().get(i) < intervalos[j]) {
                    vector[j] = vector[j]+1;
                } 
            }
        }
        for (int i = 0; i < vector.length; i++) {
            System.out.println(vector[i]);
            
        }*/
