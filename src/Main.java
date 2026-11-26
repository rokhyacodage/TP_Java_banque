import DAO.CompteDAO;
import DAO.ProprietaireDAO;
import modele.Proprietaire;
import modele.Compte;

import java.util.List;
//import modele.Proprietaire;
//
//void main() {
//    public static void main(String[]){
//
//    ProprietaireDAO pDAO =new ProprietaireDAO();
//    Proprietaire p =new Proprietaire("Faye","Absa","Mbao");
//    pDAO.ajouter(p);
//    Proprietaire p = pDAO.rechercherProprietaire(3);
//    if (p!=null){
//      pDAO.supprimer(p);
//    }

//    Proprietaire p = pDAO.rechercherProprietaire(3);
//    p.setAdresse("Keur Ndiaye Lo");
//    pDAO.modifier(p);

//    Proprietaire p =new Proprietaire("Faye","Absa","Mbao");
//    pDAO.ajouter(p);

//    Proprietaire p = pDAO.rechercherProprietaire(3);
//    if (p !=null){
//        System.out.println(p);
//    }
//    ArrayList<Proprietaire>proprietaires= pDAO.getProprietaires();
//
//    for (Proprietaire p : proprietaires){
//        System.out.println(p);
//    }
//}
public class Main {

    public static void main(String[] args) {
        ProprietaireDAO pdao = new ProprietaireDAO();
        CompteDAO cdao = new CompteDAO();

        // 1. On ajoute le proprio 1 s'il n'existe pas
        try {
            pdao.ajouter(new Proprietaire(1, "Diop", "Moussa", "Rufisque"));
        } catch (Exception e) {
            System.out.println("Proprio 1 existe déjà, on continue");
        }

        // 2. On ajoute les comptes
        cdao.ajouter(new Compte("C001", 50000, 1));
        cdao.ajouter(new Compte("C002", 75000, 1));

        // 3. On affiche
        System.out.println("--- LISTE DES COMPTES ---");
        for (Compte c : cdao.listerTous()) {
            System.out.println(c.getNumero() + " | " + c.getSolde() + " | Proprio: " + c.getProprietaire_id());
        }
    }
}




