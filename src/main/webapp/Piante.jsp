<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %><%--
  Created by IntelliJ IDEA.
  User: Manuel
  Date: 23/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    if (u instanceof AziendaBean) { %>
<%@include file="fragments/headerLoggedAzienda.html"%>
<%
    }
%>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <title>Lista Piante</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/TerreniJS.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        img {
            height: 70px;
            width: auto;
            object-fit: contain;
        }
    </style>
</head>
<body>



<div class="bd py-5">
    <legend style="font-family: 'Lobster', cursive; text-align: center; font-size: 35px;">Piante</legend>
    <form id="visualizza_piante" action="ServletPianta" method="post">
        <div class="container py-5">
            <div class="row">
                <div class="col-14 py-5">

                    <table class="table table-group-divider">
                        <thead>
                        <tr  style="font-family: 'Staatliches', cursive; font-size: 20px;">
                            <th scope="col"></th>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Descrizione</th>
                            <th scope="col">Ph minimo</th>
                            <th scope="col">Ph massimo</th>
                            <th scope="col">Temperatura minima</th>
                            <th scope="col">Temperatura massima</th>
                            <th scope="col">Umidità minima</th>
                            <th scope="col">Umidità massima</th>
                            <th scope="col">Immagine</th>
                            <th scope="col">Azione</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            /* -- INIZIO AUTENTICAZIONE --*/
                            Object seo = session.getAttribute("currentUserSession");
                            String email = null;

                            if (seo == null) {
                                response.sendError(401);
                            }

                            if (seo instanceof AziendaBean) {
                                email = ((AziendaBean) seo).getEmail();
                            } else if (seo instanceof DipendenteBean) {
                                response.sendError(401);
                            }

                            PiantaManager p = new PiantaManager();
                            List<PiantaBean> list = p.ListaPianteManager(email);
                            int i = 0;
                            for (PiantaBean pb : list) {
                                out.print("<tr>" +
                                        "<td>");
                                out.print("</td>" +
                                        "<td>" + pb.getId() + "</td>" +
                                        "<td>" + pb.getNome() + "</td>" +
                                        "<td><div class=\"overflow-auto\" style=\"max-width: 260px; max-height: 100px;\" >" + pb.getDescrizione() + "</div></td>" +
                                        "<td>" + pb.getPh_min() + "</td>" +
                                        "<td>" + pb.getPh_max() + "</td>" +
                                        "<td>" + pb.getTemperatura_min() + "</td>" +
                                        "<td>" + pb.getTemperatura_max() + "</td>" +
                                        "<td>" + pb.getUmidita_min() + "</td>" +
                                        "<td>" + pb.getUmidita_max() + "</td>" +
                                        "<td> <img src=\"" + request.getContextPath() + "/../immagini/piante/" + pb.getImmagine() + "\" alt=\"Descrizione immagine\"></td>" +
                                        "<td> <button type=\"submit\" name=\"rimuoviPianta_submit\" class=\"btn btn-danger\" value=\"" + pb.getId() + "\">Rimuovi</button>" +
                                        " <button type=\"submit\" value=\"" + pb.getId() + "\"class=\"btn btn-danger\" name=\"modificaRange_submit\">Modifica</button></td>" +
                                        "</tr>"
                                );
                                i++;
                            }
                        %>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>

    </form>
    <div class="container py-2">
        <div class="row justify-content-center">
            <div class="col-3">
                <button onclick="location.href='InserisciPianta.jsp'" type="button"
                        class="btn btn-outline-success btn-lg px-5" data-toggle="Modal"
                        data-target="#exampleModalCenter">
                    Aggiungi Pianta
                </button>
            </div>
        </div>
    </div>
</div><!-- End bd -->


<%@include file="fragments/footer.html" %>
</body>
</html>
