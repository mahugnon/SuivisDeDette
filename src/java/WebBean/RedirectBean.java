/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Franckos
 */
@ManagedBean
@SessionScoped
public class RedirectBean implements Serializable{

    /**
     * Creates a new instance of RedirectBean
     */
    public RedirectBean() {
    }

    public String verscreationSociete() {
        return "/AjouterSociete?faces-redirect=true";
    }

    public String versajouterConvention() {
        return "/ajouterConvention?faces-redirect=true";
    }
    
     public String verslisterFacture() {
        return "/listerFacture?faces-redirect=true";
    }

    public String versajouterFacture() {
        return "/AjouterFacture?faces-redirect=true";
    }

    public String verscreationProjet() {
        return "/AjouterProjet?faces-redirect=true";
    }

    public String versdetailProjet() {
        return "/detailProjet?faces-redirect=true";
    }
    public String verslisterProjet() {
        return "/listerProjet?faces-redirect=true";
    }

    public String verslisterSociete() {
        return "/listerSociete?faces-redirect=true";
    }

    public String versVoirDetteSociete() {
        return "/voirDetteSociete?faces-redirect=true";
    }

    public String versAcueil() {
        return "/accueil?faces-redirect=true";
    }
    
    

    public String verslisterConvention() {
        return "/listerConvention?faces-redirect=true";
    }

    public String versDetailSociete() {
        return "/detailSociete?faces-redirect=true";
    }
    
      public String versDetailDette() {
        return "/detailDette?faces-redirect=true";
    }
    
      
      public String versListerDette() {
        return "/ListerDette?faces-redirect=true";
    }
    
      
     public String versajouterEcheance() {
        return "/ajouterEcheance?faces-redirect=true";
    }
     
      public String versajouterConventionPret() {
        return "/ajouterConventionPret?faces-redirect=true";
    }
}
