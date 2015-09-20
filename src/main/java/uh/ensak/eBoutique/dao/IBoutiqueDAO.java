package uh.ensak.eBoutique.dao;

import java.util.List;

import uh.ensak.eBoutique.entities.*;

public interface IBoutiqueDAO {
	public Long ajouterCategorie(Categorie c);
	public List<Categorie> listCategories();
	public Categorie getCategorie(Long idCat);
	public void supprimerCategorie(Long idCat);
	public void modifierCategorie(Categorie c);
	
	public Long ajouterProduit(Produit p, Long idCat);
	public List<Produit> listProduits();
	public List<Produit> produitsParMotCle(String mc);
	public List<Produit> produitsParcategorie(Long idCat);
	public List<Produit> produitsSelectionnes();
	public Produit getProduit(Long idProd);
	public void supprimerProduit(Long idProd);
	public void modifierProduit(Produit p);
	
	public void ajouterUser(User u);
	public void attribuerRole(Role role, Long userId);
	public Commande enregistrerCommande(Panier p, Client c);

}
