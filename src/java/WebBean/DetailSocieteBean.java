 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Convention;
import Model.Dette;
import Model.Echeance;

import Model.Societe;
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
public class DetailSocieteBean {
    Societe societe;
    MetierControlleur.MetierControlleurImpl metier=new MetierControlleurImpl();
    Convention convention;
    Echeance echeance=new Echeance();
    List<Echeance> echeances=new ArrayList<>();
    Dette dette;
    

    /**
     * Creates a new instance of DetailSocieteBean
     */
    public DetailSocieteBean() {
    }
    
    public String recupererSociete(Societe societe){
        this.societe= societe;
        return "/detailSociete?faces-redirect=true";
    }
    
    public Dette detteSocieteBean(Societe societe){
       return metier.afficherDetteSociete(societe);
    }
    
    public Convention convensitionSocieteBean(Societe societe){
        if(metier.afficherConventionSociete(societe)!=null)
        return metier.afficherConventionSociete(societe);
        else{
            convention=new Convention();
             return convention;
        }
           
    } 
    
    public Date dateConventionBean(Societe societe){
        if(metier.afficherConventionSociete(societe)!=null){
            
                    convention=convensitionSocieteBean(societe);
        return convention.getDateAjout();
        }
        else return null ;
    }
    
    public String typeConventionBean(Societe societe){
        
         if(metier.afficherConventionSociete(societe)!=null){
             convention=convensitionSocieteBean(societe);
                 return convention.getType();
     
         }
         else return "";
    }
    
  
         public long montantDette(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getDetteBrute();
     }
     
      public long montantPayer(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getMontantPaye();
     }
      
       public long montantDetteRestant(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getDetteBrute()-dette.getMontantPaye();
     }
      
       public String etatDette(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getEtat();
     }
       
       public int anneeDette(Societe societe){
         dette=metier.afficherDetteSociete(societe);
         return  dette.getAnnee();
     }
    
    public List<Echeance> listerEcheanceConvBean(Societe societe){
        List<Echeance> list=new ArrayList<>();
        this.convention=convensitionSocieteBean(societe);
        if(metier.listerEcheance(convention)!=null)    
        return metier.listerEcheance(convention);
       list.add(echeance);
        return list;
   
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
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
