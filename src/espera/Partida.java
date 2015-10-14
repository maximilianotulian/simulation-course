package espera;

import espera.Espera.Vta;
import generadores.Libreria;
import java.util.ArrayList;

public class Partida {
/*
 * if(cola=vacia)
 * 	Establecer servidor como desocupado
 * 	Eliminar el evento partida de consideraci�n
 * else
 * 	Computar demora del cliente que entra y acumular estad�sticos
 * 	Sustraer 1 del n�mero de clientes en cola
 * 	Agregar 1 al n�mero de clientes que completaron su demora
 * 	Planificar un evento partida para �ste cliente
 * 	Mover cada cliente un lugar
 *      6,85
 * Volver
 * 	*/
    public static void funcion(Espera e)
    {     
        if (e.getN() == 0) {
            //System.out.println("Partida true");
            e.setS('D');            
            double aux = e.getAreaBajoB() + (e.getReloj() - e.getTIOS());
            e.setAreaBajoB(aux);
            Espera.Lev lev = new Espera.Lev(Partida.C(), -1, false);
            e.getLEV().add(lev);
        }
        else{   
            //System.out.println("Partida false");
            double aux = e.getAreaBajoQ() + ((e.getReloj() - e.getTUE()) * e.getN());
            e.setAreaBajoQ(aux);
            int ayuda = e.getN() - 1;
            e.setN(ayuda);
            double hora = 0;
            ArrayList<Vta> auxiliar = e.getVTA();
            for (int i = 0; i < e.getVTA().size(); i++) {
                if (auxiliar.get(i).isUsado()) {
                    hora = auxiliar.get(i).getHora();
                    auxiliar.get(i).setUsado(false);
                    i=e.getVTA().size();
                }
            }
            aux = e.getDemoraTotal()+(e.getReloj() - hora);
            e.setDemoraTotal(aux);
            e.setCli_at(e.getCli_at()+1);
            double tiempoServicio = Libreria.distExp(e.getMediaPartida());
            Espera.Lev lev = new Espera.Lev(Partida.C(),tiempoServicio + e.getReloj(), true);
            e.getDemora().add(e.getReloj() - hora );//demora individual en cola
            e.getDemoraSis().add(e.getReloj() - hora + tiempoServicio );//demora en sistema
            e.getLEV().add(lev);
            }
        e.setTUE(e.getReloj());
    }
    public static char C(){
        return 'P';
    }
}
