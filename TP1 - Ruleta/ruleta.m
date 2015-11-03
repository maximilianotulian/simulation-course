clear;
%Declaraci�n de variables

elec = fix(37*rand(1));%n�mero elegido para la muestra

fr=0;%Frecuencia relativa de todas las muestras
m=0;%Medias de todas las muestras
v=0;%Varianza de todas las muestras

k=1; %Se usa para determinar en que r�plica estamos
frUltima=0;
mUltima=0;
vUltima=0;

while k<4
    j=1;%Nos sirve para cada una de las muestras
    indice=j*2000;
    while indice<1000001 %Quiere decr que vamos atener 1 millon de de observaciones por r�plica
        b=fix(37*rand(1,indice)); 
        %b es el arreglo que contiene los n�meros aleatorios generados por el primer indice
        %indica la cantidad de dimensiones y el segundo indica cuantos elementos hay por  cada dimensi�n
        n=size(b);%n es el tama�o de la muestra
        a=elec;
        er=find(b==a);%Este es el encargado de buscar la cantidad de n�mero repetidos
        repetidos=lenght(er);%Con esto se termina el proceso de contar repetidos
        %Calculamos las variables de cada muestra
        fr(j,k)=repetidos/max(size(b));%Calculamos la frecuencia relativa
        tam=max(size(b));
        %Guardamos los valores en las variables generales
        v(j,k)=fix(bar(b));
        m(j,j)=fix(median(b));
        j=j+1;
        indice = j*10000;
    end
%Mostrar los resultados de la �ltima observaci�n
fprintf('Replica n�imero:%d\n',k);
fprintf('El tama�o de la muestra es de%d\n',tam);
fprintf('La cantidad de observaciones es de %d\n',j-1);
fprintf('La media es:%i\n',m(j-1,k));
fprintf('La varianza es:%i\n',v(j-1,k));
fprintf('La frecuencia relativa del n�mero %d es:%f\n',elec,fr(j-1,k));
k=k+1;
end

frUltima=median(fr);
mUltima=median(m);
vUltima=median(v);
