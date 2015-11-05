package espera;
import espera.Espera.Lev;
import generadores.Libreria;
public class Inicializacion {

	public static void Inicia(Espera modelo, long TFS, double arribo, double partida){
                
		//creamos el primer arribo y lo guardamos en LEV
                //Nuestro Lev esta formado por dos colecciones una de tipos de eventos
                //La otra esta formado por hora que seria la hora del tipo de evento
                modelo.setMediaArribo(arribo);
                modelo.setMediaPartida(partida);
                
                //primer arribo
                Lev a = new Lev(Arribo.C(), Libreria.distExp(modelo.getMediaArribo()), true);
                modelo.getLEV().add(a);
                
                //creamos la primer partida y la guardamos en LEV
                //Seteamos el tiemp -1 para indicar que no puede ocurrir
                a = new Lev(Partida.C(), -1,false);
                modelo.getLEV().add(a);
                
                //setiamos el tiempo final de simulación
                modelo.setTFS(TFS);
	}
}
