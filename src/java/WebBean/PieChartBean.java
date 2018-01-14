/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Dette;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class PieChartBean implements Serializable{
    
   private List<Dette> dettes;
   private MetierControlleur.MetierControlleurImpl metier;
   private Set<Integer> annees;
   private PieChartModel model1;
   private PieChartModel model2;
   private PieChartModel totalModel;

   Integer annee=2016;
    /**
     * Creates a new instance of BilanStatistiqueBean
     */
    public PieChartBean() {
        metier=new MetierControlleurImpl();
        dettes=metier.listerDette();
  
        annees=new HashSet<>(0);
        insererDonneemodel1();
        insererDonneemodel2();
    }
    private void insererDonneemodel1(){
        model1=new PieChartModel();
        double dette=0,montantPayer=0;
        for (Iterator<Dette> iterator = dettes.iterator(); iterator.hasNext();) {
            Dette det = iterator.next();
            dette=dette+det.getDetteBrute();
            montantPayer=montantPayer+det.getMontantPaye();
        }
       model1.set("Dette Municipalité", dette);
        model1.set("Montant total deja payé", montantPayer);
        model1.setTitle("Dette Municipalité");
        model1.setShowDataLabels(true);
        model1.setLegendPosition("w");
    }
    
    private void insererDonneemodel2(){
        model2=new PieChartModel();
        int Ndette=0,DettePayer=0;
        for (Iterator<Dette> iterator = dettes.iterator(); iterator.hasNext();) {
            Dette det = iterator.next();
            if(det.getEtat().equals("Non Payé")){
                Ndette++;
            }else{
                DettePayer++;
            }
        }
       model2.set("Dette Nom Payé", Ndette);
        model2.set("Dette deja payé", DettePayer);
        model2.setTitle("Dette Municipalité");
        model2.setShowDataLabels(true);
        model2.setLegendPosition("e");
    }
    
    private void insererDonneeTotalModel(){
        totalModel=new PieChartModel();
        double dette=0,montantPayer=0;
        for (Iterator<Dette> iterator = dettes.iterator(); iterator.hasNext();) {
            Dette det = iterator.next();
            dette=dette+det.getDetteBrute();
            montantPayer=montantPayer+det.getMontantPaye();
        }
      
        model1.setTitle("Bilan "+annee+"Municipalité");
        model1.setShowDataLabels(true);
        model1.setLegendPosition("w");
    }
    
    public Set<Integer> recupererAnnee(){
        for (Iterator<Dette> iterator = dettes.iterator(); iterator.hasNext();) {
            Dette dette = iterator.next();
            annees.add(dette.getDateCreation().getYear());
        }
        return annees;
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

    public Set<Integer> getAnnees() {
        return annees;
    }

    public void setAnnees(Set<Integer> annees) {
        this.annees = annees;
    }

    public PieChartModel getModel() {
        return model1;
    }

    public void setModel(PieChartModel model) {
        this.model1 = model;
    }

    public PieChartModel getModel1() {
        return model1;
    }

    public void setModel1(PieChartModel model1) {
        this.model1 = model1;
    }

    public PieChartModel getModel2() {
        return model2;
    }

    public void setModel2(PieChartModel model2) {
        this.model2 = model2;
    }

    public PieChartModel getTotalModel() {
        return totalModel;
    }

    public void setTotalModel(PieChartModel totalModel) {
        this.totalModel = totalModel;
    }

  
    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }
    
}
