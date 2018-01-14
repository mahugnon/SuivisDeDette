/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Caissedepret;
import Model.Convention;
import Model.Echeance;
import Model.Projet;
import Model.Societe;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class EcheanceBean {

    private Echeance echeance = new Echeance();
    private MetierControlleur.MetierControlleurImpl metier = new MetierControlleurImpl();
    Convention convention;
    Societe societe;
    Projet projet;

    public String recupererConvention(Societe s) {
        this.societe = s;
        this.convention = metier.afficherConventionSociete(societe);
        return "/ajouterEcheance?faces-redirect=true";
    }

    public String recupererConventionProjet(Projet p) {
        this.projet = p;
        Caissedepret caissedepret = metier.afficherPret(projet);
        this.convention = metier.afficherConventionPret(caissedepret);
        return "/ajouterEcheanceConvPret?faces-redirect=true";
    }

   

    public String typeConvention() {
        return convention.getType();
    }

    public String ajouterEcheance() {
        echeance.setEtatEcheance("Non Payé");
        echeance.setConvention(convention);
        metier.ajouterEcheance(echeance);
        echeance = new Echeance();
        return "/detailSociete?faces-redirect=true";
    }

    public String ajouterEcheanceConvPret() {
        echeance.setEtatEcheance("Non Payé");
        echeance.setConvention(convention);
        metier.ajouterEcheance(echeance);
        echeance = new Echeance();
        return "/detailDette?faces-redirect=true";
    }

    public String supprimerEcheanceBean(Echeance echeance) {
        this.echeance = echeance;
        metier.supprimerEcheance(echeance);
        return "/detailSociete?faces-redirect=true";
    }

    public String modifierEcheancetBean(Echeance echeance) {

        metier.modifierEcheance(echeance);
        return "/detailSociete?faces-redirect=true";
    }
    
 public String modifierEcheancePretBean(Echeance echeance) {

        metier.modifierEcheance(echeance);
        return "/detailDette?faces-redirect=true";
    }

    public String consulterEcheanceBean(Echeance echeance) {
        this.echeance = echeance;
        return "/detailEcheance?faces-redirect=true";
    }
    
     public String consulterEcheancePretBean(Echeance echeance) {
        this.echeance = echeance;
        return "/detailEcheance_1?faces-redirect=true";
    }
     
      public String recupererEcheancePretBean(Echeance echeance) {
        this.echeance = echeance;
        return "/modifierEcheance_1?faces-redirect=true";
    }


    public String recupererEcheanceBean(Echeance echeance) {
        this.echeance = echeance;
        return "/modifierEcheance?faces-redirect=true";
    }

    public String ajouterEcheance(Convention convention) {
        echeance.setConvention(convention);
        metier.ajouterEcheance(echeance);
        echeance = new Echeance();
        return "/detailSociete?faces-redirect=true";
    }

    public Echeance getEcheance() {
        return echeance;
    }

    public void setEcheance(Echeance echeance) {
        this.echeance = echeance;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

}
