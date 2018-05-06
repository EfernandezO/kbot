package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class manejoArchivo {
    
  private Object array1;
    
    public void escribeArchivo(String rutaArchivoSalida, String datos){
        //Un texto cualquiera guardado en una variable
        Date date = new Date();
        DateFormat fechaHoraActual = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        System.out.println("Escribiendo Archivo de Salida:"+ rutaArchivoSalida);
        try
        {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivoSalida=new File(rutaArchivoSalida);

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter escribir=new FileWriter(archivoSalida,true);

            //Escribimos en el archivo con el metodo write
            escribir.write("\n================*****=================\n");
            escribir.write("inicio registro: "+fechaHoraActual.format(date)+"\n");
                escribir.write(datos+"\n");
            escribir.write("\n================*****=================\n");
                    
            
            //Cerramos la conexion
            escribir.close();
        }
        //Si existe un problema al escribir cae aqui
        catch(Exception e)
        {
            System.out.println("Error al escribir");
        }
    }
    
    public char[][] leerArchivo(String rutaArchivo, int x, int i){
        System.out.println("leer Archivo:"+rutaArchivo);
        String texto;
        char[][] matriz = new char[x][i];
        char[] aCaracteres;
        int numLinea=0;
        try
        {
            //Creamos un archivo FileReader que obtiene lo que tenga el archivo
            FileReader lector=new FileReader(rutaArchivo);

            //El contenido de lector se guarda en un BufferedReader
            BufferedReader contenido=new BufferedReader(lector);

            //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
            while((texto=contenido.readLine())!=null)
            {
                aCaracteres=texto.toCharArray();
                System.out.println("Linea: "+numLinea+" "+texto);
                matriz[numLinea]=aCaracteres;
                numLinea++;
            }
            
            contenido.close();
        }
        //Si se causa un error al leer cae aqui
        catch(Exception e)
        {
            System.out.println("Error al leer: "+e);
        }
        
        return matriz;
    }
}
