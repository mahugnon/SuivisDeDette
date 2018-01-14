/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Caissedepret;
import Model.Dette;
import Model.Projet;
import Model.Societe;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class DetteBean {

    private List<Dette> dettes;
    private Dette dette;
    private MetierControlleur.MetierControlleurImpl metier;
    private Societe societe;
    private Caissedepret caissedepret;
    private Projet projet;

    public DetteBean() {
        metier = new MetierControlleurImpl();
        dettes = metier.listerDette();
    }

    public List<Dette> listerDetteBean() {
        List<Dette> list = metier.listerDette();
        return list;
    }

    public String supprimerDetteBean() {

        return "/ListerDette?faces-redirect=true";
    }

    public String modifierDetteBean() {
        metier.mettreDetteAjour(dette);
        return "/ListerDette?faces-redirect=true";
    }

    public String editerDetteBean(Dette dette) {
        this.dette = dette;
        return "/modifierDette?faces-redirect=true";
    }

    public String afficherSocieteDetteBean(Dette d) {
        if (d.getSociete() != null) {
            societe = metier.getSocieteById(d.getSociete().getIdSociete());
            return societe.getNomSociete();
        } else {
            return "";
        }
    }

    public Integer afficherCaiseDetteBean(Dette d) {
        if (d.getCaissedepret() != null) {
//        caissedepret = metier.afficherPret(d.getCaissedepret().getIdCaissedepret());
            return caissedepret.getIdCaissedepret();
        } else {
            return 0;
        }
    }

    public Societe recupererSocieteDetteBean(Dette det) {
        this.dette = det;
        societe = metier.getSocieteById(dette.getSociete().getIdSociete());
        return societe;

    }

    public List<Dette> getDettes() {
        return dettes;
    }

    public void setDettes(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public Dette getDette() {
        return dette;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Caissedepret getCaissedepret() {
        return caissedepret;
    }

    public void setCaissedepret(Caissedepret caissedepret) {
        this.caissedepret = caissedepret;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

}
