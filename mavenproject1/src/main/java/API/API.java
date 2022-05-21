package API;
import ListaSistema.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Lourdes Pérez
 */
public class API {
    public List<Actor> listaA = null;
    public List<String> lista = null;
    public List<String> nombresPeliculas = null;
    public List<Integer> ids = null;
    
    public static String getAPI(String clave) throws UnirestException{
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/movie/" + clave + "?api_key=1a527a0d4c1a4416c81e4664f92fb8b7")
          .asString();
        System.out.println(response.getBody());
        return response.getBody();
    }
    
    public ListaPeliculas getData(String clave) throws UnirestException{
        ListaPeliculas listaJSON = new ListaPeliculas();
        
        String jsonTexto = getAPI(clave);
        JSONObject jsonO = new JSONObject(jsonTexto);
        
        
        
        JSONArray results = jsonO.getJSONArray("results");
        
        for(int i=0; i<(results.length()-1);i++){
            
            JSONObject aux = (JSONObject) results.get(i);
            Pelicula peli =  new Pelicula(aux.getString("title"),aux.getString("original_title"), 5,aux.getString("overview"), aux.get("poster_path").toString());
            peli.id = aux.getInt("id");
            
            //Obtener actores
            try{
                int contador = 0;
                JSONArray jsonActores = aux.getJSONArray("genre_ids");
                for(int j = 0; j<(jsonActores.length()-1); j++){
                    contador++;
                    
                }
                peli.participantes = new int[contador];
                for(int j = 0; j<(jsonActores.length()-1); j++){
                    peli.participantes[j] = jsonActores.getInt(j);
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
            
            
            listaJSON.agregarAlFinal(peli);
            
            System.out.println("Pelicula obtenida: " + peli.nombreOriginal + "; Poster path " + aux.get("poster_path").toString());
            
        }
        
        return listaJSON;
    }
    
    public  Actor getActor(int id) throws UnirestException{
        Actor actor = new Actor();
        
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/person/" + id + "?api_key=1a527a0d4c1a4416c81e4664f92fb8b7&language=en-US")
          .asString();
        System.out.println(response.getBody());
        
        String jsonTexto = response.getBody();
        JSONObject jsonO = new JSONObject(jsonTexto);
        
        actor.nombre = jsonO.getString("name");
        actor.id = id;
        
        return actor;
    }
    
    
    
    
    public Arbol peliculasFromActor(String pActor, int aid) throws UnirestException{
        listaA = new ArrayList<Actor>();
        lista = new ArrayList<String>();
        nombresPeliculas = new ArrayList<String>();
        ids = new ArrayList<Integer>();
        
        
        String name[] = pActor.split(" ");
        Arbol arbol = new Arbol();
        
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/search/person?api_key=1a527a0d4c1a4416c81e4664f92fb8b7&language=en-US&query=" + name[0] + "%20" + name[1] + "&page=1&include_adult=false")
          .asString();
        System.out.println(response.getBody());
        
        String jsonTexto = response.getBody();
        JSONObject jsonO = new JSONObject(jsonTexto);
        
        //Obtenemos el nombre de la pelicula
        
        ListaPeliculas listaJSON = new ListaPeliculas();
        
        JSONArray results = jsonO.getJSONArray("results");
        System.out.println(jsonO.get("page"));
        System.out.println(results.length());
        
        JSONObject aux = (JSONObject) results.get(0);
        System.out.println("gender " + aux.getInt("gender"));
        JSONArray known_for = aux.getJSONArray("known_for");
        System.out.println(known_for.length());
        
        
        for(int i=0; i<(known_for.length());i++){
            JSONObject pelicula = (JSONObject) known_for.get(i);
            Pelicula peli = new Pelicula(pelicula.getString("title"));
            peli.sinopsis = pelicula.get("overview").toString();
            peli.ubicacionPortada = pelicula.get("poster_path").toString();
            ids.add(pelicula.getInt("id"));
            arbol.insertar(aid, peli);
            this.nombresPeliculas.add(peli.peliculaN);
            this.lista.add(peli.ubicacionPortada);
        }
        return arbol;
    }
    
    
    
    //Getting their info :3
    public Arbol getActorsFMovie(int idMovie) throws UnirestException{
        Actor actor = null;
        Arbol arbol = new Arbol();
        Pelicula pelicula = new Pelicula();
        
        pelicula.id = idMovie;
        
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/movie/" + idMovie + "/credits?api_key=1a527a0d4c1a4416c81e4664f92fb8b7&language=en-US")
          .asString();
        System.out.println(response.getBody());
        
        String jsonTexto = response.getBody();
        JSONObject jsonO = new JSONObject(jsonTexto);
        
        JSONArray cast = jsonO.getJSONArray("cast");
        
        JSONObject aux = (JSONObject) cast.get(0);
        
        actor = new Actor(aux.getInt("id"), aux.getString("name"));
        System.out.println("Actor " + actor.toString());
        
        for(int i=0; i<(cast.length());i++){
            JSONObject actorFound = (JSONObject) cast.get(i);
            Actor a = new Actor(actorFound.getInt("id"), actorFound.getString("name"));
            a.profilepicture = actorFound.get("profile_path").toString();
            arbol.insertarActor(a.id, a);
            pelicula.cast.add(a);
        }
        listaA = pelicula.cast;
        arbol.preordenA();
        return arbol;
    }
    
    
    
    
    public Pelicula getPelicula(int id) throws UnirestException{
        Pelicula pelicula = new Pelicula();
        HttpResponse<String> response;
        
        
        
        Unirest.setTimeouts(0, 0);
            response = Unirest.get("https://api.themoviedb.org/3/movie/" + id + "?api_key=1a527a0d4c1a4416c81e4664f92fb8b7&language=en-US")
              .asString();
            System.out.println(response.getBody());
        
        String jsonTexto = response.getBody();
        JSONObject jsonO = new JSONObject(jsonTexto);
        
        pelicula.peliculaN = jsonO.getString("original_title");
        pelicula.nombreOriginal = pelicula.peliculaN;
        
        pelicula.sinopsis = jsonO.get("overview").toString();
        pelicula.ubicacionPortada = jsonO.getString("poster_path");
        System.out.println("Pelicula buscada " + pelicula.peliculaN);
        return pelicula;
    }
    
    
    
}
