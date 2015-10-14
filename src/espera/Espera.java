package espera;

import java.math.BigInteger;
import java.util.ArrayList;

public class Espera {
//Variables
private char S; //D desocupado O ocupado
private double TUE;//tiempo ultimo evento
private long TFS; //tiempo final de simulacion
private double t; // tiempo
private ArrayList<Vta> VTA; //vector de tiempo de arribo
private int n;//tamaño de la cola de clientes sin atender
private int cli_at;//numero de clientes atendidos
private double demoraTotal;
private ArrayList<Double> demora;//demora en cola
private ArrayList<Double> demoraSis;//demora en sistema
private double areaBajoQ;//acumulado de las areas de la cola
/* Se calcula como base por altura sobre 2 donde base = tiempo n que estuvo un valor constante
 * y altura = tama�o de la cola n. Cada vez que n cambie un valor o un cliente entra al servidor y se va de la cola, se calcula el area antes
 * de actualizar n a su nuevo valor. La base se calcula entre el tiempo(ahora) y la �ltima vez que n cambi� de valor TUE*/
private double areaBajoB;//acumulado de la utilizacion del servidor
private double reloj;
private ArrayList<Lev> LEV;
double TIOS;
double mediaArribo; 
double mediaPartida; 

public Espera() {
	reloj = 0;
	areaBajoQ = 0;
	areaBajoB = 0;
	cli_at = 0;
	n= 0;
	TUE = 0; 
        S = 'D'; //Desocupado D ocupado O
        demoraTotal = 0;
        VTA = new ArrayList<>();
        t= 0;
        LEV = new ArrayList<>();
        TIOS = -1;
        demora = new ArrayList<>();
        demoraSis = new ArrayList<>();
        }

public void setDemoraSis(ArrayList<Double> demoraSis) {
        this.demoraSis= demoraSis;
    }
public ArrayList<Double> getDemoraSis() {
        return demoraSis;
    }
public void setDemora(ArrayList<Double> demora) {
        this.demora = demora;
    }
public ArrayList<Double> getDemora() {
        return demora;
    }
public static class Lev{
char C;
double hora;
boolean usado;
        public void setUsado(boolean usado) {
            this.usado = usado;
        }
        public boolean isUsado() {
            return usado;
        }    
        public void setHora(double hora) {
            this.hora = hora;
        }
        public double getHora() {
            return hora;
        }
        public void setC(char C) {
            this.C = C;
        }
        public char getC() {
            return C;
        }
        public Lev(char C, double hora,boolean usado) {
            this.C = C;
            this.hora = hora;
            this.usado = usado;
        }
  
}
public static class Vta{
double hora;
boolean usado;
        public Vta(double hora, boolean usado){
            this.hora = hora;
            this.usado = usado;
        }
        public void setUsado(boolean usado) {
            this.usado = usado;
        }

        public boolean isUsado() {
            return usado;
        }

        public void setHora(double hora) {
            this.hora = hora;
        }

        public double getHora() {
            return hora;
        }

}
public void setTIOS(double TIOS) {
        this.TIOS = TIOS;
    }
public double getTIOS() {
        return TIOS;
    }
public void setLEV(ArrayList<Lev> LEV) {
        this.LEV = LEV;
    }
public ArrayList<Lev> getLEV() {
        return LEV;
    }
public ArrayList<Vta> getVTA() {
        return VTA;
    }
public void setVTA(ArrayList<Vta> VTA) {
        this.VTA = VTA;
    }
public char getS() {
        return S;
    }
public void setS(char S) {
        this.S = S;
    }
public double getTUE() {
	return TUE;
}
public void setTUE(double tUE) {
	TUE = tUE;
}
public long getTFS() {
	return TFS;
}
public void setTFS(long tfs) {
	TFS = tfs;
}
public double getT() {
	return t;
}
public void setT(double t) {
	this.t = t;
}
public int getN() {
	return n;
}
public void setN(int n) {
	this.n = n;
}
public double getDemoraTotal() {
	return demoraTotal;
}
public void setDemoraTotal(double demoraTotal) {
	this.demoraTotal = demoraTotal;
}
public double getAreaBajoQ() {
	return areaBajoQ;
}
public void setAreaBajoQ(double areaBajoQ) {
	this.areaBajoQ = areaBajoQ;
}
public double getAreaBajoB() {
	return areaBajoB;
}
public void setAreaBajoB(double areaBajoB) {
	this.areaBajoB = areaBajoB;
}
public double getReloj() {
	return reloj;
}
public void setReloj(double reloj) {
	this.reloj = reloj;
}
public int getCli_at() {
	return cli_at;
}
public void setCli_at(int cli_at){
	this.cli_at = cli_at;
}
public double getMediaArribo() {
        return mediaArribo;
    }
public double getMediaPartida() {
        return mediaPartida;
    }
public void setMediaArribo(double mediaArribo) {
        this.mediaArribo = mediaArribo;
    }
public void setMediaPartida(double mediaPartida) {
        this.mediaPartida = mediaPartida;
    }
   
}
