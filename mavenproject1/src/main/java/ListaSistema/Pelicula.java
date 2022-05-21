package ListaSistema;

import java.util.ArrayList;
import java.util.List;


public class Pelicula {

    public Pelicula siguiente;
    public Pelicula anterior;
    public String peliculaN;
    public String nombreOriginal;
    public int resenia;
    public String sinopsis;
    public String ubicacionPortada;
    public int[] participantes;
    public List<Actor> cast;
    public int id;
    
    public Pelicula(){
        siguiente = null;
        peliculaN = null;
        nombreOriginal = "";
        resenia = 0;
        sinopsis = "";
        ubicacionPortada = "";
        id = 0;
        cast = new ArrayList<Actor>();
    }
    
    public Pelicula(String pN) {
    	peliculaN = pN;
    }
    
    public Pelicula(String name, String pname, int resenia, String sin, String ub){
        peliculaN = name;
        nombreOriginal = pname;
        this.resenia = resenia;
        sinopsis = sin;
        ubicacionPortada = ub;
    }
}
