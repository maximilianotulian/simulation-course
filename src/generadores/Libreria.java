package generadores;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Random;

public class Libreria {
/*Generar variaciones aleatorias
 * Generadores de n�meros aleatorios
 * Recordar que siempre que se calcula la media es conveniente saber cual es la varianza
 * Puesto que F(x) se define sobre el rango 0 a 1, podemos generar n�meros aleatorios distribuidos uniformemente y adem�s hacer
 * que F(x) = r. Resulta claro, entonces, c�mo queda x determinada un�vocamnte por r= F(x). Por lo tanto, para cualquier valor de r,
 * que generemos es posible encontrar el valor de x. Si tomamos a ro, es posible encontrar el valor de xo debido a la funci�n inversa de F
 * Esto es conocido como xo=F^-1(ro) Gr�fica de la p�gina 88 del cap 4 de t�cnicas de simulaci�n
 * Donde F^-1(ro) es la transformaci�n inversa o mapeo de r sobre el intervalo unitario en el dominio de x.
 * r=F(x)= integral entre - infinito y x f(t) dt
 * 
 * Ejemplo f(x)=2x, 0 <= x <= 1
 * r=F(x)=x^2 (integracion de f(x))
 * x=F^-1(r)=Raiz(r), 0<= r <= 1
 */

//Metodos de clase
public static String darFormato(double numero,int longitud){
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(longitud);
	nf.setRoundingMode(RoundingMode.DOWN);
	return nf.format(numero);
}
//Metodos de Instancia privados

public static double Uniforme() {
	//Nos devuelve un valor uniforme con dos digitos decimales
	Random rnd= new Random();
	double aux = rnd.nextDouble();
	//darFormato(aux); nos muestra el n�mero generado con el formato de 4 d�gitos nomas
	return aux;
}/*
public static double Uniforme() {
	//Nos devuelve un valor uniforme con dos digitos decimales
Random rnd = new Random(3816L);
return rnd.nextDouble();
}*/
private double UniformeRango(int rango,int inicio){
	Random rnd = new Random();
	//System.out.println("N�mero aleatorio entre["+inicio+":"+(rango+inicio)+"] : "+(rnd.nextDouble()*rango+inicio));
	return rnd.nextDouble()*rango+inicio;
	}

//Metodos de Instancia publicos
public double distUniforme(int a,int b) {
	/*f(x) = 1/b-a
	 * 	   = 0 si esta fuera del rango(a,b)
	 * b y a son los l�mites superior y inferior
	 * F(X)= integral de f(x) = x-a/b-a
	 * E(X)= integral de f(x) * x = b+a/2 = u
	 * V(X)= integral de f(x) * (x-u)^2 = Integral de (x-E(x))^2 * 1/b-a = (b-a)^2 / 12
	 * x = r(b-a)+a 0<= r <= 1
	 * 
	 * Si no se conocen los valores de a y b
	 * a= E(x) - Raiz(3V(x))
	 * b= 2E(x) - a
	 */
	double x = a +(b-a) * Uniforme(); 
	return x;
}
public static double distExp(double media){
/*
 * Si es muy peque�a la probabilidad de que ocurra un evento en un intervalo corto,
 * si la ocurrencia de tal evento es, estad�sticamente independiente respecto a la ocurrencia de otros eventos,
 * entonces el invervalo de tiempo entre ocurrencias de eventos de este tipo estar� distribuido en forma exponencial.
 * Las supociones que debe satisfacer una variable para ser exponencial son:
 * 1_La probabilidad de que ocurra un evento en el intervalo[t,t+At] es alfa*A*t
 * 2_Alfa es una constante que no depende de t o de alg�n otro factor
 * 3_La probabilidad de que durante un intervalo[t,t+At] ocurra m�s de evento, tiende a 0 a medida que At-> 0, y su orden de magnitud
 * deber� ser menor que el de alfa*A*t
 * Curiosamente el comportamiento de un considerable n�mero de procesos dependientes del tiempo satisfacen las anteriores
 * supocisiones un tanto fuertes.
 * f(x)=alfa*e^(-alfa*x)
 * F(x)=1-e^(-alfa*x)
 * E(X)=1/alfa
 * V(X)=1/alfa^2
 * alfa=1/E(x)
 * 1-F(x) = r = e^(-alfa*x)
 * x= -E(x) log r
 */
	double x = -(media * Math.log(Uniforme()));
	return x;
}
public double distNormal(double media,double var,double kValores){
	/*
	 * Muy a menudo las distribuciones se aproximan a la normal, esta misma basa su utilidad en el teorema del l�mite central
	 * Este teorema postula que, la distribuci�n de probabilidad de la suma de N valores de la variable aleatorio xi independientes
	 * pero id�nticamente distribuidos, con medias respectivas u1 y varianza vi^2 se aproxima asint�ticamente a una distribuci�n normal,
	 * a medida que N se hace muy grande, y que dicha distribuci�n tiene como media y varianza respectivamente, a
	 * u = sum(ui)
	 * v = sum(vi)
	 * Adem�s sirve para aproximar la distribuci�n binomial y de Poisson, entre otras.
	 * f(x) = formula jodida
	 * Si los par�metros de la distribuci�n normal tienen los valores ux= 0 y vx=1, la funci�n de distribuci�n recibe el nombre de distribucion normal estandar
	 *Cualquier distribuci�n normal se puede convertir a la forma est�ndar, mediante la siguiente substituci�n 
	 *z = x- ux/vx 
	 *Distribuci�n no estandar
	 *E(x) = ux
	 *V(x) = v^2
	 *Para generar valores normales utilizando computadoras se requiere el uso de la suma de k valores de la variable aleatoria distribuidos uniformemente
	 */
	double k = 0;
	double x=0;
	for (int i = 0; i < kValores; i++) {
		k = k + Uniforme();
	}
	x = ( var * Math.pow((12/kValores), 0.5) *(k-(kValores/2)) )+media;
	return x;
}
public void distGamma(){
	/*
	 * no es otra cosa que la suma de k variables exponenciales. Si k se adec�a a la distribuci�n binomial negativa o a la distribuci�n
	 * geometrica, entonces la suma de k valores de la variable aleatoria,con la misma alfa, adoptar� la forma de una distribuci�n gamma
	 * Sin desarrollar
	 */
}
}
