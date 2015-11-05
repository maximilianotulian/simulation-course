package espera;

import espera.Espera.Vta;
import generadores.Libreria;
import java.util.ArrayList;


public class Reporte {
/* Calcular los parametros
 * Valor esperado de la demora de un cliente en cola
 * d(n)=sumatoria de Di/n
 * Valor esperado del numero promedio de clientes en cola
 * q(n)=Q(t)/T(n)= Numero de clientes en cola en el tiempo t/Tiempo requerido para observar las n demoras
 * Valor esperado de la utilización del servidor
 * u(n)=B(t)/T(n) B(t)= 1 si el servidor esta ocupado en el tiempo t
 * 0 si el servidor esta desocupado en el tiempo t
 *Mostrar los resultados */
public static double[] funcion(Espera e){
    double[] aux = new double[3];
    
    double q = e.getAreaBajoQ() / e.getT();
    //System.out.println("Número medio en cola: " + Libreria.darFormato(q, 3) +" areaBajoQ: "+Libreria.darFormato(e.getAreaBajoQ(), 3));//+"Tiempo final: "+e.getT()
    double b = e.getAreaBajoB() / e.getT();
    //System.out.println("Utilización del servidor: " + Libreria.darFormato(b, 3) + " areaBajoB: "+ Libreria.darFormato(e.getAreaBajoB(),3) + " Tiempo final: " + Libreria.darFormato(e.getT(),3));//
    double d = e.getDemoraTotal() / e.getCli_at();
    //System.out.println("Demora promedio: " + Libreria.darFormato(d, 3) + " Demora Total: " + Libreria.darFormato(e.getDemoraTotal(),3)+ " Clientes atendidos: "+ e.getCli_at());//
    
    aux[0] = q; //número medio en cola
    aux[1] = b; //utilización del servidor
    aux[2] = d; //demora promedio
    
    muestraSeparador();
    System.out.println("           Parametros utilizados");
    System.out.println("Media arribo: " + e.getMediaArribo());
    System.out.println("Media partida: " + e.getMediaPartida());
    System.out.println("Tiempo final de simulación: " + e.getTFS());
    muestraSeparador();
    System.out.println("           Medidas de rendimiento");
    muestra(q,b,d);
    muestraSeparador();
    System.out.println("           Valores para graficar");
    graficarDemora(e.getDemoraSis(),0);
    graficarDemora(e.getDemora(),1);
    muestraSeparador();

    return aux;
}
public static void muestraSeparador() {
    System.out.println("------------------------------------------");
}
public static void muestra (double media0, double media1, double media2){
    
    System.out.println("Número medio en cola: " + Libreria.darFormato(media0, 5));
    System.out.println("Utilización del servidor: " + Libreria.darFormato(media1, 5));
    System.out.println("Demora promedio: " + Libreria.darFormato(media2, 5));
}
public static void tiempoEA(Espera e){
    ArrayList<Vta> aux = e.getVTA();
    ArrayList<Double> tiempos = new ArrayList<>();
    tiempos.add(aux.get(0).getHora());
    for (int i = 1; i < aux.size(); i++) {
        double help = aux.get(i).hora - aux.get(i-1).hora;
        tiempos.add(help);
    }
}
public static void graficarDemora(ArrayList demora, int typo) {
    String aux = "";
    
    switch(typo){
        case 0: aux += "Demora en sistema \n {";
            break;
        case 1: aux += "Demora en cola \n {";
            break;
    }
    for(int i = 0; i < demora.size(); i += 1){
        if(i%500 == 0 && i != 0){
            aux += "\n";
        }
        
        aux += Libreria.darFormato((double) demora.get(i),2) + ", ";
    }
    aux += "}";
    
    System.out.println(aux);
}
}
