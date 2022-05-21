<%-- 
    Document   : ActorOverview
    Created on : May 11, 2022, 6:57:17 AM
    Author     : Lourdes Pérez
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ListaSistema.*"%>
<%@page import="API.API"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="CSS/Style.css" rel="stylesheet">
    </head>
    <body>
        
        <header>
            <div>
                <a href="IndexJSP.jsp" style="text-decoration: none;">
                   <img src="IMAGES/home.jpg" style="padding-left: 1250px; width:30px; height:30px; padding-right: 10px">
                </a>
            </div>
        </header>
        
        <div class="contenedor">
            <%
                String cadena = "https://www.themoviedb.org/t/p/w780";
                String p = request.getParameter("actorId");
                API api = new API();
                Actor actor = api.getActor(Integer.parseInt(p));
                %>
                    <div class="g-col-6">
                        <h1 style="color: wheat">Peliculas en donde aparece <%=actor.nombre %></h1>
                    </div>
                    <div class="g-col-6">
                        <%
                        try{
                            Arbol arbol = api.peliculasFromActor(actor.nombre, actor.id);
                            List<String> pelis = arbol.preordenS(arbol.raiz);
                            ListaPeliculas lp = api.getData("popular");
                            
                            
                                for(int i = 0; i<api.lista.size(); i++){
                                    Pelicula aux = lp.getElement(api.nombresPeliculas.get(i));
                                    String nombre = api.nombresPeliculas.get(i);
                                    %>
                                    <button class="elemento">
                                        <a href="MovieOverview.jsp?pelicula=<%=api.ids.get(i) %>" style="text-decoration: none">
                                        <h6 style="color: wheat"><%=nombre %></h6>
                                        <img src="<%=cadena + api.lista.get(i) %>" style="width:200px; height:275px; margin:0px">
                                        </a>
                                    </button>
                                    <%
                                    if(i==api.lista.size() && api.lista.size()==0)
                                        System.out.println(api);
                                }
                        
                        }catch(Exception e){
                            %>
                            <h6 style="font-size: 20px; color: white; padding-top: 50px; padding-left: 20px">No se encontraron resultados de peliculas</h6>
                            <img src="IMAGES/sad.jpg" style="padding-left: 100px; width:95px; height:95px; padding-top: 30px">
                            <%
                        }


                        %>
                    </div>
                
        </div>
        
    </body>
</html>
