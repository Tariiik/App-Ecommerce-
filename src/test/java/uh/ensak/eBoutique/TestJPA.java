package uh.ensak.eBoutique;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.entities.Produit;
import uh.ensak.eBoutique.metier.IAdminCategoriesMetier;
import uh.ensak.eBoutique.metier.InternauteMetier;

public class TestJPA {
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context= new ClassPathXmlApplicationContext(new String[]{"ApplicationContext.xml"});
	}

	@Test
	public void test2(){
		
		Long idCat = new Long(5);
		try{
			InternauteMetier metier = (InternauteMetier)context.getBean("metier");
			List<Produit> ps =metier.produitsParcategorie(idCat);
			for(Produit p : ps){
				System.out.println(p.getDesignation());
			}
		}catch(Exception e)
		{
			assertTrue(e.getMessage(), false);
		}
	}
	
	/*@Test
	public void test1() {
		try
		{
			 
			IAdminCategoriesMetier metier = (IAdminCategoriesMetier)context.getBean("metier");
			List<Categorie> categories1 = metier.listCategories();
			metier.ajouterCategorie(new Categorie("Ordinateurs", "ordinateurs", null, "photo.jpg"));
			metier.ajouterCategorie(new Categorie("Imprimantes", "imprimantes", null, "photo.jpg"));
			List<Categorie> ctg2 = metier.listCategories();
			assertTrue(categories1.size()+2 == ctg2.size());
			//assertTrue(true);
			
		}catch(Exception e)
		{
			assertTrue(e.getMessage(), false);
		}
	}
	@Test
	public void test2() {
		try
		{
			
			IAdminCategoriesMetier metier = (IAdminCategoriesMetier)context.getBean("metier");
			List<Produit> produits = metier.listProduits();
			metier.ajouterProduit(new Produit("HP454AZER", "HPCOMpaq", 6000,true, null, 10), 1L);
			metier.ajouterProduit(new Produit("AZERTY", "AZERT290", 5000,false, null, 10), 1L);
			List<Produit> produits2 = metier.listProduits();
			assertTrue(produits.size()+2 == produits2.size());
		
		}catch(Exception e)
		{
			assertTrue(e.getMessage(), false);
		}
	}*/


}
