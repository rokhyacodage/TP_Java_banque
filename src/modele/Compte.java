package modele;

public class Compte {
    private String numero;
    private double solde;
    private int proprietaire_id;
    public Compte(String numero,double solde,int proprietaire_id){
        this.numero =numero;
        this.solde =solde;
        this.proprietaire_id=proprietaire_id;
    }
    public String getNumero(){
        return  numero;
    }
    public  double getSolde(){
        return solde;
    }
    public int getProprietaire_id(){
        return proprietaire_id;
    }

}
