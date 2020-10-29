package com.sid.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sid.entities.Compte;
import com.sid.entities.Operation;
import com.sid.metier.IBanqueMetier;

@Controller
public class BanqueController {
	
	  @Autowired 
	  private IBanqueMetier banqueMetier;
	  
	 
	@RequestMapping("/comptes")
	public String index() {
		return "comptes";
	}
	@RequestMapping(value = "/consulterCompte", method=RequestMethod.GET)
	public String consulterCompte(Model model,String codeCompte,
			@RequestParam(name = "page" ,defaultValue = "0")int page,
			@RequestParam(name = "size" ,defaultValue = "4")int size) {
		
		model.addAttribute("codeCompte",codeCompte);
		 try {
			 Optional<Compte>  compte = banqueMetier.consulterCompte(codeCompte) ;
			 
			 // pour avoir la liste des operations
			 
			 Page<Operation> pageOperation = banqueMetier.listeOperation(codeCompte, page, size);
			 model.addAttribute("listeOperation",pageOperation.getContent());
			 int [] pages = new int[pageOperation.getTotalPages()];
			 model.addAttribute("pages",pages);
			 //stock les information du compte qui sera passe dans la vue
			 model.addAttribute("compte",compte.get());
		} catch (Exception e) {
			model.addAttribute("Erreur",e);
		}
		return "comptes";
	}
	
	@RequestMapping(value = "/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte,double montant ,String codeCompte2){
		
		try {
			if(typeOperation == "versement") {
				banqueMetier.verser(codeCompte, montant);
			}else if(typeOperation == "retrait") {
				banqueMetier.retrait(codeCompte, montant);
			}else {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("ErreurSave",e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte
					+"&ErreurSave"+e.getMessage();
		}
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
}
