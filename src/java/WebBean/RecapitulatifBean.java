/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Dette;
import Model.Societe;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Franckos
 */
@ManagedBean
@SessionScoped
public class RecapitulatifBean {
    List<Societe> societes;
    Dette dette;
    
    MetierControlleur.MetierControlleurImpl metier=new MetierControlleurImpl();
    

   
    public RecapitulatifBean() {
        societes=metier.afficherSociete("");
    }
    
     public Dette recupererDetteSociete(Societe s){
         return metier.afficherDetteSociete(s);
     }
     
     public double montantDette(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getDetteBrute();
     }
     
      public double montantPayer(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getMontantPaye();
     }
      
       public double montantDetteRestant(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getDetteBrute()-dette.getMontantPaye();
     }
       
       public int anneeDette(Societe societe){
           dette=metier.afficherDetteSociete(societe);
           return dette.getAnnee();
       }

    public List<Societe> getSocietes() {
        return societes;
    }

    public void setSocietes(List<Societe> societes) {
        this.societes = societes;
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
     
     
   
}
