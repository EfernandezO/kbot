package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import modelo.camino;

public class MAPA {
    private char[][] matriz;
    private char[][] matrizOriginal;
    private boolean hayEntrada, haySalida, DEBUG;
    private int Centrada, Fentrada, menorMinas, menorMovimientos;
    private ArrayList<ArrayList> todosLosCaminos = new ArrayList<>();
    private ArrayList<camino> allCaminos;
     
    //constructor
    public  MAPA(char[][] matriz){
        this.DEBUG=false;
        this.matriz=matriz;
        this.matrizOriginal=matriz;
        this.hayEntrada=false;
        this.revisionInicialLaberinto('E', 'D');
        this.todosLosCaminos.clear();
        this.menorMinas=99999;
        this.menorMovimientos=99999;
    }
    
    //metodo para ver todos los caminos que tiene almacenado el arraylist
    //lo uso solo para pruebas
    public int numCaminosEncontrados(){
        if(this.DEBUG){System.out.println("N. Caminos: "+this.todosLosCaminos.size()+"");}
        return this.todosLosCaminos.size();
    }
    
    //este metodo revisa si hay caminos guardados el el arraylist
    //si los hay significa que se encontro algun camino a la meta
    // por lo tanto hay solucion.
    public boolean haySolucion(){
        boolean solucion=false;
        if(this.todosLosCaminos.size()>0){
            solucion=true;
        }
        return solucion;
    }
    
    //este metodo recibe el arraylist que tiene las rutas a la meta que que envia RESOLVER
    //y lo guarda dentro de un arraylist
    public void añadirCamino(ArrayList<String> camino) {
        //System.out.println("camino encontrado"+camino);
        todosLosCaminos.add(camino);
       
    }

    //recibe una matriz de caracteres y la imprime 
    //agrega los indices numericos de las filas y columnas
    // ademas pone en colores los distintos elementos del array
    public void ImprimirV2(char[][] matrizMapa){
       System.out.println("___________Imprimir MAPA V2______________");
       StringBuilder sb = new StringBuilder();
       String Linea; 
       String Encabezado="";
       boolean primeraVuelta=true, primeraVuelta2=true;
       String color=(char)27 + "[33;34m";
       String color2=(char)27 + "[33;39m";
       String color3=(char)27 + "[33;31m";
        for(int f=0;f<matrizMapa.length;f++) {
           
            for(int c=0;c<matrizMapa[f].length;c++) {
                Encabezado=Encabezado+c+" ";
                if(primeraVuelta2){
                    primeraVuelta2=false;
                    sb.append(color).append(f).append(color3).append(' ');
                }
                if(matrizMapa[f][c]=='?'||matrizMapa[f][c]=='.'){
                     sb.append(color2).append(matrizMapa[f][c]).append(color3).append(' ');
                }else{
                    sb.append(matrizMapa[f][c]).append(' ');
                }  
            }
            Linea=sb.toString();
            sb.delete(0, Linea.length());
            if(primeraVuelta){
                primeraVuelta=false;
                System.out.println(color+"  "+Encabezado);
            }
                
            System.out.println(Linea);
            primeraVuelta2=true;
        }
        System.out.println("______________________________");
    
    }
    public ArrayList mejorCamino(){
        if(this.DEBUG){System.out.println("___________Mejor Camino______________");}
        this.StatusCaminos();
        ArrayList<String> caminoOptimo=null;
        int menorMinas=99999, menorMovimientos=99999, indiceCamino, numMinas, numMovimientos, indiceCaminoOptimo=-1;
       
        if(this.haySolucion()){
            
            for(camino auxCamino : this.allCaminos){
                indiceCamino=auxCamino.getPosicion();
                numMovimientos=auxCamino.getNumMovimientos();
                numMinas=auxCamino.getNumeroMinas();
                
                if(this.DEBUG){   
                    System.out.println("*indice Camino:"+indiceCamino);
                    System.out.println("*Num Minas:"+numMinas);
                    System.out.println("*Num Movimientos:"+numMovimientos);
                    System.out.println("");
                 }
                if(numMovimientos>0){


                  //busco el con menor numero de minas
                  if(numMinas<menorMinas){
                      indiceCaminoOptimo=indiceCamino;
                      menorMinas=numMinas;
                      menorMovimientos=numMovimientos;

                  }
                  else{
                      //si hay otro con igual numero de minas, pero con menos movimientos lo guardo
                      if((numMinas==menorMinas)&&(numMovimientos<menorMovimientos)){
                          indiceCaminoOptimo=indiceCamino;
                          menorMinas=numMinas;
                          menorMovimientos=numMovimientos;
                       }
                  }
                 }
                 else{
                     if(this.DEBUG){System.out.println("Camino no Valido");}
                 }
             }//fin foreach
            if(this.DEBUG){
                 System.out.println("*_*");
                 System.out.println("El camino Optimo es: "+indiceCaminoOptimo);
                 System.out.println("N. movimientos: "+menorMovimientos);
                 System.out.println("N. Minas"+menorMinas);
                 System.out.println("*-*");
             }
             caminoOptimo=this.todosLosCaminos.get(indiceCaminoOptimo);
             if(this.DEBUG){System.out.println("Camino Optimo:"+caminoOptimo);}
            }//fin si hay solucion
            
        if(this.DEBUG){System.out.println("______________________________");}
        return caminoOptimo; 
    }
    
    
    private void StatusCaminos(){
        this.allCaminos= new ArrayList<>();
       
        int indiceCamino=0;
        if(this.DEBUG){ System.out.println("______________MEJOR CAMINO________________");}
       
        if(this.haySolucion()){
            Iterator<ArrayList> auxCamino = this.todosLosCaminos.iterator();
            while( auxCamino.hasNext() ){
                if(this.DEBUG){System.out.println("");System.out.println("Camino "+indiceCamino);}
               Iterator<String> auxContenido =auxCamino.next().iterator();
                int numMovimientos = 0;
                int numMinas = 0;
                
               while(auxContenido.hasNext()){
                    String elemento = auxContenido.next();
                    String[] auxArray=elemento.split(":");
                    
                    if(this.DEBUG){
                        System.out.print("X. "+auxArray[0]);
                        System.out.print(" Y. "+auxArray[1]);
                        System.out.print(" D. "+auxArray[2]);
                        System.out.println(" S. "+auxArray[3]);
                    }
                    numMovimientos++;
                    if(String.valueOf('M').equals(auxArray[3])){
                        numMinas++;
                    }
               }
               numMovimientos--;
               
               camino caminitoAux=new camino();
               caminitoAux.setNumMovimientos(numMovimientos);
               caminitoAux.setNumeroMinas(numMinas);
               caminitoAux.setPosicion(indiceCamino);
               caminitoAux.setRuta(this.todosLosCaminos.get(indiceCamino));
               this.allCaminos.add(caminitoAux);
  
               indiceCamino++;
               if(this.DEBUG){
                    System.out.println("Movimientos: "+numMovimientos);
                    System.out.println("Minas: "+numMinas);
               }
            }
        }
     
        if(this.DEBUG){ System.out.println("__________________________________________");}
        
    }
   
