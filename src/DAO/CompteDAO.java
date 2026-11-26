package DAO;

import config.Connexion;
import modele.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {
    public void ajouter(Compte c) {
        try {
            Connection cn = Connexion.ouvrir();
            String sql = "INSERT INTO compte (numero, solde, proprietaire_id) VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getNumero());
            ps.setDouble(2, c.getSolde());
            ps.setInt(3, c.getProprietaire_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur ajouter: " + e.getMessage());
        }
    }

    public List<Compte> listerTous() {
        List<Compte> liste = new ArrayList<>();
        try {
            Connection cn = Connexion.ouvrir();
            String sql = "SELECT numero, solde, proprietaire_id FROM compte";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compte c = new Compte(
                        rs.getString("numero"),
                        rs.getDouble("solde"),
                        rs.getInt("proprietaire_id")
                );
                liste.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erreur listerTous: " + e.getMessage());
        }
        return liste;
    }

    public List<Compte> listerParProprietaire(int proprietaireId) {
        List<Compte> liste = new ArrayList<>();
        try {
            Connection cn = Connexion.ouvrir();
            String sql = "SELECT numero, solde, proprietaire_id FROM compte WHERE proprietaire_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, proprietaireId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compte c = new Compte(
                        rs.getString("numero"),
                        rs.getDouble("solde"),
                        rs.getInt("proprietaire_id")
                );
                liste.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erreur listerParProprietaire: " + e.getMessage());
        }
        return liste;
    }

    // CORRIGE : c'était chercherParNumrro
    public Compte chercherParNumero(String numero) {
        Compte c = null;
        try {
            Connection cn = Connexion.ouvrir();
            String sql = "SELECT numero, solde, proprietaire_id FROM compte WHERE numero = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Compte(
                        rs.getString("numero"),
                        rs.getDouble("solde"),
                        rs.getInt("proprietaire_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur chercherParNumero: " + e.getMessage());
        }
        return c;
    }

    public void modifierSolde(String numero, double nouveauSolde) {
        try {
            Connection cn = Connexion.ouvrir();
            String sql = "UPDATE compte SET solde = ? WHERE numero = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setDouble(1, nouveauSolde);
            ps.setString(2, numero);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur modifierSolde: " + e.getMessage());
        }
    }

    public void supprimer(String numero) {
        try {
            Connection cn = Connexion.ouvrir();
            String sql = "DELETE FROM compte WHERE numero = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, numero);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur supprimer: " + e.getMessage());
        }
    }

}
