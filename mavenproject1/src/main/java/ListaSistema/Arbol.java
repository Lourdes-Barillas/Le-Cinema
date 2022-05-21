/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaSistema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lourdes Pérez
 */
public class Arbol {
    public Nodo raiz;
    public static List<String> lista = new ArrayList<String>();
    private List<Actor> listaA = new ArrayList<Actor>();
    
    public Arbol() {

    }

    public Nodo existe(int busqueda) {
        return existe(this.raiz, busqueda);
    }

    private Nodo existe(Nodo n, int busqueda) {
        if (n == null) {
            return n;
        }
        if (n.getDato() == busqueda) {
            return n;
        } else if (busqueda < n.getDato()) {
            return existe(n.getIzquierda(), busqueda);
        } else {
            return existe(n.getDerecha(), busqueda);
        }

    }

    public void insertar(int dato, Pelicula pelicula) {
        if (this.raiz == null) {
            this.raiz = new Nodo(dato, pelicula);
        } else {
            this.insertar(this.raiz, dato, pelicula);
        }
    }

    private void insertar(Nodo padre, int dato, Pelicula pelicula) {
        if (dato > padre.getDato()) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new Nodo(dato, pelicula));
            } else {
                this.insertar(padre.getDerecha(), dato, pelicula);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new Nodo(dato, pelicula));
            } else {
                this.insertar(padre.getIzquierda(), dato, pelicula);
            }
        }
    }

    //-----------------------------------
    
    public void insertarActor(int dato, Actor actor) {
        if (this.raiz == null) {
            this.raiz = new Nodo(dato, actor);
        } else {
            this.insertarActor(this.raiz, dato, actor);
        }
    }

    private void insertarActor(Nodo padre, int dato, Actor actor) {
        if (dato > padre.getDato()) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new Nodo(dato, actor));
            } else {
                this.insertarActor(padre.getDerecha(), dato, actor);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new Nodo(dato, actor));
            } else {
                this.insertarActor(padre.getIzquierda(), dato, actor);
            }
        }
    }
    
    
    
    
    //-----------------------------------
    
    
    
    
    private void preorden(Nodo n) {
        if (n != null) {
            n.imprimirDato();
            preorden(n.getIzquierda());
            preorden(n.getDerecha());
        }
    }
    
    private void preordenA(Nodo n) {
        if (n != null) {
            n.imprimirActor();
            preordenA(n.getIzquierda());
            preordenA(n.getDerecha());
        }
    }
    
    
    
    
    /**
     * Imprime las pelis de un arbol con una lista de peliculas
     * @param n
     * @return 
     */
    public List<String> preordenS(Nodo n){
        System.out.println("Recorriendo en post-orden");
        if(n != null){
            lista.add(n.imprimirDato());
            preordenS(n.izquierda);
            preordenS(n.derecha);
        }
        return lista;
    }
    
    /**
     * Imprime los actores de un arbol en una lista de actores
     * @param n
     * @return 
     */
    public List<Actor> preordenSA(Nodo n){
        System.out.println("Recorriendo en post-orden");
        if(n != null){
            listaA.add(new Actor(n.imprimirActor()));
            preordenS(n.izquierda);
            preordenS(n.derecha);
        }
        return listaA;
    }
    
    
    
    
    private void inorden(Nodo n) {
        if (n != null) {
            inorden(n.getIzquierda());
            n.imprimirDato();
            inorden(n.getDerecha());
        }
    }

    private void postorden(Nodo n) {
        if (n != null) {
            postorden(n.getIzquierda());
            postorden(n.getDerecha());
            n.imprimirDato();
        }
    }
    
    public void preorden() {
        this.preorden(this.raiz);
    }
    
    public void preordenA() {
        this.preordenA(this.raiz);
    }
    
    public void inorden() {
        this.inorden(this.raiz);
    }

    public void postorden() {
        this.postorden(this.raiz);
    }
    
    /**
     * 
     * @param a es el Id del elemento. Ejemplo: ActorId
     */
    
    
}


 
