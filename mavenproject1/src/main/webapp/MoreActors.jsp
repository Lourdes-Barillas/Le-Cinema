<%-- 
    Document   : MoreActors
    Created on : May 20, 2022, 12:32:17 PM
    Author     : Lourdes Pérez
--%>

<%@page import="java.util.List"%>
<%@page import="ListaSistema.Actor"%>
<%@page import="ListaSistema.Arbol"%>
<%@page import="ListaSistema.Pelicula"%>
<%@page import="ListaSistema.ListaPeliculas"%>
<%@page import="API.API"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            ListaPeliculas lp = lp = (ListaPeliculas) session.getAttribute("listaFromMovieOverview");
            Pelicula pPelicula = lp.getElement(parametroPeli);
            

        %>
        <section id="pantalla-dividida">
            <div class="izquierda" style="padding-right: 0; margin-right: 0;">
                <img src="<%=cadena + pPelicula.ubicacionPortada %>" style="width:500px; height:600px; margin:0px"> 
            </div>
            <div class="derecha" 
                 style="align-content: center; margin-top: 100px;">
                <h1 style="color: wheat; font-size: 25px;"><%=pPelicula.peliculaN %> </h1>
                <h2 style="color: wheat; font-size: 16px;">Original name: <%=pPelicula.nombreOriginal %></h2>
                <h5 style="color: wheat; font-size: 13px; text-align: center;"><%=pPelicula.sinopsis %></h5>
                <a href="http://localhost:8090/mavenproject1/MovieOverview.jsp?pelicula=<%=parametroPeli %>" style="text-decoration: none;">
                    <button class="buttons" style="margin-top: 50px">
                            <h4 style="color: whitesmoke">Click for less</h4>
                    </button>
                </a>
            </div>
        </section>
        
        <section id="actores">
            <div class="abajo">
                <%
                    try{
                        String ubicacionImagen = null;
                        Arbol arbolActores = api.getActorsFMovie(pPelicula.id);
                        int nparticipantes = api.listaA.size();
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
                        <botton class="element" id="cuadroActor" 
                                style="padding-right: 10px; padding-left: 15px; padding-bottom: 20px; background-color:#b8b6ba; height: 230px">
                            <div class="name">
                                <h5 style="color: #17002e;"><%=actor.nombre %> </h5>
                            </div>
                            <div class="img">
                                <img src="<%=ubicacionImagen %>" style="width:100px; height:120px; margin:0px; color: white">
                            </div>
                        </botton>
                    </a>


                    <%

                        }
                    }catch(Exception e){
                        System.out.println(e);
                        %>
                        <h6>Sorry... No more actors</h6>
                        <%
                    }
                %>
            <br>    
            </div>
                <a href="http://localhost:8090/mavenproject1/MovieOverview.jsp?pelicula=<%=parametroPeli %>" style="text-decoration: none;">
                    <button class="buttons" style="margin-top: 50px">
                            <h4 style="color: whitesmoke">Click for less</h4>
                    </button>
                </a>
        </section>
    </body>
</html>
