<%-- 
    Document   : IndexJSP
    Created on : Apr 25, 2022, 2:32:12 AM
    Author     : Lourdes Pérez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.Iterator,API.*"
        import="java.util.Iterator,ListaSistema.*"%>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="ISO-8859-1">
        <title>Home</title>
        <!--LINKS A HOJAS CSS-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/Style.css" >
    </head>



    <body>
        <header>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
            <div align="center" style="margin-top:20px; color: windowframe"><h1>Le Cinema</h1></div>
        </header>

        <div class="contenedor">
            <%
                API api = new API();
                ListaPeliculas listFromApi = api.getData("popular");
                if (listFromApi != null) {
                    Pelicula aux = listFromApi.inicio;
                    do {
                        String cadena = "https://www.themoviedb.org/t/p/w780";
            %>
            <button class="elemento">
                <a href="http://localhost:8090/mavenproject1/MovieOverview.jsp?pelicula=<%=aux.peliculaN%>" style="text-decoration: none">
                    <img src="<%=cadena + aux.ubicacionPortada%>" style="width:200px; height:275px; margin:0px">
                    <h6><%=aux.peliculaN%></h6>
                    <p><%=aux.sinopsis.substring(0, 50) + "..."%></p>
                </a>  
            </button>   
            <%

                        System.out.println("Obteniendo..." + aux.participantes.length);
                        aux = aux.siguiente;
                    } while (aux != listFromApi.inicio);
                    session.setAttribute("miVariable", listFromApi);
                }
            %>



        </div>

    </body>

</html>