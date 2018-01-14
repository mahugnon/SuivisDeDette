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
import Model.Projet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Honoré
 */
@ManagedBean
@SessionScoped
public class ConventionPretBean {
    private Projet projet;
    Dette dette;
    Caissedepret caissedepret;
    Convention convention=new Convention();
    MetierControlleur.MetierControlleurImpl metier=new MetierControlleurImpl();

    /**
     * Creates a new instance of ConventionPrêt
     */
    public ConventionPretBean() {
    }
    public String recupererProjet(Projet proj){
        this.projet=proj;
        this.caissedepret=metier.afficherPret(projet);
        this.dette=metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
        convention.setCaissedepret(caissedepret);
        convention.setDette(dette);
        return "/ajouterConventionPret?faces-redirect=ture";
    }
    
    public String ajouterConvention(Convention conv) {
        
       //  this.caissedepret=metier.afficherPret(projet);
       // this.dette=metier.afficherDetteCaisse(caissedepret.getIdCaissedepret());
   // convention.setCaissedepret(caissedepret);
    //convention.setDette(dette);
    metier.ajouterConvention(convention);
    convention=new Convention();
    return "detailDette?faces-redirect=true";
    
}

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Dette getDette() {
        return dette;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }

    public Caissedepret getCaissedepret() {
        return caissedepret;
    }

    public void setCaissedepret(Caissedepret caissedepret) {
        this.caissedepret = caissedepret;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }
    
}
