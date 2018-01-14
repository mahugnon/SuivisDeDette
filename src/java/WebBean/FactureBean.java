/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Budget;
import Model.Facture;
import Model.FactureAnnee;
import Model.Projet;
import Model.Societe;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class FactureBean implements Serializable {

    private MetierControlleurImpl metierControlleur = new MetierControlleurImpl();
    private List<Projet> listprojet;
    private List<Societe> listsociete;
    private Facture facture = new Facture();
    private Societe societe;
    private Projet projet;
    private String nomProjet;
    private String nomSociete;
    private List<FactureAnnee> factureAnnees;
   private HorizontalBarChartModel horizontalBarChartModel;

    /**
     * Creates a new instance of FactureBean
     */
    public FactureBean() {
        facture = new Facture();
        listprojet = metierControlleur.listerProjet();
        listsociete = metierControlleur.afficherSociete("");
    }
    @PostConstruct
    public void init(){
     createHorizontaleBar();   
    }
    
      private void createHorizontaleBar(){
         horizontalBarChartModel=new HorizontalBarChartModel();
         List<FactureAnnee> factAnne=factureAnne();
         ChartSeries budgetSerie=new ChartSeries();
         budgetSerie.setLabel("Budgets Municipalité");
         
            ChartSeries factureSeries=new ChartSeries();
         factureSeries.setLabel("Total des factures par année");
         for (Iterator<FactureAnnee> iterator = factAnne.iterator(); iterator.hasNext();) {
             FactureAnnee next = iterator.next();
             budgetSerie.set(next.getAnnee(), next.getBudget());
             factureSeries.set(next.getAnnee(), next.getMontantTotal());
         }
         horizontalBarChartModel.addSeries(factureSeries);
        horizontalBarChartModel.addSeries(budgetSerie);
         
         
         horizontalBarChartModel.setAnimate(true);
      //   horizontalBarChartModel.setStacked(true);
         horizontalBarChartModel.setTitle("Total des par année");
         horizontalBarChartModel.setLegendPosition("e");
         
         
         Axis xAxis=horizontalBarChartModel.getAxis(AxisType.X);
         xAxis.setLabel("Total des factures");
         
         Axis yAxis=horizontalBarChartModel.getAxis(AxisType.Y);
         yAxis.setLabel("Années");
         
     }
    
    
    

    public String ajouterFactureBean() {
        facture.setProjet(metierControlleur.afficherProjet(nomProjet));
        facture.setSociete(metierControlleur.consulterSociete(nomSociete));
        metierControlleur.ajouterFacture(facture);
        facture = new Facture();
        return "/listerFacture?faces-redirect=true";
    }

    public List<Facture> listerFactureBean() {
        List<Facture> listFacture = metierControlleur.listerFactureSociete("");
        return listFacture;
    }

    public String detailFatureBean(Facture facture) {
        this.facture = facture;
        return "/detailFacture?faces-redirect=true";
    }

    public String afficherSocieteFactureBean(Facture facture) {
        societe = metierControlleur.getSocieteById(facture.getSociete().getIdSociete());
        return societe.getNomSociete();
    }

    public String afficherProjetFactureBean(Facture facture) {
        projet = metierControlleur.getProjetById(facture.getProjet().getIdProjet());
        return projet.getNomProjet();
    }

    public String enregistrerFactureBean() {
        affecterValeur();
        metierControlleur.modifierFacture(facture);
        facture = new Facture();
        return "/listerFacture?faces-redirect=true";
    }

    public void affecterValeur() {
        this.projet = metierControlleur.afficherProjet(nomProjet);
        facture.setProjet(projet);
        this.societe = metierControlleur.consulterSociete(nomSociete);
        facture.setSociete(societe);

    }

    public String editerFactureBean(Facture facture) {
        this.facture = facture;
        return "/modifierFacture?faces-redirect=true";
    }

    public String supprimerFactureBean(Facture facture) {
        metierControlleur.supprimerFacture(facture);
        return "/listerFacture?faces-redirect=true";
    }

    //Facture total par année
    public List<FactureAnnee> factureAnne() {
        List<Budget> budgets = metierControlleur.budgetAnnee();
        factureAnnees = metierControlleur.listerFactureAnnee();
        for (Iterator<FactureAnnee> iterator = factureAnnees.iterator(); iterator.hasNext();) {
            FactureAnnee next = iterator.next();
            int annee = next.getAnnee();
            long budget = 0;
            for (Iterator<Budget> iterator1 = budgets.iterator(); iterator1.hasNext();) {
                Budget next1 = iterator1.next();
                if (next1.getAnnee() == annee) {
                    budget = next1.getMontant();
                }
            }
            next.setBudget(budget);

        }
        return factureAnnees;
    }

    public MetierControlleurImpl getMetierControlleur() {
        return metierControlleur;
    }
    
    public void setMetierControlleur(MetierControlleurImpl metierControlleur) {
        this.metierControlleur = metierControlleur;
    }

    public List<Projet> getListprojet() {
        return listprojet;
    }

    public void setListprojet(List<Projet> listprojet) {
        this.listprojet = listprojet;
    }

    public List<Societe> getListsociete() {
        return listsociete;
    }

    public void setListsociete(List<Societe> listsociete) {
        this.listsociete = listsociete;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public List<FactureAnnee> getFactureAnnees() {
        return factureAnnees;
    }

    public void setFactureAnnees(List<FactureAnnee> factureAnnees) {
        this.factureAnnees = factureAnnees;
    }

    public HorizontalBarChartModel getHorizontalBarChartModel() {
        return horizontalBarChartModel;
    }

    public void setHorizontalBarChartModel(HorizontalBarChartModel horizontalBarChartModel) {
        this.horizontalBarChartModel = horizontalBarChartModel;
    }

}
