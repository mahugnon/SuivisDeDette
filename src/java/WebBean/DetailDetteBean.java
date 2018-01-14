/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Caissedepret;
import Model.Convention;
import Model.Dette;
import Model.Echeance;
import Model.Projet;
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
public class DetailDetteBean {

    private Projet projet = new Projet();
    private MetierControlleur.MetierControlleurImpl metier = new MetierControlleurImpl();
    private Caissedepret caissedepret = new Caissedepret();
    private Dette dette = new Dette();
    private Convention convention = new Convention();
    private String type;
    private Date date = new Date();
    private Echeance echeance;

    /**
     * Creates a new instance of DetailDetteBean
     */
    public DetailDetteBean() {
        convention.setCaissedepret(caissedepret);
        convention.setDette(dette);
    }

    public String recupererProjet(Projet p) {
        this.projet = p;
        return "detailDette?faces-redirect=true";
    }

    public Object detteBrute() {
        caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            dette = metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        }
        if (dette != null) {
            return dette.getDetteBrute();
        }
        return "0";

    }

    public Object caisseDePret() {
        caissedepret = metier.afficherPret(projet);
        if (caissedepret != null) {
            return caissedepret.getDatePret();
        }

        return "0";

    }

    public String ajouterConvention() {

        metier.ajouterConvention(convention);
        convention = new Convention();
        return "/detailDette?faces-redirect=true";
    }

    public Object dettePaye() {
        caissedepret = metier.afficherPret(projet);
        dette = metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        if (dette != null) {
            return dette.getMontantPaye();
        }
        return "0";

    }

    public Object detteRestant() {
        caissedepret = metier.afficherPret(projet);
        dette = metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        if (dette != null) {
            return dette.getMontantRestant();
        }
        return "0";

    }

    public Object dateCreation() {
        caissedepret = metier.afficherPret(projet);
        dette = metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        if (dette != null) {
            return dette.getDateCreation();
        }
        return "null";

    }

    public Object dateConvention() {
        caissedepret = metier.afficherPret(projet);
        dette = metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        convention = metier.afficherConventionPret(caissedepret);
        if (convention != null) {
            return convention.getDateAjout();
        }
        return "";
    }

    public Object typeConvention() {
        caissedepret = metier.afficherPret(projet);
        dette = metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        convention = metier.afficherConventionPret(caissedepret);
        if (convention != null) {
            return convention.getType();
        }
        return "";
    }

    public List<Echeance> afficherEcheance(Projet p) {

        List<Echeance> echeances = new ArrayList<>();
        this.caissedepret = metier.afficherPret(p);
        convention = metier.afficherConventionPret(caissedepret);
        if (convention != null) {
            if (metier.listerEcheance(convention) != null) {
                echeances = metier.listerEcheance(convention);
                return echeances;
            } else {
                echeance = new Echeance();
                echeances.add(echeance);
                return echeances;
            }
        }
         echeance = new Echeance();
                echeances.add(echeance);
                return echeances;

    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

    public Caissedepret getCaissedepret() {
        return caissedepret;
    }

    public void setCaissedepret(Caissedepret caissedepret) {
        this.caissedepret = caissedepret;
    }

    public Dette getDette() {
        return dette;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
