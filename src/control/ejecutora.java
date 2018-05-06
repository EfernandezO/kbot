package control;
import modelo.manejoArchivo;
import modelo.MAPA;

public class ejecutora {

    
    public static void main(String[] args) {
        // TODO code application logic here
        int i=7,j=9;
        char[][] arrayMapa = new char[i][j];
        //ARRAY=lecturaArchivo(x, i);
       
       
        //inicializo objeto para lectura de archivo
        manejoArchivo ARCHIVO = new manejoArchivo();
         //llamo al metodo leerArchivo, indico la ruta del archivo a leer ademas de las dimensiones que retornará
        arrayMapa=ARCHIVO.leerArchivo("D:/mapa1.txt", i, j);
        
        MAPA mapaX = new MAPA(arrayMapa);
        mapaX.setDebbug(true);
        
        String salida=mapaX.buscarDocumento();
        
        System.out.println(""+salida);
 
        //llamo al metodo escribir archivo, indicando la ruta del archivo de salida, y un String con el texto a escribir
        ARCHIVO.escribeArchivo("D:/xxx.txt", salida);
    }
    
}
