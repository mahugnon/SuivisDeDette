/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Lignebudgetaire;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class LigneBudgetaireBean {
private MetierControlleurImpl metierControlleur=new MetierControlleurImpl();
private List<Lignebudgetaire> listlignebudgetaire;
private Lignebudgetaire lignebudgetaire;
 private String nomLigne;
    /**
     * Creates a new instance of LigneBudgetaireBean
     */
    public LigneBudgetaireBean() {
    }
    public List<Lignebudgetaire> listerLigneBudgetaireBean(){
  List<Lignebudgetaire> lignebudgetaire=metierControlleur.ListerLignes();
       return lignebudgetaire;
    }
    
    public String ajouterLignebean(){
        metierControlleur.ajoutLignebudgetaire(lignebudgetaire);
        return"";
    }
    
    public String modifierLigneBean(Lignebudgetaire ligne){
        metierControlleur.modifierLigne(lignebudgetaire);
        return"";
    }
    
 public void supprimerLigneBean(Lignebudgetaire lignebudgetaire){
     metierControlleur.supprimerLigne(lignebudgetaire);
 }
    public List<Lignebudgetaire> getLignebudgetaire() {
        return listlignebudgetaire;
    }

    public void setLignebudgetaire(List<Lignebudgetaire> lignebudgetaire) {
        this.listlignebudgetaire = listlignebudgetaire;
    }

  
    public void setLignebudgetaire(Lignebudgetaire lignebudgetaire) {
        this.lignebudgetaire = lignebudgetaire;
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

       
}
