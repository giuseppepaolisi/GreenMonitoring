package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.autenticazione.LoginManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

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
                UtenteBean user = new AziendaBean();
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("password"));
                user = AziendaDAOImpl.doRetrieve(user);
                sessione.setAttribute("currentUserSession", user);
                //ciclo for per la verifica del corretto retrieve delle informazioni del user corrente
                for (String s : Arrays.asList(user.getEmail(), user.getPassword(), user.getTelefono(), user.getCitta(), user.getProvincia(), user.getIndirizzo())) {
                    System.out.println(s);
                }
                response.sendRedirect("Dipendente.jsp");
            } else if (checkRole.matches("dipendente")) {
                UtenteBean user = new DipendenteBean();
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("password"));
                //user = DipendenteDAOImpl.doRetrieve(user);
                sessione.setAttribute("currentUserSession", user);
                response.sendRedirect("Dipendente.jsp");
            } else {
                response.sendRedirect("index.jsp?error=true");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
