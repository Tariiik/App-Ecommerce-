package uh.ensak.eBoutique.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ValidationException;

import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.entities.Client;
import uh.ensak.eBoutique.entities.Commande;
import uh.ensak.eBoutique.entities.LigneCommande;
import uh.ensak.eBoutique.entities.Panier;
import uh.ensak.eBoutique.entities.Produit;
import uh.ensak.eBoutique.entities.Role;
import uh.ensak.eBoutique.entities.User;

public class BoutiqueDaoImpl implements IBoutiqueDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Long ajouterCategorie(Categorie c) {
		
		if(c == null) throw new ValidationException("catégorie vide");
		em.persist(c);
		return c.getIdCategorie();
	}

	@Override
	public List<Categorie> listCategories() {
		String requete = "select c from Categorie c";
		Query req = em.createQuery(requete);
		return req.getResultList();
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		
		if(idCat==null) throw new ValidationException("invalide idCat");
		return em.find(Categorie.class, idCat);
	}

	@Override
	public void supprimerCategorie(Long idCat) {
		Categorie c = em.find(Categorie.class, idCat);
		em.remove(c);// on suppose qu'elle existe

	}

	@Override
	public void modifierCategorie(Categorie c) {
		
		em.merge(c);

	}

	@Override
	public Long ajouterProduit(Produit p, Long idCat) {
		Categorie c = getCategorie(idCat);//ou c = em.find(idCat);
		p.setCategorie(c);
		em.persist(p);
		return p.getIdProduit();
	}

	@Override
	public List<Produit> listProduits() {
		Query req = em.createQuery("select p from Produit p");
		return req.getResultList();
	}

	@Override
	public List<Produit> produitsParMotCle(String mc) {
		Query req = em.createQuery("select p from Produit p where p.designation like :x or p.description like :x");
		req.setParameter("x", "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public List<Produit> produitsParcategorie(Long idCat) {
		Query req = em.createQuery("select p from Produit p where p.categorie.idCategorie ="+idCat);
		//em.setProperty("x", idCat);
		return req.getResultList();
	}

	@Override
	public List<Produit> produitsSelectionnes() {
		Query req = em.createQuery("select p from Produit p where p.selected=true");
		return req.getResultList();
	}

	@Override
	public Produit getProduit(Long idProd) {
		// TODO Auto-generated method stub
		return em.find(Produit.class, idProd);
	}

	@Override
	public void supprimerProduit(Long idProd) {
		Produit p =  getProduit(idProd);// em.find(Produit.class, idProd);
		em.remove(p);
	}

	@Override
	public void modifierProduit(Produit p) {
		
		em.merge(p);

	}

	@Override
	public void ajouterUser(User u) {
		em.persist(u);

	}

	@Override
	public void attribuerRole(Role role, Long userId) {
		User u = em.find(User.class, userId);
		u.getRoles().add(role);
		em.persist(u);

	}

	@Override
	public Commande enregistrerCommande(Panier p, Client c) {
		//a traiter si le client existe deja il faut charger
		em.persist(c);
		Commande cmd = new Commande();
		cmd.setDateCommande(new Date());
		cmd.setItems(p.getItems());
		for(LigneCommande lc : p.getItems())
		{
			em.persist(lc);
		}
		return cmd;
	}

}
