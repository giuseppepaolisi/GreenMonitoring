package it.unisa.greenmonitoring.businesslogic.gestionesensore;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneSensoreDAO;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneSensoreDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class MisurazioneSensoreManager {

    /**
     * MisurazioneSensoreDAO.
     */
    private MisurazioneSensoreDAO misurazioneSensoreDAO;
    /**
     * Questo metodo restituisce le misurazioni più recenti a partire da un'azienda.
     * @param id_azienda
     * @param id_coltivazione
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public List<MisurazioneSensoreBean> restituisciMisurazioniRecenti(String id_azienda, int id_coltivazione) {
        misurazioneSensoreDAO = new MisurazioneSensoreDAOImpl();
        try {
            return misurazioneSensoreDAO.restituisciMisurazioniRecenti(id_azienda, id_coltivazione);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo restituisce le misurazioni per periodo.
     * @param data_inizio_periodo
     * @param data_fine_periodo
     * @param coltivazione
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public List<MisurazioneSensoreBean> restituisciMisurazioniPerPeriodo(java.sql.Date data_inizio_periodo, java.sql.Date data_fine_periodo, int coltivazione) {
        misurazioneSensoreDAO = new MisurazioneSensoreDAOImpl();
        try {
            return misurazioneSensoreDAO.restituisciMisurazioniPerPeriodo(data_inizio_periodo, data_fine_periodo, coltivazione);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
