<%-- 
    Document   : MovieOverview
    Created on : May 9, 2022, 6:03:59 PM
    Author     : Lourdes Pérez
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
    import="java.util.Iterator,API.*"
    import="java.util.Iterator,ListaSistema.*"%>
<%@ page import= "java.io.IOException" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/MOpagestyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        
        <header>
            <div>
                <a href="IndexJSP.jsp" style="text-decoration: none;">
                   <img src="IMAGES/home.jpg" style="padding-left: 1250px; width:30px; height:30px; padding-right: 10px">
                </a>
            </div>
        </header>
        
        <%
            String cadena = "https://www.themoviedb.org/t/p/w780";
            
            String parametroPeli = request.getParameter("pelicula");
            API api = new API();
            ListaPeliculas lp = api.getData("popular");
            Pelicula pPelicula = lp.getElement(parametroPeli);
            

        %>
        <section id="pantalla-dividida">
            <div class="izquierda">
                <img src="<%=cadena + pPelicula.ubicacionPortada %>" style="width:500px; height:600px; margin:0px"> 
            </div>
            <div class="derecha" 
                 style="align-content: center">
                <h1 style="color: wheat; font-size: 25px;"><%=pPelicula.peliculaN %> </h1>
                <h2 style="color: wheat; font-size: 16px;">Original name: <%=pPelicula.nombreOriginal %></h2>
                <h5 style="color: wheat; font-size: 13px; text-align: center;"><%=pPelicula.sinopsis %></h5>
                <section id="actores">
                    <div class="abajo">
                        <%
                            String ubicacionImagen = null;
                            Arbol arbolActores = api.getActorsFMovie(pPelicula.id);
                            int nparticipantes = api.listaA.size();
                            if(nparticipantes==5 || nparticipantes>5){
                                nparticipantes = 5;
                            }
                            List<Actor> listaA = api.listaA;
                            for(int i = 0; i<nparticipantes; i++){
                                Actor actor = listaA.get(i);
                                if(actor.profilepicture.equals("null")){
                                    ubicacionImagen = "IMAGES/person.jpg";
                                }else{
                                    ubicacionImagen = cadena + actor.profilepicture;
                                }

                        %>
                        <a href="http://localhost:8090/mavenproject1/ActorOverview.jsp?actorId=<%=actor.id%>">
                            <botton class="element" id="cuadroActor">
                                <div class="name">
                                    <h5 style="color: wheat;"><%=actor.nombre %> </h5>
                                </div>
                                <div class="img">
                                    <img src="<%=ubicacionImagen %>" style="width:100px; height:140px; margin:0px">
                                </div>
                            </botton>
                        </a>


                        <%

                            }
                        %>
                        
                    </div>
                        <a href="http://localhost:8090/mavenproject1/MoreActors.jsp?pelicula=<%=parametroPeli %>" style="text-decoration: none;">
                            <button class="buttons" style="margin-top: 50px">
                                    <h4 style="color: whitesmoke">Click for more</h4>
                            </button>
                        </a>
                </section>
            </div>
        </section>
        
        
    </body>
</html>
