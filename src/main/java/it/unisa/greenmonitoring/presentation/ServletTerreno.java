package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(name = "ServletTerreno", value = "/ServletTerreno")
@MultipartConfig
public class ServletTerreno extends HttpServlet {
    /**
     * Object that provides the methods to manage the Terreno.
     */
    private TerrenoManager tm = new TerrenoManager();
    /**
     * Method that handle the GET requests.
     * @param request the request from the client.
     * @param response the response from the server.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Method that handle the POST requests.
     * @param request the request from the client.
     * @param response the response from the server.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("inserisciTerreno_submit") != null) {
            inserisciTerreno(request, response);
        } else {
            Enumeration<String> parameters = request.getParameterNames();
            while (parameters.hasMoreElements()) {
                String id = request.getParameter(parameters.nextElement());
                tm.rimuoviTerreno(id);
            }
            response.sendRedirect("Terreni.jsp");
            return;
        }

        response.sendRedirect("index.jsp");

    }

    private String inserisciTerreno(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String azienda = request.getParameter("azienda");

        Part immagine = request.getPart("immagine");
        String fileName = Paths.get(immagine.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = immagine.getInputStream();
        // Salva l'immagine su disco
        String path = "C:\\Users\\stefa\\IdeaProjects\\GreenMonitoring\\src\\main\\webapp\\img\\" + fileName;
        Files.copy(fileContent, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        fileContent.close();

        Float latitudine = Float.parseFloat(request.getParameter("latitudine"));
        Float longitudine = Float.parseFloat(request.getParameter("longitudine"));
        String superfice = request.getParameter("superfice");
        TerrenoBean terreno = new TerrenoBean(latitudine, longitudine, superfice, fileName, azienda);
        try {
            tm.createTerreno(terreno);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}