#Kbot
#eliasfernandezo@gmail.com
#COD:9e545c0d3febf377436f2e4e365ebc3f
#06/05/2018

//proyecto java

Descripcion General
Para la busqueda del camino mas optimo para recorrer un laberinto, usando backtraking.

Funcionamiento
-leer un archivo txt (mapa2.txt) y agrega su contenido como un array bidimencional
-verifica que sea un laberinto valido.
-recorre esta matriz recursivamente siguido algunas reglas,
 hasta encontrar todos los caminos a la meta.
-si se encuentra la meta la ruta hasta esta se guarda.
finalmente si existe caminos a la meta se elige el mas conveniente en base a restricciones.
-se genera un archvivo de salida (xxx.txt) donde se guarda la ruta mas conveniente en caso de existir.

reglas:
	-las casillas 'P' son intransitables.
	-la-s casilla-s 'D' son la meta.
	-la casilla 'E' es la entrada.
	-la-s casilla-s 'M' son minas.
	
camino mas conveniente:	
	aquel que tiene menos cantidad minas en su ruta y menor cantidad de movimientos.
	