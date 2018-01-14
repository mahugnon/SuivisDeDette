/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class ListBean {
private List<String> listfacture;

@PostConstruct
 public void init(){
     listfacture=new ArrayList<String>();
     listfacture.add("Lister Factures");
     listfacture.add("Ajouter Facture");
    
 }
    public ListBean() {
    }

    public List<String> getListfacture() {
        return listfacture;
    }

    public void setListfacture(List<String> listfacture) {
        this.listfacture = listfacture;
    }
    
}
