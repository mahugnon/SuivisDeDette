package Model;
// Generated 16 mai 2016 23:18:10 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Lignebudgetaire generated by hbm2java
 */
public class Lignebudgetaire  implements java.io.Serializable {


     private Integer idBudget;
     private String nomLigne;
     private int anneeBudgetaire;
     private long montantBudgetaire;
     private Set projets = new HashSet(0);

    public Lignebudgetaire() {
    }

	
    public Lignebudgetaire(String nomLigne, int anneeBudgetaire, long montantBudgetaire) {
        this.nomLigne = nomLigne;
        this.anneeBudgetaire = anneeBudgetaire;
        this.montantBudgetaire = montantBudgetaire;
    }
    public Lignebudgetaire(String nomLigne, int anneeBudgetaire, long montantBudgetaire, Set projets) {
       this.nomLigne = nomLigne;
       this.anneeBudgetaire = anneeBudgetaire;
       this.montantBudgetaire = montantBudgetaire;
       this.projets = projets;
    }
   
    public Integer getIdBudget() {
        return this.idBudget;
    }
    
    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }
    public String getNomLigne() {
        return this.nomLigne;
    }
    
    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }
    public int getAnneeBudgetaire() {
        return this.anneeBudgetaire;
    }
    
    public void setAnneeBudgetaire(int anneeBudgetaire) {
        this.anneeBudgetaire = anneeBudgetaire;
    }
    public long getMontantBudgetaire() {
        return this.montantBudgetaire;
    }
    
    public void setMontantBudgetaire(long montantBudgetaire) {
        this.montantBudgetaire = montantBudgetaire;
    }
    public Set getProjets() {
        return this.projets;
    }
    
    public void setProjets(Set projets) {
        this.projets = projets;
    }




}


