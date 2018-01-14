/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Budget;
import Model.Dette;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.POST;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class BilanBean {
    private List<Dette> dettes;
   private  MetierControlleur.MetierControlleurImpl metier=new MetierControlleurImpl();
    private HorizontalBarChartModel  horizontalBarChartModel;
    private PieChartModel model;
    private Budget budget;

    /**
     * Creates a new instance of BilanBean
     */
    public BilanBean() {

    } 
    @PostConstruct
    public void init(){
        createHorizontaleBar();
    }
    
     private void createHorizontaleBar(){
         horizontalBarChartModel=new HorizontalBarChartModel();
         List<Budget> bud=bilan();
         ChartSeries detteSeries=new ChartSeries();
         detteSeries.setLabel("Dette Municipalité");
         
            ChartSeries payeSeries=new ChartSeries();
         payeSeries.setLabel("Dette Municipalité paye");
         for (Iterator<Budget> iterator = bud.iterator(); iterator.hasNext();) {
             Budget next = iterator.next();
             detteSeries.set(next.getAnnee(), next.getDette());
             payeSeries.set(next.getAnnee(), next.getDettePayer());
         }
         horizontalBarChartModel.addSeries(payeSeries);
         horizontalBarChartModel.addSeries(detteSeries);
         
         horizontalBarChartModel.setAnimate(true);
         horizontalBarChartModel.setStacked(true);
         horizontalBarChartModel.setTitle("Evolution de la dette");
         horizontalBarChartModel.setLegendPosition("e");
         
         
         Axis xAxis=horizontalBarChartModel.getAxis(AxisType.X);
         xAxis.setLabel("Evolution");
         
         Axis yAxis=horizontalBarChartModel.getAxis(AxisType.Y);
         yAxis.setLabel("Année");
         
     }
      
      
    
    public List<Budget> bilan(){
        long total=0;
        long payer=0;
        int annee;
        List<Budget> budgets=metier.budgetAnnee();
        dettes=metier.listerDette();
        for (Iterator<Budget> iterator = budgets.iterator(); iterator.hasNext();) {
            Budget next = iterator.next();
            annee=next.getAnnee();
            for (Iterator<Dette> iterator1 = dettes.iterator(); iterator1.hasNext();) {
                Dette dette = iterator1.next();
                if(dette.getAnnee()<=annee){
                    total=total+dette.getMontantRestant();
                    payer=payer+dette.getMontantPaye();
                } 
            }
            next.setDette(total);
            next.setDettePayer(payer);
            total=0;
            payer=0;     
        }
        return budgets;
    }
    
    //recuperer le bilan courant
    public String recupererBilan(Budget b){
        insererDonneemodel(b);
//        insererDonneemodel(b);
       // budget=new Budget();
        return"/graph?faces-redirect=true";
        
    }
    
    //Insertion de de donnée dans le model du Pie chart
     private void insererDonneemodel(Budget budget){
        model=new PieChartModel();
       
       model.set("Dette Municipalité", budget.getDette());
       model.set("Budget Municipalité "+budget.getAnnee(), budget.getMontant());
        model.set("Montant total deja payé", budget.getDettePayer());
        model.setTitle("Bilan annuel");
        model.setShowDataLabels(true);
        model.setLegendPosition("w");
        
    }
    
     //retour
     public String retour(){
         budget=null;
         return"listeDetteAnneeBudgetaire?faces-redirect=true";
     }
    

    public List<Dette> getDettes() {
        return dettes;
    }

    public void setDettes(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

    public HorizontalBarChartModel getHorizontalBarChartModel() {
        return horizontalBarChartModel;
    }

    public void setHorizontalBarChartModel(HorizontalBarChartModel horizontalBarChartModel) {
        this.horizontalBarChartModel = horizontalBarChartModel;
    }

    public PieChartModel getModel() {
        return model;
    }

    public void setModel(PieChartModel model) {
        this.model = model;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
  
    
}
