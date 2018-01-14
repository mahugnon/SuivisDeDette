/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Dette;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@ApplicationScoped
public class DateLineBean {
 private List<Dette> dettes;
 private Set<Integer> annees;
 MetierControlleur.MetierControlleurImpl metier;
    /**
     * Creates a new instance of DateLineBean
     */
    public DateLineBean() {
        metier=new MetierControlleurImpl();
        dettes=metier.listerDette();
    }

    public List<Dette> getDettes() {
        return dettes;
    }

    public void setDettes(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public Set<Integer> getAnnees() {
        return annees;
    }

    public void setAnnees(Set<Integer> annees) {
        this.annees = annees;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }
    
}
