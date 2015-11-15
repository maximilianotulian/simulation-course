package espera;

import generadores.Libreria;

public class Arribo {
    public static void funcion(Espera e){
        if (e.getS() == 'O') {
            double aux = e.getAreaBajoQ() + (e.getReloj() -e.getTUE()) * e.getN();
            e.setAreaBajoQ(aux);
            int num = e.getN() + 1; 
            e.setN(num);
            Espera.Vta aux2 = new Espera.Vta(e.getReloj(), true);
            e.getVTA().add(aux2);
            //System.out.println("Arribo True");
        }
        else{
           // System.out.println("Arribo False");
            e.setS('O');
            e.getDemora().add(0.0);
            e.setTIOS(e.getReloj());//tiempo en que empieza a estar ocupado el servidor
            int aux = e.getCli_at() + 1;
            e.setCli_at(aux);
            double tiempoServicio = Libreria.distExp(e.getMediaPartida());
            //revisar el tiempo de servicio si es para una partida o para un arribo
            
            Espera.Lev lev = new Espera.Lev(Partida.C(),tiempoServicio+ e.getReloj(), true);
            e.getDemoraSis().add(tiempoServicio);
            e.getLEV().add(lev);
        }
        //revisar para ver si era arribo o partida
        Espera.Lev lev = new Espera.Lev(Arribo.C(), (Libreria.distExp(e.getMediaArribo()) + e.getReloj()), true);
        e.getLEV().add(lev);
        e.setTUE(e.getReloj());
    }
    public static char C(){
        return 'A';
    }
}