   public String buscarDocumento(){
       String msjSalida="";
       boolean s;
       long tiempoInicio, tiempoFin, tiempoEjecucion, tiempoEjecucionSeg=0;
        ArrayList<String> caminoX = new ArrayList<>();
        if((this.hayEntrada)&&(this.haySalida)){
            if(this.DEBUG){ System.out.println("Mapa Valido, intenta resolver");}
            
            tiempoInicio = System.nanoTime();
            s=this.Resolver(this.matriz, this.getFentrada(), this.getCentrada(), " ", caminoX, false, 1, 0);
            tiempoFin=System.nanoTime();
            tiempoEjecucion=tiempoFin-tiempoInicio;
            tiempoEjecucionSeg=(tiempoEjecucion/1000000000);
            
            if(this.haySolucion()){
                if(this.DEBUG){ System.out.println("Solucion Encontrada...");}
                msjSalida=this.mejorCamino().toString().replace(",", "\n"); 
            }
            else{msjSalida="No hay solucion...\n";}
            msjSalida=msjSalida.concat("\nTiempo Ejecucion: "+Long.toString(tiempoEjecucionSeg)+" Seg\n");

        }else{msjSalida="Mapa Invalido\n";}
       
       msjSalida=msjSalida.concat("Numero caminos Encontrados: "+this.numCaminosEncontrados());
       return(msjSalida);
   } 
    
