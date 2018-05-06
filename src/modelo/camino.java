package modelo;

import java.util.ArrayList;

public class camino {
    private int numeroMinas;
    private int numMovimientos;
    private ArrayList<String> ruta;
    private int posicion;

    public camino() {
        this.ruta = new ArrayList<>();
        this.numMovimientos=0;
        this.numeroMinas=0;
        this.posicion=0;
    }

    public int getNumeroMinas() {
        return numeroMinas;
    }

    public void setNumeroMinas(int numeroMinas) {
        this.numeroMinas = numeroMinas;
    }

    public int getNumMovimientos() {
        return numMovimientos;
    }

    public void setNumMovimientos(int numMovimientos) {
        this.numMovimientos = numMovimientos;
    }

    public ArrayList<String> getRuta() {
        return ruta;
    }

    public void setRuta(ArrayList<String> ruta) {
        this.ruta = ruta;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    
}
