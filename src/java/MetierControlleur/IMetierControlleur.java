/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetierControlleur;

import Model.Budget;
import Model.Caissedepret;
import Model.Convention;
import Model.Dette;
import Model.Echeance;
import Model.Facture;
import Model.FactureAnnee;
import Model.Lignebudgetaire;
import Model.Projet;
import Model.Service;
import Model.Societe;
import Model.Tranche;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Honorem
 */
public interface IMetierControlleur {

    public void ajouterFacture(Facture facture);//ça y est
    //Methode  relative aux Factures

    public Facture afficherFacture(Integer idFacture);//ça y est

    public void modifierFacture(Facture facture);//ça y est

    public void supprimerFacture(Facture facture);//ça y est

    public List<Facture> listerFactureSociete(String nomSocite);

    public List<Facture> listerFactureProjet(Integer idProjet);

   
    public List<FactureAnnee> listerFactureAnnee();

    //Methode relative aux Sociétés
    public void ajouterSociete(Societe societe);//ça y est

    public Societe consulterSociete(String nomSociete);//ça y est

    public List<Societe> afficherSociete(String motCle);//ça y est

    public List<Societe> listerSocieteProjet(Integer idProjet);

    public void modifierSociete(Societe societe);//ça y est

    public void supprimerSociete(Societe societe);//ça y est

    public Societe getSocieteById(Integer idSociete);//ça y est

    //Methode relatives aux dettes
    public Dette afficherDetteSociete(Societe societe);//ça y est

    public Dette afficherDetteCaisse(Integer idCaissePret);

    public List<Dette> afficherDetteCaisse();

    public double afficherTotalDette();

    public void mettreDetteAjour(Dette dette);

    public List<Dette> listerDette();

    public List<Dette> listerDetteLigne(Integer idLigneBudgetaire);

    //Methode relative aux projet
    public void ajouterProjet(Projet projet);

    public Projet afficherProjet(String nom);

    public List<Projet> listerProjet();

    public List<Projet> listerProjetLigneBudgetaire(Integer idLigne);

    public void modifierProjet(Projet projet);

    public Projet getProjetById(Integer idProjet);//ça y est

    public void supprimerProjet(Projet projet);

    //Methode relative à la caisse de prêt
    public void ajouterPret(Caissedepret caissedepret);

    public void supprimerPret(Caissedepret caisse);

    public void ModifierPret(Caissedepret caissedepret);

    public Caissedepret afficherPret(Projet projet);
public Caissedepret afficherCaisseDePret(Integer id);
    public List<Caissedepret> listerPret();

    //Methode relative aux tranches
    public void ajouterTranche(Tranche tranche);

    public void modifierTranche(Tranche tranche);

    public void supprimerTranche(Tranche tranche);

    public List<Tranche> ListerTranche(Integer idCaisseDePret);

    public Tranche afficherTranche(Integer idTranche);

    //Methode relative au convention

    public void ajouterConvention(Convention convention);

    public List<Convention> listerConvention();

    public void ModifierConvention(Convention convention);

    public void supprimerConvention(Convention convention);

    public Convention afficherConvention(Integer idConvention);
    public Convention afficherConventionSociete(Societe Societe);
 public Convention afficherConventionPret(Caissedepret caissedepret);
    //Methode relative au Plan de payement

    public void ajouterEcheance(Echeance echeance);
    public List<Echeance> listerEcheance(Convention convention);
    
    public List<Echeance> listEcheance();
   

    public void modifierEcheance(Echeance echeance);

    public void supprimerEcheance(Echeance echeance);

    //Methode relative a la ligne budgetaire
    public void ajoutLignebudgetaire(Lignebudgetaire lignebudgetaire);

    public void modifierLigne(Lignebudgetaire lignebudgetaire);

    public Lignebudgetaire consultLignebudgetaire(Integer idLigne);

    public Lignebudgetaire afficherLingne(String nom,int annee);

    public List<Lignebudgetaire> ListerLignes();
  
    public void supprimerLigne(Lignebudgetaire ligne);

    //Methode relative au service

    public void ajouterService(Service service);

    public void modifierService(Service service);

    public void supprimerService(Service service);
    

    public List<Service> listerService();
     public List<Budget> budgetAnnee();

}
