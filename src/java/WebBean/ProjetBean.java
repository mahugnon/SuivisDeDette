/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Projet;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Model.Lignebudgetaire;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;


/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class ProjetBean  implements Serializable{
private MetierControlleur.MetierControlleurImpl metierControlleur=new MetierControlleurImpl();
private Projet projet;
private String nomLinge;
private Lignebudgetaire lignebudgetaire=new Lignebudgetaire();
private final  List<Lignebudgetaire> lignes;
public int annee;
private List<Projet> listprojet;
    /**
     * Creates a new instance of ProjetBean
     */
    public ProjetBean() {
        lignes=metierControlleur.ListerLignes();
        projet=new Projet();
    }
    public String ajouterProjetBean(){
        Calendar c=Calendar.getInstance();
        annee=c.get(Calendar.YEAR);
      lignebudgetaire= metierControlleur.afficherLingne(nomLinge,annee);
       
       projet.setLignebudgetaire(lignebudgetaire);
    metierControlleur.ajouterProjet(projet);
    projet.setIdProjet(0);
     projet.setNomProjet("");
     projet.setMontantAlloue(0);
     projet.setDateRealisation(null);
    return "/listerProjet?faces-redirect=true";
}
//    public List<Integer> annees(){
//        List<Integer> list=new ArrayList<>();
//        for (Iterator<Lignebudgetaire> iterator = lignes.iterator(); iterator.hasNext();) {
//            Lignebudgetaire next = iterator.next();
//            list.add(next.getAnneeBudgetaire());
//            
//        }
//        return list;
//    }
    
    
     public String afficherLigneProjetBean(Projet projet) {
        lignebudgetaire = metierControlleur.consultLignebudgetaire(projet.getLignebudgetaire().getIdBudget());
 
        return lignebudgetaire.getNomLigne();
    }
    
 public String supprimerProjetBean(Projet projet){
     metierControlleur.supprimerProjet(projet);
     return "/listerProjet?faces-redirect=true";
 }
 public String modifierProjetBean(){
     projet.setLignebudgetaire(lignebudgetaire);
     metierControlleur.modifierProjet(projet);
   return "/listerProjet?faces-redirect=true";
 }
 
 public String editerProjetBean(Projet projet){
     this.projet=projet;
     this.lignebudgetaire=metierControlleur.
             consultLignebudgetaire(projet.getLignebudgetaire().getIdBudget());
     return "/modifierProjet?faces-redirect=true";
 }
 
 public List<Projet> listerProjetBean(){
     List<Projet> listerprojet=metierControlleur.listerProjet();
     return listerprojet;
 }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<Projet> getListprojet() {
        return listprojet;
    }

    public void setListprojet(List<Projet> listprojet) {
        this.listprojet = listprojet;
    }

    public MetierControlleurImpl getMetierControlleur() {
        return metierControlleur;
    }

    public void setMetierControlleur(MetierControlleurImpl metierControlleur) {
        this.metierControlleur = metierControlleur;
    }

    public List<Lignebudgetaire> getLignes() {
        return lignes;
    }

    public Lignebudgetaire getLignebudgetaire() {
        return lignebudgetaire;
    }

    public void setLignebudgetaire(Lignebudgetaire lignebudgetaire) {
        this.lignebudgetaire = lignebudgetaire;
    }

    public String getNomLinge() {
        return nomLinge;
    }

    public void setNomLinge(String nomLinge) {
        this.nomLinge = nomLinge;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    
}
