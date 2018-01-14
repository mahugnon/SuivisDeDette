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
public class ConventionBean {

    private MetierControlleurImpl metierControlleur = new MetierControlleurImpl();
    private List<Convention> listconention;
    private Societe societe;
    private Dette dette;
    private Convention convention = new Convention();
    private Integer idConvention;

    public ConventionBean() {
    }

    public String recupererSocieteConvention(Societe societe) {
        this.societe = societe;
        return "/ajouterConvention?faces-redirect=true";
    }

    public long detteRestant(Societe s) {
        dette = metierControlleur.afficherDetteSociete(s);
        return dette.getMontantRestant();
    }

    public String EtatDette(Societe s) {
        dette = metierControlleur.afficherDetteSociete(s);
        return dette.getEtat();
    }

    public String ajouterConvention(Societe s) {
        dette = metierControlleur.afficherDetteSociete(s);
        convention.setDette(dette);
        convention.setSociete(societe);
        metierControlleur.ajouterConvention(convention);
        convention = new Convention();
        return "detailSociete?faces-redirect=true";
    }

    public List<Convention> listerConventionBean() {
        listconention = (List<Convention>) metierControlleur.listerConvention();
        return listconention;
    }

    public String afficherSociete(Convention c) {
      String s="vide";
      if(c.getSociete()!=null){
           this.societe = metierControlleur.getSocieteById(c.getSociete().getIdSociete());
        s = societe.getNomSociete();
       
        return s;
      }
       return s;
    }
    
     public String afficherCaisse(Convention c) {
     
              String s="Vide";
       if(c.getCaissedepret()!=null){
            Caissedepret   caissdepret  = metierControlleur.afficherCaisseDePret(c.getCaissedepret().getIdCaissedepret());
       
            
                  Projet projet=metierControlleur.getProjetById(caissdepret.getProjet().getIdProjet());
   s = projet.getNomProjet();
   
   return s;
  
           
       }
         
       
        return s;
    }

    public String supprimerConventionBean(Convention convention) {
        metierControlleur.supprimerConvention(convention);
        return "/listerConvention?faces-redirect=true";
    }

    public String modifierConventionBean(Convention c) {
        metierControlleur.ModifierConvention(c);
        return "/listerConvention?faces-redirect=true";
    }

    public List<Convention> getListconention() {
        return listconention;
    }

    public void setListconention(List<Convention> listconention) {
        this.listconention = listconention;
    }

    public Integer getIdConvention() {
        return idConvention;
    }

    public void setIdConvention(Integer idConvention) {
        this.idConvention = idConvention;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public MetierControlleurImpl getMetierControlleur() {
        return metierControlleur;
    }

    public void setMetierControlleur(MetierControlleurImpl metierControlleur) {
        this.metierControlleur = metierControlleur;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Dette getDette() {
        return dette;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }

}
