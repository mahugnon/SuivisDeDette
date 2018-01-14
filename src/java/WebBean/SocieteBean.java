/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Societe;
import java.io.Serializable;
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
public class SocieteBean implements Serializable {

    private final MetierControlleurImpl metierControlleur=new MetierControlleurImpl();
    private Societe societe;
    private List<Societe> listsociete;

    /**
     * Creates a new instance of SocieteBean
     */
    public SocieteBean() {
        societe = new Societe();
        listsociete = new ArrayList<>();
    }

    public String ajouterSocieteBean() {
        metierControlleur.ajouterSociete(societe);
        societe.setIdSociete(null);
        societe.setAdresseSociete("");
        societe.setContactSociete("");
        societe.setNature("");
        societe.setNomSociete("");
        //this.societe=new Societe();
        return "/listerSociete?faces-redirect=true";
    }

    //pour la facture
    public String ajouterDiagSocieteBean() {
        metierControlleur.ajouterSociete(societe);
        return "/listerSociete?faces-redirect=true";
    }

    public String supprimerSocieteBean(Societe societes) {
        societe = societes;
        metierControlleur.supprimerSociete(societe);
        return "/listerSociete?faces-redirect=true";
    }

    public List<Societe> listerSocieteBean() {
        listsociete = metierControlleur.afficherSociete("");
        return listsociete;
    }
    
    public String modifierSocieteBean() {
        metierControlleur.modifierSociete(societe);
       societe.setIdSociete(null);
       societe.setAdresseSociete("");
       societe.setContactSociete("");
        societe.setNature("");
        societe.setNomSociete("");
        return "/listerSociete?faces-redirect=true";
    }

    public String editerSocieteBean(Societe societe) {
        this.societe = societe;
        return "/modifierSociete?faces-redirect=true";
    }
    
   

    public Societe getSociete() {
        return societe;
    }
    
    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    
    public List<Societe> getListsociete() {
        return listsociete;
    }
    
    public void setListsociete(List<Societe> listsociete) {
        this.listsociete = listsociete;
    }
    
}
