package uh.ensak.eBoutique.metier;

import java.util.List;

import uh.ensak.eBoutique.entities.*;

public interface InternauteMetier {
	public List<Categorie> listCategories();
	public Categorie getCategorie(Long idCat);
	public List<Produit> listProduits();
	public List<Produit> produitsParMotCle(String mc);
	public List<Produit> produitsParcategorie(Long idCat);
	public Commande enregistrerCommande(Panier p, Client c);
	public List<Produit> produitsSelectionnes();
	public Produit getProduit(Long idProd);
}
