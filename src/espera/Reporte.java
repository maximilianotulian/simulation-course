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
    
    /*for (int i = 0; i < e.getDemora().size(); i++) {
        System.out.println("Demora individual: "+ e.getDemora().get(i));
        System.out.println("Demora en sistema: "+ e.getDemoraSis().get(i)); 
    }*/
    return aux;
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
}