   public boolean Resolver(char[][]matrizMapa, int i, int j, String  direccion, ArrayList<String> camino, boolean  noPisarMinas, int profundidad, int minas){
      boolean r=false, seguirBuscando=true, restringir=true;
      int t, C, F;
      
      C=this.getNumColumnas();
      F=this.getNumFilas();
      char situacionActual;
      String movimiento;
      
      situacionActual=matrizMapa[i][j];
      movimiento=""+i+":"+j+":"+direccion+":"+situacionActual+"";
       
     
       if(this.DEBUG){
            System.out.println("Movimiento: "+movimiento);
            System.out.println("posicion Actual: ["+i+" "+j+"]");
       }
      
       
      if(matrizMapa[i][j]=='D'){
         camino.add(movimiento);
          this.añadirCamino((ArrayList<String>) camino.clone());
          this.setMenorMinas(minas);
          this.setMenorMovimientos(profundidad);
          t=camino.size();
          camino.remove(t-1);
          if(this.DEBUG){System.out.println("->ENCONTRADO");}
          r=false; 
      }
      else{
          
          if(matrizMapa[i][j]=='M'){
              minas++;
          }
          if(restringir){ seguirBuscando=!this.caminoEsMasComplejo(profundidad, minas);}
          
          if(seguirBuscando){
          //camino ya recorrido o pared
          if(this.posicionIntransitable(matrizMapa[i][j], noPisarMinas)){
         // if((matrizMapa[i][j]=='?')||(matrizMapa[i][j]=='.')||(matrizMapa[i][j]=='P')){
          
              if(this.DEBUG){System.out.println("->posicion intransitable");}
              r=false;
          }
          else{
              //posicion no visitada
              if(this.DEBUG){System.out.println("->posicion no visitada");}
              
              //marco camino
              //if(matrizMapa[i][j]!='E'){matrizMapa[i][j]='?';}
              
              
              matrizMapa[i][j]='?';
              //imprimir

                //derecha
              if(j+1<C && !r){
                  if(this.DEBUG){System.out.println("");System.out.println("Matriz profundidad: "+profundidad);this.ImprimirV2(matrizMapa);}
                  direccion="R";
                  if(this.DEBUG){System.out.println("-->entra a "+direccion);}
                  if(!this.posicionIntransitable(matrizMapa[i][j+1], noPisarMinas)){
                      if(this.DEBUG){System.out.println("La siguiente posicion es transitable, revisar");}
                        //System.out.println("camino llegada "+camino);
                      camino.add(movimiento);
                      //if(this.DEBUG){System.out.println("camino agregado "+camino);}
                      r=Resolver(matrizMapa, i, j+1, direccion, camino, noPisarMinas, profundidad+1, minas);
                    //imprimir
                    //if(this.DEBUG){ System.out.println("Retorno Matrix R");this.ImprimirV2(matrizMapa);}

                      if(r){if(this.DEBUG){System.out.println(direccion +"-OK");}}
                      else{if(this.DEBUG){System.out.println(direccion +"-ERROR");}}
                       t=camino.size();
                       camino.remove(t-1);
                       //if(this.DEBUG){System.out.println("camino: quitado: "+camino);}
                  }else{if(this.DEBUG){System.out.println("->La siguiente posicion es intransitable, No revisar");}}
              }
              
              
                //abajo
              if(i+1<F &&!r){
                  if(this.DEBUG){  System.out.println(""); System.out.println("Matriz profundidad: "+profundidad);this.ImprimirV2(matrizMapa);}
                   direccion="D";
                   if(this.DEBUG){System.out.println("-->entra a "+direccion);}
                   if(!this.posicionIntransitable(matrizMapa[i+1][j], noPisarMinas)){
                        if(this.DEBUG){System.out.println("La siguiente posicion es transitable, revisar");}
                        camino.add(movimiento);

                        r=Resolver(matrizMapa, i+1, j, direccion, camino, noPisarMinas, profundidad+1, minas);
                        if(this.DEBUG){
                             if(r){System.out.println(direccion +"-OK");}
                             else{System.out.println(direccion +"-ERROR");}
                        }
                        t=camino.size();
                        camino.remove(t-1);
                   }else{if(this.DEBUG){System.out.println("->La siguiente posicion es intransitable, No revisar");}}
            }
              
              
              //izquierda
              if(j-1>=0 &&!r){
                  if(this.DEBUG){  System.out.println(""); System.out.println("Matriz profundidad: "+profundidad);this.ImprimirV2(matrizMapa);}
                   direccion="L";
                   if(this.DEBUG){System.out.println("-->entra a "+direccion);}
                   if(!this.posicionIntransitable(matrizMapa[i][j-1], noPisarMinas)){
                        if(this.DEBUG){System.out.println("La siguiente posicion es transitable, revisar");}
                        camino.add(movimiento);

                        r=Resolver(matrizMapa, i, j-1, direccion, camino, noPisarMinas, profundidad+1, minas);
                        if(this.DEBUG){
                             if(r){System.out.println(direccion +"-OK");}
                             else{System.out.println(direccion +"-ERROR");}
                        }
                        t=camino.size();
                        camino.remove(t-1);
                    }else{if(this.DEBUG){System.out.println("->La siguiente posicion es intransitable, No revisar");}}
              }
              
              //arriba
              if(i-1>=0 &&!r){
                  if(this.DEBUG){  System.out.println(""); System.out.println("Matriz profundidad: "+profundidad);this.ImprimirV2(matrizMapa);}
                   direccion="U";
                   if(this.DEBUG){System.out.println("-->entra a "+direccion);}
                   if(!this.posicionIntransitable(matrizMapa[i-1][j], noPisarMinas)){
                        camino.add(movimiento);
                        if(this.DEBUG){System.out.println("La siguiente posicion es transitable, revisar");}
                        r=Resolver(matrizMapa, i-1, j, direccion, camino, noPisarMinas, profundidad+1, minas);
                        if(this.DEBUG){
                             if(r){System.out.println(direccion +"-OK");}
                             else{System.out.println(direccion +"-ERROR");}

                        }
                        t=camino.size();
                        camino.remove(t-1);
                   }else{if(this.DEBUG){System.out.println("->La siguiente posicion es intransitable, No revisar");}}
              }
             
              
              if(!r){
                  
                  /*if(matrizMapa[i][j]!='E'){
                      if(this.DEBUG){System.out.println("->devolverse");}
                      matrizMapa[i][j]='.';
                  }*/
                  if(this.DEBUG){System.out.println("->devolverse");}
                  matrizMapa[i][j]='.';
                 // matrizMapa[i][j]=this.matrizOriginal[i][j];
                  //imprimir
                  if(this.DEBUG){this.ImprimirV2(matrizMapa);}
              }//fin fi
          }//fin else
          }
          if(r){
              if(matrizMapa[i][j]!='E' && matrizMapa[i][j]!='D'){
                  //matrizMapa[i][j]='*';
              }
          }
          
      }
      matrizMapa[i][j]=situacionActual;
      return r;  
   }
   
