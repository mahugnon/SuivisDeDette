/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.annotation.PostConstruct;

/**
 *
 * @author pc
 */
public class FactureAnnee {
    private int annee;
    private long montantTotal;
    private long budget;

    public FactureAnnee() {
    }
    
   

    public FactureAnnee(int annee, long montantTotal,long budget) {
        this.annee = annee;
        this.montantTotal = montantTotal;
        this.budget=budget;
    }

    
    
    
    
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public long getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(long montantTotal) {
        this.montantTotal = montantTotal;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
    
    
}
