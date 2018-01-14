/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetierControlleur;

import Model.Caissedepret;
import Model.Convention;
import Model.Dette;
import Model.Echeance;
import Model.Lignebudgetaire;
import Model.Projet;
import Model.Societe;
import WebBean.ConventionBean;
import WebBean.ConventionPretBean;
import WebBean.DetailDetteBean;
import WebBean.DetailSocieteBean;
import WebBean.EcheanceBean;
import WebBean.NotificationBean;
import WebBean.ProjetBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Honore
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//
//      Calendar c=Calendar.getInstance();
//      c.set(2006, 02, 18);
//      long t=c.getTimeInMillis();
//      
//      
//        System.out.println(c.get(Calendar.DAY_OF_MONTH)+"\t"+c.get(Calendar.MONTH)+"\t"+c.get(Calendar.YEAR));
//         
//        c.set(Calendar.YEAR, 2015);
//        c.add(Calendar.YEAR, 4);
//               System.out.println(c.get(Calendar.DAY_OF_MONTH)+"\t"+c.get(Calendar.MONTH)+"\t"+c.get(Calendar.YEAR));
//      c.add(Calendar.DATE, -7);
//      
//              System.out.println(c.get(Calendar.DAY_OF_MONTH)+"\t"+c.get(Calendar.MONTH)+"\t"+c.get(Calendar.YEAR));
////------------------------------------------------------------------------------------------------------------------------------------
              NotificationBean bean=new NotificationBean();
              List<Dette> dettes=bean.getDepasse();
              for (Iterator<Dette> iterator = dettes.iterator(); iterator.hasNext();) {
            Dette next = iterator.next();
                  System.out.println(next.getMontantPaye());
            
        }
              List<Echeance> echeances=bean.getEcheancement();
              for (Iterator<Echeance> iterator = echeances.iterator(); iterator.hasNext();) {
            Echeance next = iterator.next();
              System.out.println(next.getDatePaiement());
        }

  
                
                
    }
    
    
     
    }