   //------------------------------------//
  
    
 
  private void revisionInicialLaberinto(char partida, char salida){
      System.out.println("___________posicionPartida______________");
     
      this.hayEntrada=false;
      this.haySalida=false;
      
       for(int f=0;f<matriz.length;f++) {
           
            for(int c=0;c<matriz[f].length;c++) {
                
                if(!this.hayEntrada){
                    if(matriz[f][c]==partida){
                        System.out.println("Partida encontrada, posicion F: "+f+" C: "+c);
                        this.Centrada=c;
                        this.Fentrada=f;
                        this.hayEntrada=true;
                        //break;
                    }
                }
                
               if(!this.haySalida){
                    if(matriz[f][c]==salida){
                       System.out.println("Salida encontrada, posicion F: "+f+" C: "+c);
                        this.haySalida=true;
                        //break;
                    }
                }
               
               
               
            }
 
        }
       if(this.hayEntrada&&this.haySalida){System.out.println("Mapa Valido");}
       else{System.out.println("Mapa Invalido");}
     System.out.println("______________________________________________");
  }
  
  
  private boolean posicionIntransitable(char valorBuscado, boolean noPisarMinas){
      char [] arrayB = new char[4];  
      arrayB[0]='.';
      arrayB[1]='?';
      arrayB[2]='P';
      
      if(this.DEBUG){ System.out.println("=>Valor a Revisar: "+valorBuscado);}
      if(noPisarMinas){ arrayB[3]='M';}
     
      for(char s: arrayB){
            if(s==valorBuscado)
                return true;
	}
	return false;
      
  }
  
  public boolean gethayEntrada(){
      return this.hayEntrada;
  }
  
  public int getFentrada(){
      return this.Fentrada;
  }
  public int getCentrada(){
      return this.Centrada;
  }

    public void setDebbug(boolean b) {
        this.DEBUG=b;
    }
  public int getNumColumnas(){
    if(this.matrizOriginal!=null){
        int columnas = this.matrizOriginal[0].length;
        return (columnas);
    }
    else
        return 0;
  }  
  
  public int getNumFilas(){
      if(this.matrizOriginal!=null){
        int filas = this.matrizOriginal.length;
        return (filas);
    }
    else
        return 0;
  }

    private boolean caminoEsMasComplejo(int numMovimientos, int numMinas) {
        boolean salida=false;
        if(this.DEBUG){System.out.println("_____________caminoEsMasComplejo ¿?________________");}
        if(this.DEBUG){System.out.println("Movimientos ["+numMovimientos+"/"+this.menorMovimientos+"]");}
        if(this.DEBUG){System.out.println("Minas ["+numMinas+"/"+this.menorMinas+"]");}
        
        //mayor numero de minas
        if(numMinas>this.menorMinas){  
                salida=true;
        }
        else{
            //igual numero de minas, mas movimientos
            if((numMinas==this.menorMinas)&&(numMovimientos>this.menorMovimientos)){
                    salida=true;
            }
        }
         if(this.DEBUG){System.out.println("Camino es mas complejo :"+salida);}
        if(this.DEBUG){System.out.println("_____________________________");}
        return salida;
    }

    public void setMenorMinas(int menorMinas) {
        this.menorMinas = menorMinas;
    }

    public void setMenorMovimientos(int menorMovimientos) {
        this.menorMovimientos = menorMovimientos;
    }
}
