package DAO;

import config.Connexion;
import modele.Proprietaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProprietaireDAO {
    public ArrayList<Proprietaire> getProprietaires() {
        ArrayList<Proprietaire> proprietaires = new ArrayList<>();
        String sql = "select id, nom,prenom,adresse from proprietaire =?";

        try (Connection cn = Connexion.ouvrir();
             PreparedStatement ps = cn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getNString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getNString("adresse");
                Proprietaire p = new Proprietaire(id, nom, prenom, adresse);
                proprietaires.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return proprietaires;

    }

    public Proprietaire rechercherProprietaire(int id) {

        String sql = "select id,nom,prenom,adresse from proprietaire where id =?";

        try (Connection cn = Connexion.ouvrir();
             PreparedStatement ps = cn.prepareStatement(sql);){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getNString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getNString("adresse");
                Proprietaire p = new Proprietaire(id, nom, prenom, adresse);
                return p;

            }

        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return null;

    }
    public void ajouter(Proprietaire p){
        String sql ="INSERT INTO proprietaire(nom ,prenom,adresse)VALUES(?,?,?)";
        try{
                 Connection cn =Connexion.ouvrir();
             PreparedStatement ps =cn.prepareStatement(sql);
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getAdresse());
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Erreur :"+ex.getMessage());
        }
    }

    public void modifier(Proprietaire p){
        String sql ="UPDATE proprietaire SET nom=?,prenom=?,adresse=?WHERE id=?";
        try{
            Connection cn =Connexion.ouvrir();
            PreparedStatement ps =cn.prepareStatement(sql);
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getAdresse());
            ps.setInt(4,p.getId());
            ps.executeUpdate();

        }catch (SQLException ex) {
            System.out.println("Erreur:"+ex.getMessage());
        }
    }
    public void supprimer(Proprietaire p){
        String sql ="DELETE FROM proprietaire WHERE id=?";
        try{
            Connection cn =Connexion.ouvrir();
            PreparedStatement ps =cn.prepareStatement(sql);
            ps.setInt(1,p.getId());
            ps.executeUpdate();

        }catch (SQLException ex) {
            System.out.println("Erreur:"+ex.getMessage());
        }
    }


}