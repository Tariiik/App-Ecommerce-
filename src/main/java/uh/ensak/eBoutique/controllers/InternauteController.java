package uh.ensak.eBoutique.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.entities.Panier;
import uh.ensak.eBoutique.entities.Produit;
import uh.ensak.eBoutique.metier.InternauteMetier;

@Controller
@RequestMapping(value="/")
public class InternauteController {
	
	@Autowired
	private InternauteMetier metier;
	
	@RequestMapping(value="/home")
	public String home(Model model)
	{
		model.addAttribute("categories", metier.listCategories());
		return "home";
	}
	
	@RequestMapping(value="/categorie")
	public String categorie(Long idCat, Model model){
		
		Categorie c = metier.getCategorie(idCat);
		
		model.addAttribute("categorie", c);
		
		model.addAttribute("produits",metier.produitsParcategorie(idCat));
		return "produitsParCat";
	}
	
	@RequestMapping(value="photoCat",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoCat(Long idCat) throws IOException
	{
		Categorie c = metier.getCategorie(idCat);
		return IOUtils.toByteArray(new ByteArrayInputStream(c.getPhoto()));
	}
	
	@RequestMapping(value="photoProd",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProd(Long idProd) throws IOException
	{
		Produit p = metier.getProduit(idProd);
		File f = new File( System.getProperty("java.io.tmpdir")+"/PROD_"+idProd+"_"+p.getPhoto());
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/ajouter")
	public String ajouterItem(Produit prod,Model model){
		
		Panier p = new Panier();
		p.addArticle(prod, 1);
		model.addAttribute("currentPanier", p);
		model.addAttribute("panier", p.getSize());
		return("produitsParCat"+metier.produitsParcategorie(prod.getCategorie().getIdCategorie()));
	}
	
}
