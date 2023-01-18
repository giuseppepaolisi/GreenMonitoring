package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.autenticazione.LoginManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * Object that provides the methods to manage the Terreno.
     */
    private final LoginManager lm = new LoginManager();
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

        HttpSession sessione = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            String checkRole = lm.CheckData(email, password);
            if (checkRole.matches("azienda")) {
                sessione.setAttribute("currentUserSession", email);
                response.sendRedirect("Terreni.jsp");
            } else if (checkRole.matches("dipendente")) {
                sessione.setAttribute("currentUserSession", email);
                response.sendRedirect("Dipendente.jsp");
            } else {
                response.sendRedirect("index.jsp?error=true");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}