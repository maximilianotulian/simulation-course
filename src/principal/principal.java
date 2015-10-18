/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import espera.*;
import generadores.Libreria;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 *
 * @author MaximilianoDaniel
 */
public class principal {
    public static void main(String[] args) {
        ArrayList<double[]> mediasTotal = new ArrayList<>();
        ArrayList<Double> mediaServicio;  //utilización de servidor aux[1]
        ArrayList<Double> mediaDemora; // demora promedio aux[2]
        ArrayList<Double> mediaCola; // numero medio en cola aux[0]
        double[] auxMedia;//recibe las medias de los parametros desde el reporte
        double[] medias;  //acumula las medias de todas las réplicas
        
        // hacer un for con mil para hacer 1000 réplicas automaticamente
        for  (int replicas = 100; replicas <= 1000; replicas += 100) {
            mediaServicio = new ArrayList<>();
            mediaDemora = new ArrayList<>();
            mediaCola = new ArrayList<>();
            auxMedia = new double[3];
            medias = new double[3];
            
            for (int i = 0; i <= replicas; i++) { 
            Espera modelo = new Espera();

            //El primer argumento es el modelo y el segundo el tiempo final de simulación
            //Para este caso práctico lo setie en 10
            //En un futuro se debería poder cambiar.   
                //System.out.print("---------------\n");    
                //System.out.print("replica: " + (i+1) +"\n");

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

            muestra(replicas,medias[0],medias[1],medias[2]);
            mediasTotal.add(medias);
        }
    }
    
    public static void muestra (int replicas, double media0, double media1, double media2) {
        
        System.out.print("--------Media de las "+replicas+" réplicas-------\n");  
        System.out.println("número medio en cola " + Libreria.darFormato(media0 / replicas, 5));
        System.out.println("utilización del servidor " + Libreria.darFormato(media1 / replicas, 5));
        System.out.println("demora promedio " + Libreria.darFormato(media2 / replicas, 5));
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