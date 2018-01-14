/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Caissedepret;
import Model.Projet;
import Model.Tranche;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Honore
 */
@ManagedBean
@SessionScoped
public class TrancheBean {
    Projet projet;
    Caissedepret caissedepret;
    Tranche tranche=new Tranche();
    MetierControlleur.MetierControlleurImpl metier=new MetierControlleurImpl();

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public TrancheBean() {
    }
    
    public String recupererProjet(Projet p){
        this.projet=p;
        caissedepret=metier.afficherPret(projet);
        return "/ajouterTranche?faces-redirect=true";
    }
    
    public String ajouterTranche(){
        tranche.setCaissedepret(caissedepret);
        metier.ajouterTranche(tranche);
        return "/detailProjet?faces-redirect=true";
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

    public Tranche getTranche() {
        return tranche;
    }

    public void setTranche(Tranche tranche) {
        this.tranche = tranche;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }
    
    
    
}
