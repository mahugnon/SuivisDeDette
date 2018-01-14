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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Honorem
 */
public class MetierControlleurImpl implements IMetierControlleur {
    
    public static final SessionFactory factory = HibernateUtil.getSessionFactory();
    
    @Override
    public void ajouterFacture(Facture facture) {
        
        Session session = factory.openSession();
        Projet projet;
        Societe societe;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            societe = (Societe) session.get(Societe.class, facture.getSociete().getIdSociete());
            societe.getFactures().add(facture);
            session.update(societe);
            projet = (Projet) session.get(Projet.class, facture.getProjet().getIdProjet());
            projet.getFactures().add(facture);
            session.update(projet);

//Mise a jour automatique de la dette
            Query query = session.createQuery("from Dette d where d.societe.idSociete = :x");
            query.setParameter("x", societe.getIdSociete());
            Dette dette = (Dette) query.uniqueResult();
            dette.setDetteBrute(dette.getDetteBrute() + facture.getMontantFacture());
            session.update(dette);
            
            Calendar c = Calendar.getInstance();
            int annee = c.get(Calendar.YEAR);
            facture.setAnnee(annee);
            session.save(facture);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Echec transaction");
        } finally {
            session.close();
        }
        
    }
    
    @Override
    public Facture afficherFacture(Integer idFacture) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Facture facture = null;
        try {
            tx = session.beginTransaction();
            facture = (Facture) session.get(Facture.class, idFacture);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return facture;
        
    }
    
    @Override
    public void modifierFacture(Facture facture) {
        Session session = factory.openSession();
        Transaction tx = null;
        Societe societe;
        Facture facture1;
        try {
            tx = session.beginTransaction();
            societe = (Societe) session.get(Societe.class, facture.getSociete().getIdSociete());
            facture1 = (Facture) session.get(Facture.class, facture.getIdFacture());

            //mise a jour de la dette si le montant d'une facture est modifier 
            Query query = session.createQuery("from Dette d where d.societe.idSociete = :x");
            query.setParameter("x", societe.getIdSociete());
            Dette dette = (Dette) query.uniqueResult();
            dette.setDetteBrute(dette.getDetteBrute() - facture1.getMontantFacture());
            dette.setDetteBrute(dette.getDetteBrute() + facture.getMontantFacture());
            session.update(dette);
            facture1.setNumFacture(facture.getNumFacture());
            facture1.setDateArrive(facture.getDateArrive());
            facture1.setDatePaiement(facture.getDatePaiement());
            facture1.setDetail(facture.getDetail());
            facture1.setProjet((Projet) session.get(Projet.class, facture.getProjet().getIdProjet()));
            facture1.setSociete(societe);
            session.update(facture1);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerFacture(Facture facture) {
        Session session = factory.openSession();
        Transaction tx = null;
        Societe societe = null;
        try {
            tx = session.beginTransaction();

            //mise a jour de la dette
            societe = (Societe) session.get(Societe.class, facture.getSociete().getIdSociete());
            Query query = session.createQuery("from Dette d where d.societe.idSociete = :x");
            query.setParameter("x", societe.getIdSociete());
            Dette dette = (Dette) query.uniqueResult();
            dette.setDetteBrute(dette.getDetteBrute() - facture.getMontantFacture());
            session.update(dette);
            session.delete(facture);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    
     @Override
    public List<FactureAnnee> listerFactureAnnee() {
 Session session = factory.openSession();
        Transaction tx = null;
      List<Object> budgetAnnee = null;
      List<FactureAnnee> factureAnnees=new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query query=session.createQuery("SELECT f.annee,SUM(f.montantFacture)"
                    + " FROM Facture f"
                    + " GROUP BY f.annee "+"ORDER BY f.annee DESC");
            budgetAnnee=query.list();
            for (Iterator<Object> iterator = budgetAnnee.iterator(); iterator.hasNext();) {
                Object[] next = (Object[]) iterator.next();
                FactureAnnee factureAnnee=new FactureAnnee();
                factureAnnee.setAnnee((int) next[0]);
                factureAnnee.setMontantTotal((long) next[1]);
                factureAnnees.add(factureAnnee);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return factureAnnees;
    }
    @Override
    /*Pour reussir il faut tout d'abord faire l'enregistrement de la facture 
     en tenant compte de la relation bidirectionnelle et 
     puis comprendre que ici on manipule le diagramme 
     de classe IMPORTANT*/
    public List<Facture> listerFactureSociete(String nom) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Societe> societes = null;
        List<Facture> factures = new ArrayList<>();//on utilise le <<Set>> au cas où il y a affichage sans doublons....
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Societe s where s.nomSociete like :x");
            req.setParameter("x", "%" + nom + "%");
            societes = req.list();
            for (Iterator<Societe> iterator = societes.iterator(); iterator.hasNext();) {
                Societe societe = (Societe) iterator.next();
                Query query = session.createQuery("from Facture f "
                        + "where f.societe.idSociete =:x");
                query.setParameter("x", societe.getIdSociete());
                factures.addAll(query.list());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return factures;
    }
    
    @Override
    public List<Facture> listerFactureProjet(Integer idProjet) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Projet projet = null;
        List<Facture> factures = null;
        try {
            tx = session.beginTransaction();
            projet = (Projet) session.get(Projet.class, idProjet);
            Query req = session.createQuery("FROM Facture f WHERE f.projet.idProjet = :x ");
            req.setParameter("x", idProjet);
            factures = req.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return factures;
    }
    
    @Override
    public void ajouterSociete(Societe societe) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Dette dette;
        try {
            tx = session.beginTransaction();

            //ajout automatique de la dette lors de la creation d'une société
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int annee = c.get(Calendar.YEAR);
            dette = new Dette(0, 0, 0, "Non Payé", new Date(), annee);
            dette.setSociete(societe);
            // societe.getDettes().add(dette);
            session.save(dette);
            session.save(societe);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<Societe> afficherSociete(String motCle) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Societe> societes = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Societe s where s.nomSociete like :x");
            req.setParameter("x", "%" + motCle + "%");
            societes = req.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return societes;
    }
    
    @Override
    public List<Societe> listerSocieteProjet(Integer idProjet)//non complet
    {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Societe> societes = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Facture> factures = listerFactureProjet(idProjet);
            for (Iterator<Facture> iterator = factures.iterator(); iterator.hasNext();) {
                Facture next = iterator.next();
                societes.add((Societe) session.get(Societe.class, next.getSociete().getIdSociete()));
            }
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return societes;
    }
    
    @Override
    public void modifierSociete(Societe societe) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(societe);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerSociete(Societe societe) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        // Dette dette;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Facture f where f.societe.idSociete = :societe");
            q.setParameter("societe", societe.getIdSociete());
//            List<Facture> factures = q.list();
//            for (Iterator<Facture> iterator = factures.iterator(); iterator.hasNext();) {
//                Facture next = iterator.next();
//                session.delete(next);
//            }
//            Query query = session.createQuery("from Dette d where d.societe.idSociete = :x");
//            query.setParameter("x", societe.getIdSociete());
//            dette = (Dette) query.uniqueResult();
//            session.delete(dette);
            session.delete(societe);
            // System.out.println("suppression reussi!!!");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public Dette afficherDetteCaisse(Integer idCaissePret) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Dette dette = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Dette d where d.caissedepret.idCaissedepret = :x");
            req.setParameter("x", idCaissePret);
            dette = (Dette) req.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dette;
    }
    
    @Override
    public List<Dette> afficherDetteCaisse() {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Dette> dettes = new ArrayList<>();
        List<Caissedepret> caissedeprets;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Caissedepret");
            caissedeprets = req.list();
            for (Iterator<Caissedepret> iterator = caissedeprets.iterator(); iterator.hasNext();) {
                Caissedepret next = iterator.next();
                Query query = session.createQuery("from Dette d where d.caissedepret.idCaissedepret = :x");
                query.setParameter("x", next.getIdCaissedepret());
                dettes.addAll(query.list());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dettes;
    }
    
    @Override
    public double afficherTotalDette() {
        double TotalDette = 0;
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Dette> dettes = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Dette  d");
            dettes = req.list();
            tx.commit();
            for (Iterator<Dette> iterator = dettes.iterator(); iterator.hasNext();) {
                Dette next = iterator.next();
                TotalDette = TotalDette + next.getMontantRestant();
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return TotalDette;
    }
    
    @Override
    public void mettreDetteAjour(Dette dette) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            if (dette.getDetteBrute() == dette.getMontantPaye()) {
                dette.setColeur("green");
                dette.setEtat("Payer");
            }
            session.update(dette);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }
    
    @Override
    public List<Dette> listerDette() {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Dette> dettes = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Dette");
            dettes = req.list();
            tx.commit();
            
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dettes;
    }
//A REVOIR

    @Override
    public List<Dette> listerDetteLigne(Integer idLigneBudgetaire) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Dette> dettes = new ArrayList<>();
        List<Projet> projets = null;
        Set<Societe> societes = new HashSet<>();
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Projet p where p.lignebudgetaire.idBudget = :x");
            req.setParameter("x", idLigneBudgetaire);
            projets = req.list();
            for (Iterator<Projet> iterator = projets.iterator(); iterator.hasNext();) {
                Projet next = iterator.next();
                societes.addAll(listerSocieteProjet(next.getIdProjet()));
            }
            System.out.println("succes");
            for (Iterator<Societe> iterator = societes.iterator(); iterator.hasNext();) {
                Societe next = iterator.next();
                dettes.add(afficherDetteSociete(next));
            }
            
            tx.commit();
            
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dettes;
    }
    
    @Override
    public void ajouterProjet(Projet projet) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            session.save(projet);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }
    
    @Override
    public Projet afficherProjet(String nomProjet) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Projet projet = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Projet p where p.nomProjet = :x");
            req.setParameter("x", nomProjet);
            projet = (Projet) req.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return projet;
    }
    
    @Override
    public List<Projet> listerProjet() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Projet> projets = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Projet");
            projets = req.list();
            tx.commit();
            
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return projets;
    }
    
    @Override
    public List<Projet> listerProjetLigneBudgetaire(Integer idLigne) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Projet> projets = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Projet p where p.lignebudgetaire.idBudget = :x");
            req.setParameter("x", idLigne);
            projets = req.list();
            tx.commit();
            
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return projets;
    }
    
     @Override
    public List<Budget> budgetAnnee() {
          Session session = factory.openSession();
        Transaction tx = null;
      List<Object> budgetAnnee = null;
      List<Budget> budgets=new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query query=session.createQuery("SELECT l.anneeBudgetaire,SUM(l.montantBudgetaire)"
                    + " FROM Lignebudgetaire l"
                    + " GROUP BY l.anneeBudgetaire "+
            "ORDER BY l.anneeBudgetaire DESC");
            budgetAnnee=query.list();
            for (Iterator<Object> iterator = budgetAnnee.iterator(); iterator.hasNext();) {
                Object[] next = (Object[]) iterator.next();
                Budget budget=new Budget();
                budget.setAnnee((int) next[0]);
                budget.setMontant((long) next[1]);
                budgets.add(budget);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return budgets;
    }
    
    @Override
    public void modifierProjet(Projet projet) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(projet);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerProjet(Projet projet)//A retester
    {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Facture> factures;
        Set<Societe> societes = new HashSet<>();
        try {
            tx = session.beginTransaction();
            System.out.println("success chargement du projet");
            Query query = session.createQuery("from Facture f where f.projet.idProjet = :x ");
            query.setParameter("x", projet.getIdProjet());
            factures = query.list();
            System.out.println("succes chagement facture");
            if (factures != null) {
                for (Iterator<Facture> iterator = factures.iterator(); iterator.hasNext();) {
                    Facture facture = iterator.next();
                    societes.add(facture.getSociete());
                    // session.delete(facture);
                    supprimerFacture(facture);
                }
            }
            session.delete(projet);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void ajouterPret(Caissedepret caissedepret) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Projet projet;
        List<Societe> societes;
        Dette det;
        
        try {
            tx = session.beginTransaction();
            projet = caissedepret.getProjet();
            long dettebrute, dettePaye;
            dettebrute = caissedepret.getMontantDebloquer() + (caissedepret.getInteret() *( caissedepret.getMontantDebloquer()/100));
            dettePaye = 0;
            Calendar c = Calendar.getInstance();
            Date d = new Date();
            c.setTime(d);
            int annee = c.get(Calendar.YEAR);
            det = new Dette(dettebrute, 0, dettebrute, "Non Payé", d, annee);
            det.setCaissedepret(caissedepret);
         
            session.save(det);
            societes = listerSocieteProjet(projet.getIdProjet());
            for (Iterator<Societe> iterator = societes.iterator(); iterator.hasNext();) {
                Societe next = iterator.next();
                Dette dette = afficherDetteSociete(next);
                dette.setEtat("Payer");
                session.update(dette);
            }
            session.save(caissedepret);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public Dette afficherDetteSociete(Societe societe) {
        Session session = factory.openSession();
        Transaction tx = null;
        Dette dette = null;
        
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Dette d where d.societe.idSociete = :x");
            req.setParameter("x", societe.getIdSociete());
            dette = (Dette) req.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dette;
    }
    
    @Override
    public void ajoutLignebudgetaire(Lignebudgetaire lignebudgetaire) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Calendar c=Calendar.getInstance();
            int annee=c.get(Calendar.YEAR);
            lignebudgetaire.setAnneeBudgetaire(annee);
            session.save(lignebudgetaire);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void modifierLigne(Lignebudgetaire lignebudgetaire) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            session.update(lignebudgetaire);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public Lignebudgetaire consultLignebudgetaire(Integer idLigne) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Lignebudgetaire ligne = null;
        try {
            tx = session.beginTransaction();
            ligne = (Lignebudgetaire) session.get(Lignebudgetaire.class, idLigne);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ligne;
    }
    
    @Override
    public List<Lignebudgetaire> ListerLignes() {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Lignebudgetaire> lignes = null;
        try {
            tx = session.beginTransaction();
            lignes = session.createQuery("from Lignebudgetaire").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lignes;
    }
    
    @Override
    public void ajouterEcheance(Echeance echeance) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            echeance.setEtatEcheance("Non Payé");
            session.save(echeance);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void ModifierPret(Caissedepret caissedepret) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Dette dette = afficherDetteCaisse(caissedepret.getIdCaissedepret());
            long interet=(long) (caissedepret.getMontantDebloquer()*caissedepret.getInteret()*0.01);
            dette.setDetteBrute(caissedepret.getMontantDebloquer()
                    + interet);
            dette.setMontantRestant(dette.getDetteBrute()-dette.getMontantPaye());
            session.update(dette);
            session.update(caissedepret);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public Caissedepret afficherPret(Projet projet) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Caissedepret caissedepret = null;
        try {
            tx = session.beginTransaction();
            Query q=session.createQuery("from Caissedepret c where c.projet.idProjet = :x");
            q.setParameter("x", projet.getIdProjet());
            caissedepret=(Caissedepret) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return caissedepret;
    }
    
    @Override
    public List<Caissedepret> listerPret() {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Caissedepret> caissedeprets = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Caissedepret c");
            caissedeprets = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return caissedeprets;
    }
    
    @Override
    public void ajouterTranche(Tranche tranche) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Caissedepret caissedepret;
        try {
            tx = session.beginTransaction();
            caissedepret = tranche.getCaissedepret();
            
            
            session.save(tranche);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void modifierTranche(Tranche tranche) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(tranche);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerTranche(Tranche tranche) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(tranche);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<Tranche> ListerTranche(Integer idCasse) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Tranche> tranches = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Tranche t "
                    + "where t.caissedepret.idCaissedepret = :x");
            query.setParameter("x", idCasse);
            tranches = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tranches;
    }
    
    @Override
    public void ajouterConvention(Convention convention) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            session.save(convention);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void ModifierConvention(Convention convention) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(convention);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerConvention(Convention convention) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerEcheance(Echeance echeance) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(echeance);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerPret(Caissedepret caissedepret) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Tranche> tranches;
        Dette dette;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Tranche f where f.caissedepret.idCaissedepret = :x");
            query.setParameter("x", caissedepret.getIdCaissedepret());
            tranches = query.list();
            for (Iterator<Tranche> iterator = tranches.iterator(); iterator.hasNext();) {
                Tranche next = iterator.next();
                supprimerTranche(next);
            }
            Query query1 = session.createQuery("from Dette f where f.caissedepret.idCaissedepret = :x");
            query1.setParameter("x", caissedepret.getIdCaissedepret());
            dette = (Dette) query1.uniqueResult();
            session.delete(dette);
            session.delete(caissedepret);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }
    
    @Override
    public List<Convention> listerConvention() {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Convention> convention = null;
        try {
            tx = session.beginTransaction();
            convention = session.createQuery("from Convention").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return convention;
    }
    
    @Override
    public Convention afficherConvention(Integer idConvention) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        Convention convention = null;
        try {
            tx = session.beginTransaction();
            convention = (Convention) session.get(Convention.class, idConvention);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return convention;
    }
    
    @Override
    public Convention afficherConventionPret(Caissedepret caissedepret) {
 Session session = factory.openSession();
        Transaction tx = null;
        Convention convention = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Convention c where c.caissedepret.idCaissedepret = :x");
            query.setParameter("x", caissedepret.getIdCaissedepret());
            convention = (Convention) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return convention;
    }

    
    
    
    @Override
    public void modifierEcheance(Echeance echeance) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.update(echeance);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<Echeance> listerEcheance(Convention convention) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Echeance> echeances = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Echeance p where p.convention.idConvention = :x");
            query.setParameter("x", convention.getIdConvention());
            echeances = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return echeances;
    }
    
    @Override
    public void ajouterService(Service service) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(service);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void modifierService(Service service) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(service);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void supprimerService(Service service) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(service);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<Service> listerService() {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<Service> services = null;
        
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Service");
            services = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return services;
    }
    
   
    @Override
    public Societe consulterSociete(String nomSociete) {
        Session session = factory.openSession();
        Transaction tx = null;
        Societe societe = null;
        try {
            tx = session.beginTransaction();
            Query req = session.createQuery("from Societe s where s.nomSociete = :x");
            req.setParameter("x", nomSociete);
            societe = (Societe) req.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return societe;
    }
    
    @Override
    public Tranche afficherTranche(Integer idTranche) {
        Session session = factory.openSession();
        Transaction tx = null;
        Tranche tranche = null;
        try {
            tx = session.beginTransaction();
            tranche = (Tranche) session.get(Tranche.class, idTranche);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tranche;
    }
    
    @Override
    public Societe getSocieteById(Integer idSociete) {
        Session session = factory.openSession();
        Transaction tx = null;
        Societe societe = null;
        try {
            tx = session.beginTransaction();
            societe = (Societe) session.get(Societe.class, idSociete);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return societe;
    }
    
    @Override
    public Projet getProjetById(Integer idProjet) {
        Session session = factory.openSession();
        Transaction tx = null;
        Projet projet = null;
        try {
            tx = session.beginTransaction();
            projet = (Projet) session.get(Projet.class, idProjet);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return projet;
    }
    
    @Override
    public Lignebudgetaire afficherLingne(String nom,int annee) {
        Session session = factory.openSession();
        Transaction tx = null;
        Lignebudgetaire ligne = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Lignebudgetaire l where l.nomLigne = ? and l.anneeBudgetaire = ?");
            query.setString(0, nom);
            query.setParameter(1, annee);
            ligne =  (Lignebudgetaire) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ligne;
    }
    
    @Override
    public void supprimerLigne(Lignebudgetaire ligne) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (ligne.getProjets() == null) {
                session.delete(ligne);
            } else {
                System.out.println("Supprimer d'abord les projets du budget");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public Convention afficherConventionSociete(Societe societe) {
        Session session = factory.openSession();
        Transaction tx = null;
        Convention convention = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Convention c where c.societe.idSociete = :x");
            query.setParameter("x", societe.getIdSociete());
            convention = (Convention) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return convention;
    }

    @Override
    public List<Echeance> listEcheance() {
          
        Session session = factory.openSession();
        Transaction tx = null;
        List<Echeance> echeances = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Echeance ");
            echeances = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return echeances;
    }

    @Override
    public Caissedepret afficherCaisseDePret(Integer id) {
           Session session = factory.openSession();
        Transaction tx = null;
        Caissedepret caissedepret = null;
        try {
            tx = session.beginTransaction();
           caissedepret=(Caissedepret) session.get(Caissedepret.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return caissedepret;
    }

    
}
