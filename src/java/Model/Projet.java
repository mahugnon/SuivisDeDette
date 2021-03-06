package Model;
// Generated 16 mai 2016 23:18:10 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Projet generated by hbm2java
 */
public class Projet  implements java.io.Serializable {


     private Integer idProjet;
     private Lignebudgetaire lignebudgetaire;
     private String nomProjet;
     private Date dateRealisation;
     private long montantAlloue;
     private double evolution;
     private Set factures = new HashSet(0);
     private Set caissedeprets = new HashSet(0);

    public Projet() {
    }

	
    public Projet(Lignebudgetaire lignebudgetaire, String nomProjet, Date dateRealisation, long montantAlloue, double evolution) {
        this.lignebudgetaire = lignebudgetaire;
        this.nomProjet = nomProjet;
        this.dateRealisation = dateRealisation;
        this.montantAlloue = montantAlloue;
        this.evolution = evolution;
    }
    public Projet(Lignebudgetaire lignebudgetaire, String nomProjet, Date dateRealisation, long montantAlloue, double evolution, Set factures, Set caissedeprets) {
       this.lignebudgetaire = lignebudgetaire;
       this.nomProjet = nomProjet;
       this.dateRealisation = dateRealisation;
       this.montantAlloue = montantAlloue;
       this.evolution = evolution;
       this.factures = factures;
       this.caissedeprets = caissedeprets;
    }
   
    public Integer getIdProjet() {
        return this.idProjet;
    }
    
    public void setIdProjet(Integer idProjet) {
        this.idProjet = idProjet;
    }
    public Lignebudgetaire getLignebudgetaire() {
        return this.lignebudgetaire;
    }
    
    public void setLignebudgetaire(Lignebudgetaire lignebudgetaire) {
        this.lignebudgetaire = lignebudgetaire;
    }
    public String getNomProjet() {
        return this.nomProjet;
    }
    
    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }
    public Date getDateRealisation() {
        return this.dateRealisation;
    }
    
    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }
    public long getMontantAlloue() {
        return this.montantAlloue;
    }
    
    public void setMontantAlloue(long montantAlloue) {
        this.montantAlloue = montantAlloue;
    }
    public double getEvolution() {
        return this.evolution;
    }
    
    public void setEvolution(double evolution) {
        this.evolution = evolution;
    }
    public Set getFactures() {
        return this.factures;
    }
    
    public void setFactures(Set factures) {
        this.factures = factures;
    }
    public Set getCaissedeprets() {
        return this.caissedeprets;
    }
    
    public void setCaissedeprets(Set caissedeprets) {
        this.caissedeprets = caissedeprets;
    }




}


