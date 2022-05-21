/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaSistema;

/**
 *
 * @author Lourdes Pérez
 */
public class Nodo {
    public int dato;
    public Pelicula pelicula;
    public Actor actor;
    public Nodo izquierda, derecha;
    
    

    public Nodo() {
        
    }

    public Nodo(int dato, Pelicula pelicula) {
        this.pelicula = pelicula;
        this.dato = dato;
        this.izquierda = this.derecha = null;
    }

    public Nodo(int dato, Actor actor) {
        this.actor = actor;
        this.dato = dato;
        this.izquierda = this.derecha = null;
    }
    

    public int getDato() {
        return dato;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public String imprimirDato() {
        String cadena = this.getDato() + getPelicula().peliculaN;
        System.out.println(cadena);
        return getPelicula().peliculaN;
    }
    
    public String imprimirActor() {
        String cadena = getActor().id + "*-*" + getActor().nombre;
        System.out.println(cadena);
        return cadena;
    }
    
    public Nodo getNodo(){
        return this;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }
    
    public Actor getActor() {
        return actor;
    }
    
}
