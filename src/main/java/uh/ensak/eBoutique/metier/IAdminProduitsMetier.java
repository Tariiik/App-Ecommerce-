package uh.ensak.eBoutique.metier;

import uh.ensak.eBoutique.entities.*;

public interface IAdminProduitsMetier extends InternauteMetier{
	public void supprimerProduit(Long idProd);
	public void modifierProduit(Produit p);
	public Long ajouterProduit(Produit p, Long idCat);

}
