package espera;

import espera.Espera.Vta;
import generadores.Libreria;
import java.util.ArrayList;

public class Partida {
    public static void funcion(Espera e)
    {     
        if (e.getN() == 0) {
            e.setS('D');            
            double aux = e.getAreaBajoB() + (e.getReloj() - e.getTIOS());
            e.setAreaBajoB(aux);
            Espera.Lev lev = new Espera.Lev(Partida.C(), -1, false);
            e.getLEV().add(lev);
        }
        else{   
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
