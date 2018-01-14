/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Caissedepret;
import Model.Lignebudgetaire;
import Model.Projet;
import Model.Tranche;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Honore
 */
@ManagedBean
@SessionScoped
public class DetailProjetBean {

    Projet projet;
    Caissedepret caissedepret = new Caissedepret();
    MetierControlleur.MetierControlleurImpl metier = new MetierControlleurImpl();
    List<Tranche> ts = new ArrayList<>();

    /**
     * Creates a new instance of detailProjetBean
     */
    public DetailProjetBean() {
    }

    public String recupererProjet(Projet p) {
        this.projet = p;
        return "/detailProjet?faces-redirect=true";
    }

    public Object typeBudget(Projet p) {
        Lignebudgetaire ligne = metier.consultLignebudgetaire(p.getLignebudgetaire().getIdBudget());
        if (ligne != null) {
            return ligne.getNomLigne();
        }
        return "null";
    }

    public int anneeBudget(Projet p) {
        Lignebudgetaire ligne = metier.consultLignebudgetaire(p.getLignebudgetaire().getIdBudget());
        return ligne.getAnneeBudgetaire();
    }

    public Caissedepret afficherCaisssedePretBean(Projet p) {
        this.caissedepret = metier.afficherPret(p);
        return caissedepret;
    }

    public Object dateDerniereTranche() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getDateDerniereTranche();
        }
        return "null";
    }

    public Object datePret() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getDatePret();
        }
        return "null";
    }

    public Object delaiDeGrace() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getDelaiDeGrace();
        }
        return "null";
    }

    public Object montantDebloquer() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getMontantDebloquer();
        } else {
            return "0";
        }
    }

    public Object interet() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getInteret() + "%";
        } else {
            return "0%";
        }
    }

    public Object penalite() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getPenalite();
        } else {
            return "0";
        }
    }

    public Object dettePret() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return metier.afficherDetteCaisse(caissedepret.getIdCaissedepret()).getMontantRestant();
        } else {
            return "0";
        }
    }

    public List<Tranche> tranches() {
        this.caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            this.ts = metier.ListerTranche(caissedepret.getIdCaissedepret());
        }
        return ts;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Caissedepret getCaissedepret() {
        return caissedepret;
    }

    public void setCaissedepret(Caissedepret caissedepret) {
        this.caissedepret = caissedepret;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

}
