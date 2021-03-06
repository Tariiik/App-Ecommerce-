package uh.ensak.eBoutique.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.entities.Produit;
import uh.ensak.eBoutique.metier.IAdminProduitsMetier;


@Controller
@RequestMapping(value="/adminProd")
@SessionAttributes("editedProd")
public class AdminProduitsController {

	@Autowired
	private IAdminProduitsMetier metier;
	
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("produit", new Produit());
		model.addAttribute("categories", metier.listCategories());
		model.addAttribute("produits",metier.listProduits());
		return "produits";
	}
	
	@RequestMapping(value="/saveProd")
	public String saveProd(@Valid Produit p,BindingResult bindingResult,Model model, MultipartFile file)
	throws IOException
	{
		
		if(bindingResult.hasErrors())
		{
			model.addAttribute("produits",metier.listProduits());
			model.addAttribute("categories",metier.listCategories());
			return "produits";
		}
		
		Long idP = null;
		String path = System.getProperty("java.io.tmpdir");
		
		if(!file.isEmpty())
		{
			p.setPhoto(file.getOriginalFilename());
			
			if(p.getIdProduit() == null){
				idP = metier.ajouterProduit(p, p.getCategorie().getIdCategorie());
			}else{
				
				metier.modifierProduit(p);
				idP = p.getIdProduit();
			}
			file.transferTo(new File(path+"/PROD_"+idP+"_"+file.getOriginalFilename()));
		}
		/*else 
		{
			  
			if(p.getIdProduit() == null){
			metier.ajouterProduit(p, p.getCategorie().getIdCategorie());
			}else{
				
				metier.modifierProduit(p);
			}
			
		}*/
		if(p.getIdProduit()!=null)
		{
			Produit prod = metier.getProduit(p.getIdProduit());
			p.setPhoto(prod.getPhoto());
			metier.modifierProduit(p);
		}
		else 
				metier.ajouterProduit(p,p.getCategorie().getIdCategorie());
		
		model.addAttribute("produit", new Produit());
		model.addAttribute("produits",metier.listProduits());
		model.addAttribute("categories",metier.listCategories());
		return "produits";
	}
	
	
	@RequestMapping(value="photoProd",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProd(Long idProd) throws IOException
	{
		Produit p = metier.getProduit(idProd);
		File f = new File( System.getProperty("java.io.tmpdir")+"/PROD_"+idProd+"_"+p.getPhoto());
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/suppProd")
	public String supp(Long idProd, Model model){
		
		metier.supprimerProduit(idProd);
		model.addAttribute("produit", new Produit());
		model.addAttribute("categories",metier.listCategories());
		model.addAttribute("produits",metier.listProduits());
		return "produits";
	}
	@RequestMapping(value="/editProd")
	public String edit(Long idProd, Model model){
		
		Produit p = metier.getProduit(idProd);
		
		model.addAttribute("produit", p);
		model.addAttribute("produits",metier.listProduits());
		model.addAttribute("categories",metier.listCategories());
		return "produits";
	}

}
