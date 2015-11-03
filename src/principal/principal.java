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
        
        /* 
        * Esta variable va a guardar los parametros de cada una de las réplicas
        * Para luego ser gráficadas por un programa externo 
        * 0 número medio en cola, utilización del servidor, demora promedio 
        */
        
        ArrayList<double[]> parametros = new ArrayList<>(); 
        
        double[] auxMedia;//recibe las medias de los parametros desde el reporte
        
        /*
        * mediaCola, mediaServicio y mediaDemora guarda la media de cada una de las replicas.
        * Es decir si tengo 100 observaciones, vamos a tener 100 valores dentro de cada una de las variables mencionadas. 
        */
        
        ArrayList<Double> mediaCola; // numero medio en cola aux[0]
        ArrayList<Double> mediaServicio;  //utilización de servidor aux[1]
        ArrayList<Double> mediaDemora; // demora promedio aux[2]
        
        /* 
        * acumula las medias de todas las réplicas para luego dividirlas por el total de replicas y calcular la media de la media.
        * la media de la media se guarda en la variable parámetros que luego se utilizará para gráficar.
        * el subindice 0 corresponde a la media en cola,
        * el subindice 1 corresponde a media de la utilización de servicio
        * el subindice 2 corresponde a la demora promedio
        */
        
        double[] medias;  
        
        /*
        * inicia, aumenta y cantReplicas se utilizan para manejar el bucle for de las réplicas
        */
        int inicia = 500; 
        int aumenta = 500; 
        int cantReplicas = 10000;  
        
        /* inicio primera replica*/
            Espera modelo = new Espera();

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
            muestra(1,auxMedia[0],auxMedia[1],auxMedia[2]);
            parametros.add(auxMedia);
        /* fin primera replica*/
        
        /* comienza bucle de replicas */
        for  (int replicas = inicia; replicas <= cantReplicas; replicas += aumenta) {
            mediaServicio = new ArrayList<>();
            mediaDemora = new ArrayList<>();
            mediaCola = new ArrayList<>();
            auxMedia = new double[3];
            medias = new double[3];
            
            for (int i = 0; i <= replicas; i++) { 
            modelo = new Espera();

            //El primer argumento es el modelo y el segundo el tiempo final de simulación
            //Para este caso práctico lo setie en 10
            //En un futuro se debería poder cambiar.   
                //System.out.print("---------------\n");    
                //System.out.print("replica: " + (i+1) +"\n");

            Inicializacion.Inicia(modelo, 500);
            aux = Tiempos.busca(modelo);
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
            
            medias[0] = medias[0] / replicas;
            medias[1] = medias[1] / replicas;
            medias[2] = medias[2] / replicas;
            
            muestra(replicas,medias[0],medias[1],medias[2]);
            parametros.add(medias);
        }
        /* fin bucle de replicas */
        
        /* inicio generación de string para gráficar con programa externo */
        String mediaFirst = "{";
        String mediaSecond = "{";
        String mediaThird = "{";
        for (int i = 0; i < parametros.size(); i += 1) {
            if(( i + 1) == parametros.size()){
                mediaFirst += parametros.get(i)[0];
                mediaSecond += parametros.get(i)[1];
                mediaThird += parametros.get(i)[2];
            } else {
                mediaFirst += parametros.get(i)[0] + " ,";
                mediaSecond += parametros.get(i)[1] + " ,";
                mediaThird += parametros.get(i)[2] + " ,";
            }
            
        }
        
        mediaFirst += " }";
        mediaSecond += " }";
        mediaThird += " }";
        /* Fin generación de string para gráficar con programa externo */
        
        /* Mostrar string para gráficar con programa externo */
        System.out.println("--------Datos para graficar--------");
        System.out.println("número medio en cola " + mediaFirst);
        System.out.println("utilización del servidor " + mediaSecond);
        System.out.println("demora promedio " + mediaThird);
    }
    
    public static void muestra (int replicas, double media0, double media1, double media2) {
        
        System.out.print("--------Media de las "+replicas+" réplicas-------\n");  
        System.out.println("número medio en cola " + Libreria.darFormato(media0, 5));
        System.out.println("utilización del servidor " + Libreria.darFormato(media1, 5));
        System.out.println("demora promedio " + Libreria.darFormato(media2, 5));
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