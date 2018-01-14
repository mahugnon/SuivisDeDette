/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Honore
 */
@javax.faces.bean.ManagedBean
@RequestScoped
public class ManagedBean {

    /**
     * Creates a new instance of ManagedBean
     */
    public ManagedBean() {
    }

    public void showDialog() {

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentHeight", 340);
        options.put("height", 400);
        options.put("width", 700);

        RequestContext.getCurrentInstance().openDialog("AjouterSociete", options, null);

    }
}
