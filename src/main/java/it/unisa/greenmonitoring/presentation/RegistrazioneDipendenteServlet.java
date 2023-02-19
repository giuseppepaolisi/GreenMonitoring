package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;


import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrazioneDipendenteServlet", value = "/RegistrazioneDipendenteServlet")
public class RegistrazioneDipendenteServlet extends HttpServlet {


    /**
     * Dichiaro la variabile privata AziendaManager.
     */
    private AutenticazioneManager aziendaManager = new AutenticazioneManager();

    /**
     * Metodo doGet.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("inputNome");
        String cognome = request.getParameter("inputCognome");
        String email = request.getParameter("inputEmail");
        String confermEmail = request.getParameter("confermaInputEmail");
        String password = request.getParameter("inputPassword");
        String confermPassword = request.getParameter("confermaInputPassword");
        String indirizzo = request.getParameter("inputIndirizzo");
        String citta = request.getParameter("inputCitta");
        String provincia = request.getParameter("inputProvincia");
        String telefono = request.getParameter("inputTelefono");
        String azienda = request.getParameter("inputAzienda");

        if (!(password.equals(confermPassword)) || !(email.equals(confermEmail))) {
            System.out.println("\nErrore email o password diverse ");
            response.sendRedirect("RegistrazioneDipendente.jsp");
        } else {

            DipendenteBean dipendenteBean = new DipendenteBean();

            dipendenteBean.setAzienda(null);
            dipendenteBean.setCognome(cognome);
            dipendenteBean.setNome(nome);
            dipendenteBean.setCitta(citta);
            dipendenteBean.setPassword(password);
            dipendenteBean.setEmail(email);
            dipendenteBean.setTelefono(telefono);
            dipendenteBean.setProvincia(provincia);
            dipendenteBean.setIndirizzo(indirizzo);

            if (!(dipendenteBean.getCitta().matches("^[a-zA-Z]+$"))) {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Dipendente.jsp");
                dispatcher.forward(request, response);
            } else if (!(dipendenteBean.getProvincia().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome della Provincia\n");
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Dipendente.jsp");
                dispatcher.forward(request, response);
            } else if (!(dipendenteBean.getPassword().matches("^[a-zA-Z0-9!@#$%^&*]+$"))) {
                System.out.println("\nErrore nella password\n");
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Dipendente.jsp");
                dispatcher.forward(request, response);
            } else if (!(dipendenteBean.getNome().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome");
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Dipendente.jsp");
                dispatcher.forward(request, response);
            } else if (!(dipendenteBean.getCognome().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome");
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Dipendente.jsp");
                dispatcher.forward(request, response);
            } else {
                try {
                    aziendaManager.registraDipendente(dipendenteBean);
                    response.sendRedirect("index.jsp");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Metodo post.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
