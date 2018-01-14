/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author pc 
 */
public class Budget {
    private int annee;
    private long montant;
    private long dette;
    private long dettePayer;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }

    public Budget(int annee, long montant) {
        this.annee = annee;
        this.montant = montant;
    }

    public Budget() {
    }

    public long getDette() {
        return dette;
    }

    public void setDette(long dette) {
        this.dette = dette;
    }

    public Budget(int annee, long montant, long dette) {
        this.annee = annee;
        this.montant = montant;
        this.dette = dette;
    }

    public long getDettePayer() {
        return dettePayer;
    }

    public void setDettePayer(long dettePayer) {
        this.dettePayer = dettePayer;
    }
    
    
}
