% random(nombre de distribuci�n,parametros)
%en el caso de la exponencial es random('exp',media,cantidad de dimensiones,cantidad de elementos por dimension) 
%en el ejemplo estamos generando numeros aleatorios con distribuci�n exponencial, media = 5, 1 fila y 100 elementos
R = random('exp',5,1,100)
media = mean(R);
varianza = var(R);