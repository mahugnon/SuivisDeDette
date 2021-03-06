package Model;
// Generated 16 mai 2016 23:18:10 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Convention generated by hbm2java
 */
public class Convention  implements java.io.Serializable {


     private Integer idConvention;
     private Caissedepret caissedepret;
     private Dette dette;
     private Societe societe;
     private Date dateAjout;
     private String type;
     private Set echeances = new HashSet(0);

    public Convention() {
    }

	
    public Convention(Date dateAjout, String type) {
        this.dateAjout = dateAjout;
        this.type = type;
    }
    public Convention(Caissedepret caissedepret, Dette dette, Societe societe, Date dateAjout, String type, Set echeances) {
       this.caissedepret = caissedepret;
       this.dette = dette;
       this.societe = societe;
       this.dateAjout = dateAjout;
       this.type = type;
       this.echeances = echeances;
    }
   
    public Integer getIdConvention() {
        return this.idConvention;
    }
    
    public void setIdConvention(Integer idConvention) {
        this.idConvention = idConvention;
    }
    public Caissedepret getCaissedepret() {
        return this.caissedepret;
    }
    
    public void setCaissedepret(Caissedepret caissedepret) {
        this.caissedepret = caissedepret;
    }
    public Dette getDette() {
        return this.dette;
    }
    
    public void setDette(Dette dette) {
        this.dette = dette;
    }
    public Societe getSociete() {
        return this.societe;
    }
    
    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    public Date getDateAjout() {
        return this.dateAjout;
    }
    
    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Set getEcheances() {
        return this.echeances;
    }
    
    public void setEcheances(Set echeances) {
        this.echeances = echeances;
    }




}


