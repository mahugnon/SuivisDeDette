/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebBean;

import MetierControlleur.MetierControlleurImpl;
import Model.Service;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pc
 */
@ManagedBean
@RequestScoped
public class ServiceBean {

    private Service service;
    List<Service> services;
    String message="";

    MetierControlleur.MetierControlleurImpl metier = new MetierControlleurImpl();

    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
        this.service = new Service();
        this.services = metier.listerService();
    }

    public String verif() {
        String role = "";
        for (Service next : services) {
            if (next.getRole().equals(service.getRole()) && (next.getMotDePasse() == null ? service.getMotDePasse() == null : next.getMotDePasse().equals(service.getMotDePasse()))) {
                role = next.getRole();

            }
        }
        if (role.equals("Financier")) {
            return "/accueil?faces-redirect=true";
        } else {
            message="les donnée utilisateur entrées sont fausses veillez réessayé";
            return "/login?faces-redirect=true";
            
        }

    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public MetierControlleurImpl getMetier() {
        return metier;
    }

    public void setMetier(MetierControlleurImpl metier) {
        this.metier = metier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
