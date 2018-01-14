package Model;
// Generated 16 mai 2016 23:18:10 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Echeance generated by hbm2java
 */
public class Echeance  implements java.io.Serializable {


     private Integer idEcheance;
     private Convention convention;
     private Date dateCreation;
     private Date datePaiement;
     private long montantEcheance;
     private String etatEcheance;
     private String couleur;

    public Echeance() {
    }

	
    public Echeance(Convention convention, Date dateCreation, Date datePaiement, long montantEcheance, String etatEcheance) {
        this.convention = convention;
        this.dateCreation = dateCreation;
        this.datePaiement = datePaiement;
        this.montantEcheance = montantEcheance;
        this.etatEcheance = etatEcheance;
    }
    public Echeance(Convention convention, Date dateCreation, Date datePaiement, long montantEcheance, String etatEcheance, String couleur) {
       this.convention = convention;
       this.dateCreation = dateCreation;
       this.datePaiement = datePaiement;
       this.montantEcheance = montantEcheance;
       this.etatEcheance = etatEcheance;
       this.couleur = couleur;
    }
   
    public Integer getIdEcheance() {
        return this.idEcheance;
    }
    
    public void setIdEcheance(Integer idEcheance) {
        this.idEcheance = idEcheance;
    }
    public Convention getConvention() {
        return this.convention;
    }
    
    public void setConvention(Convention convention) {
        this.convention = convention;
    }
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Date getDatePaiement() {
        return this.datePaiement;
    }
    
    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }
    public long getMontantEcheance() {
        return this.montantEcheance;
    }
    
    public void setMontantEcheance(long montantEcheance) {
        this.montantEcheance = montantEcheance;
    }
    public String getEtatEcheance() {
        return this.etatEcheance;
    }
    
    public void setEtatEcheance(String etatEcheance) {
        this.etatEcheance = etatEcheance;
    }
    public String getCouleur() {
        return this.couleur;
    }
    
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }




}

