package uh.ensak.eBoutique.metier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uh.ensak.eBoutique.dao.IBoutiqueDAO;
import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.entities.Client;
import uh.ensak.eBoutique.entities.Commande;
import uh.ensak.eBoutique.entities.Panier;
import uh.ensak.eBoutique.entities.Produit;
import uh.ensak.eBoutique.entities.Role;
import uh.ensak.eBoutique.entities.User;

@Transactional
public class BoutiqueMetierImpl implements IAdminCategoriesMetier {
	private IBoutiqueDAO dao;// on peut utiliser @autowire mais nécessite autre annotations
	
	
	

	

	public void setDao(IBoutiqueDAO dao) {
		this.dao = dao;
	}

	@Override
	public void supprimerProduit(Long idProd) {
		dao.supprimerProduit(idProd);
		
	}

	@Override
	public void modifierProduit(Produit p) {
		// TODO Auto-generated method stub
		dao.modifierProduit(p);
	}

	@Override
	public Long ajouterProduit(Produit p, Long idCat) {
		
		return dao.ajouterProduit(p, idCat);
	}

	@Override
	public List<Categorie> listCategories() {
		// TODO Auto-generated method stub
		return dao.listCategories();
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		// TODO Auto-generated method stub
		return dao.getCategorie(idCat);
	}

	@Override
	public List<Produit> listProduits() {
		// TODO Auto-generated method stub
		return dao.listProduits();
	}

	@Override
	public List<Produit> produitsParMotCle(String mc) {
		// TODO Auto-generated method stub
		return dao.produitsParMotCle(mc);
	}

	@Override
	public List<Produit> produitsParcategorie(Long idCat) {
		// TODO Auto-generated method stub
		return dao.produitsParcategorie(idCat);
	}

	@Override
	public Commande enregistrerCommande(Panier p, Client c) {
		// TODO Auto-generated method stub
		return dao.enregistrerCommande(p, c);
	}

	@Override
	public List<Produit> produitsSelectionnes() {
		// TODO Auto-generated method stub
		return dao.produitsSelectionnes();
	}

	@Override
	public Produit getProduit(Long idProd) {
		// TODO Auto-generated method stub
		return dao.getProduit(idProd);
	}

	@Override
	public Long ajouterCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return dao.ajouterCategorie(c);
	}

	@Override
	public void supprimerCategorie(Long idCat) {
		// TODO Auto-generated method stub
		dao.supprimerCategorie(idCat);
	}

	@Override
	public void modifierCategorie(Categorie c) {
		// TODO Auto-generated method stub
		dao.modifierCategorie(c);
	}

	@Override
	public void ajouterUser(User u) {
		// TODO Auto-generated method stub
		dao.ajouterUser(u);
	}

	@Override
	public void attribuerRole(Role role, Long userId) {
		// TODO Auto-generated method stub
		dao.attribuerRole(role, userId);
	}

}
