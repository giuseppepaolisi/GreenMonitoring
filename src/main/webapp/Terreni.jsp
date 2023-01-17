<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%  %>
    <title>Terreni</title>
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
</head>
<body>
<div class="bd">
    <legend>Terreni</legend>
    <!-- <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
            <li class="breadcrumb-item active">Terreni</li>
        </ol>
    </nav> -->
<form id="rimuovi_terreno" action="ServletTerreno" method="post">
    <div id="alrt" class="alert alert-warning fade show" role="alert">
        <i class="bi bi-exclamation-triangle me-1"> Selezionare almeno un terreno.</i>
    </div>
    <div class="card">
        <div class="card-body">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Immagine</th>
                <th scope="col">Latitudine</th>
                <th scope="col">Longitudine</th>
                <th scope="col">Superfice</th>
            </tr>
            </thead>
            <tbody>
                <%
                    TerrenoManager t = new TerrenoManager();

                    //List<TerrenoBean> list = t.visualizzaListaTerreni(session.getAttribute("Azienda"));


                    List<TerrenoBean> list = new ArrayList<>();
                    TerrenoBean t1 = new TerrenoBean(new Random().nextFloat(), new Random().nextFloat(), "pianeggiante", "esempio", "esempio");
                    t1.setId(String.valueOf(new Random().nextInt()));
                    TerrenoBean t2 = new TerrenoBean(new Random().nextFloat(), new Random().nextFloat(), "collinare", "esempio2", "esempio2" );
                    t2.setId(String.valueOf(new Random().nextInt()));
                    list.add(t1);
                    list.add(t2);


                    int i = 0;
                    for (TerrenoBean tb : list) {
                        System.out.print("<tr>" +
                                "<td>"+
                                "<input id=\"chk\" name=\"terreno"+i+"\" type=\"checkbox\" value=\""+ tb.getId() +"\"></input>" +
                                "</td>"+
                                "<td>" + tb.getImmagine() + "</td>" +
                                "<td>" + tb.getLatitudine()+ "</td>" +
                                "<td>" + tb.getLongitudine() + "</td>" +
                                "<td>" + tb.getSuperficie() + "</td>" + "</tr>"
                        );
                    }
                %>
            </tbody>
        </table>
        </div>
    </div>
    <!-- Button trigger modal -->
    <button id="showModal" type="button" class="btn btn-primary" data-toggle="Modal" data-target="#exampleModalCenter" onclick="validate()">
        Rimuovi terreni
    </button>
</form>
</div><!-- End bd -->

<!-- Modal -->
<div id=Modal class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Sei sicuro di voler effettuare la rimozione?
            </div>
            <div class="modal-footer">
                <button id="closeModal" type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <button id="summit" type="button" class="btn btn-primary">Sì</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
