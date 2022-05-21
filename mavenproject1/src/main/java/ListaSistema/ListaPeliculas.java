package ListaSistema;


public class ListaPeliculas{
    public Pelicula inicio;
    public Pelicula ultimo;
    public int tamanio;
    public ListaPeliculas lista;
    
    public ListaPeliculas()
    {
        inicio = null;
        ultimo = null;
        tamanio = 0;
    }

    public boolean esVacia() {
    	return inicio == null;
    }
    
    
    /*
     *@pN = nombre
     *@nO = nombre original
     *@r = resenia
     *@s = sinopsis
     *@u = ubicacion de la imagen
     */
    public void agregarAlFinal(String pN, String nO, int r, String s, String u, int id) {
    	Pelicula nueva = new Pelicula();
    	nueva.peliculaN = pN;
    	nueva.nombreOriginal = nO;
    	nueva.resenia = r;
    	nueva.sinopsis = s;
    	nueva.ubicacionPortada = u;
    	nueva.id = id;
        
    	if(esVacia()) {
    		inicio = nueva;
    		ultimo = nueva;
    		ultimo.siguiente = inicio;
    	}else {
    		ultimo.siguiente = nueva;
    		nueva.siguiente = inicio;
    		ultimo = nueva;
    	}
    	tamanio++;
    }
    
    public void agregarAlFinal(Pelicula p) {
    	Pelicula nueva = new Pelicula();
    	nueva.peliculaN = p.peliculaN;
    	nueva.nombreOriginal = p.nombreOriginal;
    	nueva.resenia = p.resenia;
    	nueva.sinopsis = p.sinopsis;
    	nueva.ubicacionPortada = p.ubicacionPortada;
    	nueva.participantes = p.participantes;
        nueva.id = p.id;
    	if(esVacia()) {
    		inicio = nueva;
    		ultimo = nueva;
    		ultimo.siguiente = inicio;
    	}else {
    		ultimo.siguiente = nueva;
    		nueva.siguiente = inicio;
    		ultimo = nueva;
    	}
    	tamanio++;
    }
    
    public void listar() {
    	if(!esVacia()) {
    		Pelicula aux = inicio;
    		int i = 0;
    		System.out.println("-> ");
    		do {
    			System.out.println("No." + i + " Nombre: " + aux.peliculaN + " Nombre Original: " + aux.nombreOriginal + 
    					" Resenia: " + aux.resenia + " Sinopsis: " + aux.sinopsis + " Ubicacion: " + aux.ubicacionPortada);
    			
                        aux = aux.siguiente;
    			i++;
    		}while(aux != inicio);
    	}
    }
    
    public ListaPeliculas buscar(String n) {
    	Pelicula aux = inicio;
    	ListaPeliculas lista = new ListaPeliculas();
    	boolean analizador = false;
    	do {
    		analizador = aux.peliculaN.toLowerCase().indexOf(n.toLowerCase())>=0;
    		if(analizador) {
    			lista.agregarAlFinal(aux.peliculaN, aux.nombreOriginal, aux.resenia, aux.sinopsis, aux.ubicacionPortada, aux.id);
    		}else {
    			aux = aux.siguiente;
    		}
    	}while(aux != inicio);
    	return lista;
    }
    
    public void setLista(ListaPeliculas l) {
    	this.lista = l;
    }
    
    public boolean buscarUnico(String n) {
    	Pelicula aux = inicio;
    	boolean encontrado = false;
    	do {
    		if(n.equalsIgnoreCase(aux.peliculaN)) {
    			encontrado = true;
    		}else {
    			aux = aux.siguiente;
    		}
    	}while(aux != inicio && encontrado!= true);
    	return encontrado;
    }
    
    public Pelicula getElement(String n) {
    	Pelicula aux = inicio;
    	Pelicula encontrado = null;
    	do {
    		if(n.equalsIgnoreCase(aux.peliculaN)) {
    			encontrado = aux;
    		}else {
    			aux = aux.siguiente;
    		}
    	}while(aux != inicio && encontrado == null);
    	return encontrado;
    }
    
    public void eliminarNodo(String n) {
    	
    	//Paso 1: Verificar que este en la lista o que exista
    	if(buscarUnico(n)) {
    		
    		//Paso 2: Probemos si el nodo que buscamos es el primero
    		if(inicio.peliculaN==n) {
    			inicio = inicio.siguiente;
    			ultimo.siguiente = inicio;
    		}else {
    			//Paso 3: Si no es el primero entonces creara una copia de la lista
    			Pelicula aux = inicio;
    			while(aux.siguiente.peliculaN != n) {
    				aux = aux.siguiente;
    			}
    			
    			
    			if(aux.siguiente==ultimo) {
    				aux.siguiente = inicio;
    				ultimo = aux;
    			}else {
    				//Paso 4: Guardamos todo menos el nodo a eliminar y seguimos con el siguiente
    				Pelicula siguiente = aux.siguiente;
    				aux.siguiente = siguiente.siguiente;
    			}
    			//fin del proceso del primer else :) 
    			
    			
    		}
    	}//fin del if >:v
    }//Y FIN DEL PROCESO!!!!!!!
    
}
