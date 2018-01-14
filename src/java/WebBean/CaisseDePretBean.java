
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Model.Caissedepret;
import Model.Projet;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class CaisseDePretBean  implements Serializable{
    private Caissedepret caissedepret;
    private MetierControlleur.MetierControlleurImpl metier;
    private String nomProjet;
    private Projet projet;
   List<Projet> projets;
    private Caissedepret pret;
    /**
     * Creates a new instance of CaisseDePretBean
     */
    public CaisseDePretBean() {
        metier=new MetierControlleurImpl();
        caissedepret=new Caissedepret();
        projets=metier.listerProjet();
        projet=new Projet();
        pret=new Caissedepret();
        
        
    }
    
    public String recupererProjet(Projet p){
        this.projet=p;
        return"AjouterCaisseDePret?faces-redirect=true";
    }
    
    public String recupererPret(Projet projet){
        this.caissedepret=metier.afficherPret(projet);
        return"modifierPret?faces-redirect=true";
    }
    public void ajouterPret(){
        caissedepret.setProjet(metier.afficherProjet(nomProjet));
        metier.ajouterPret(caissedepret);
        caissedepret=new Caissedepret();
        
    }
    
      public String ajouterPret1(){
        caissedepret.setProjet(projet);
        metier.ajouterPret(caissedepret);
        caissedepret=new Caissedepret();
        projet=new Projet();
        
        return "detailProjet?faces-redirect=true";
    }
    
    public List<Caissedepret> ListerPret(){
        return metier.listerPret();
    }
    public void supprimerPret(){
        metier.supprimerPret(caissedepret);
    }
    public String modifierPret(){
        metier.ModifierPret(caissedepret);
      return "detailProjet?faces-redirect=true";  
    }
    
    public String recupereProjet(Caissedepret pret){
        this.projet=metier.getProjetById(pret.getProjet().getIdProjet());
        return projet.getNomProjet();
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public Caissedepret getCaissedepret() {
        return caissedepret;
    }

    public void setCaissedepret(Caissedepret caissedepret) {
        this.caissedepret = caissedepret;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Caissedepret getPret() {
        return pret;
    }

    public void setPret(Caissedepret pret) {
        this.pret = pret;
    }
    
}
