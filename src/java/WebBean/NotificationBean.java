/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Dette;
import Model.Echeance;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@RequestScoped
public final class NotificationBean {

    private List<Echeance> echeances, echeancement;
    private List<Dette> dettes;
    private List<Dette> depasse;
    private MetierControlleur.MetierControlleurImpl metier = new MetierControlleurImpl();

    /**
     * Creates a new instance of NotificationBean
     */
    public NotificationBean() {
         this.depasse = new ArrayList<>();
        this.echeancement = new ArrayList<>();
        echeances = metier.listEcheance();
        dettes = metier.listerDette();

        //Echeance dont la date de paiement est atteind
        for (Echeance next : echeances) {
            if (verifEcheancement(next)==true) {
                echeancement.add(next);
            }
        }

        //Dette dépassé
        for (Dette next : dettes) {
            if (verifDepassement(next) == true) {
                depasse.add(next);
            }
        }

    }

    @PostConstruct
    public void init() {
       
    }

    public boolean verifEcheancement(Echeance e) {
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.setTime(e.getDatePaiement());
        c.add(Calendar.DATE, -7);
        long currentDate = c1.getTimeInMillis();
        long dateEcheancement = c.getTimeInMillis();
        if ((dateEcheancement<= currentDate ) & e.getEtatEcheance().equals("Non Payé")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean verifDepassement(Dette d) {
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.setTime(d.getDateCreation());
        c.add(Calendar.YEAR, 4);
        c.add(Calendar.DATE, -7);
        long currentDate = c1.getTimeInMillis();
        long dateDepassement = c.getTimeInMillis();
        if ((dateDepassement<=currentDate) & d.getEtat().equals("Non Payé") ) {
            return true;
        } else {
            return false;
        }
    }

    public List<Echeance> getEcheances() {
        return echeances;
    }

    public void setEcheances(List<Echeance> echeances) {
        this.echeances = echeances;
    }

    public List<Echeance> getEcheancement() {
        return echeancement;
    }

    public void setEcheancement(List<Echeance> echeancement) {
        this.echeancement = echeancement;
    }

    public List<Dette> getDettes() {
        return dettes;
    }

    public void setDettes(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public List<Dette> getDepasse() {
        return depasse;
    }

    public void setDepasse(List<Dette> depasse) {
        this.depasse = depasse;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

}
