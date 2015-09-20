package uh.ensak.eBoutique.metier;


import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.entities.Role;
import uh.ensak.eBoutique.entities.User;

public interface IAdminCategoriesMetier extends IAdminProduitsMetier {
	public Long ajouterCategorie(Categorie c);
	
	public void supprimerCategorie(Long idCat);
	public void modifierCategorie(Categorie c);
	public void ajouterUser(User u);
	public void attribuerRole(Role role, Long userId);

}
