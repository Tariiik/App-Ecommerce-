package uh.ensak.eBoutique.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.handler.HandlerResolver;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import uh.ensak.eBoutique.entities.Categorie;
import uh.ensak.eBoutique.metier.IAdminCategoriesMetier;

@Controller
@RequestMapping(value = "/adminCat")
@SessionAttributes("editedCat")
public class AdminCategoriesController implements HandlerExceptionResolver {
	@Autowired
	private IAdminCategoriesMetier metier;

	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("categories", metier.listCategories());
		return "categories";
	}

	@RequestMapping(value = "/saveCat")
	public String saveCat(@Valid Categorie categorie, BindingResult bindingResult,
			Model model, MultipartFile file) throws IOException {

		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", metier.listCategories());
			return "categories";
		}

		if (!file.isEmpty()) {
			// BufferedImage bi = ImageIO.read(file.getInputStream());
			categorie.setPhoto(file.getBytes());
			categorie.setNomPhoto(file.getOriginalFilename());
		}

		if (categorie.getIdCategorie() != null) {
			if (model.asMap().get("editedCat") != null) {
				if (file.isEmpty()) {
					// Categorie cat = (Categorie)
					// model.asMap().get("editedCat");
					Categorie cat = metier.getCategorie(categorie.getIdCategorie());
					categorie.setPhoto(cat.getPhoto());
				}
			}
			metier.modifierCategorie(categorie);
		} else{

			  metier.ajouterCategorie(categorie);
			  model.addAttribute("message", "la catégorie "+categorie.getNomCategorie()+" a été sauvegardée");
			  }

		model.addAttribute("categorie", new Categorie());
		model.addAttribute("categories", metier.listCategories());
		return "categories";
	}

	@RequestMapping(value = "photoCat", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoCat(Long idCat) throws IOException {
		Categorie c = metier.getCategorie(idCat);
		return IOUtils.toByteArray(new ByteArrayInputStream(c.getPhoto()));
	}

	@RequestMapping(value = "/suppCat")
	public String supp(Long idCat, Model model) {
		
		Categorie c = metier.getCategorie(idCat);
		model.addAttribute("message", "la catégorie "+c.getNomCategorie()+" a été supprimée");
		metier.supprimerCategorie(idCat);
		
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("categories", metier.listCategories());
		
		return "categories";
	}

	@RequestMapping(value = "/editCat")
	public String edit(Long idCat, Model model) {

		Categorie c = metier.getCategorie(idCat);
		model.addAttribute("editedCat", c);
		model.addAttribute("categorie", c);
		model.addAttribute("categories", metier.listCategories());
		return "categories";
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse response, Object arg2, Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categorie", new Categorie());
		mv.addObject("categories", metier.listCategories());
		mv.addObject("exception", ex.getMessage());
		mv.setViewName("categories");
		return mv;
	}

}
