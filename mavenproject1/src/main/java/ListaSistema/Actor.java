/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaSistema;

/**
 *
 * @author Lourdes Pérez
 */
public class Actor {
    public String nombre;
    public int id;
    public Arbol pelis;//peliculas en las que aparece
    public String profilepicture;
    
    public Actor(){
        id = 0;
        nombre = null;
        pelis = null;
        profilepicture = null;
    }
    
    public Actor(int id){
        this.id = id;
        nombre = null;
        pelis = null;
    }
    
    public Actor(String nombre){
        id = 0;
        this.nombre = nombre;
    }

    public Actor(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return this.id + " " + this.nombre; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
