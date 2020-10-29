package com.sid.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sid.dao.CompteRepository;
import com.sid.dao.OperationRepository;
import com.sid.entities.Compte;
import com.sid.entities.CompteCourant;
import com.sid.entities.Operation;
import com.sid.entities.Retrait;
import com.sid.entities.Versement;

//pour que spring puis intancier cette classe au demarage de 
@Service

@Transactional 
public class BanqueMetierImpl implements IBanqueMetier{
	// injection de dependance
	@Autowired
	private CompteRepository compteRepository; 
	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Optional<Compte> consulterCompte(String codeCpte) {
		Optional<Compte> cp = compteRepository.findById(codeCpte);
		if(cp == null ) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Optional<Compte> cp = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(),montant,cp.get());
		operationRepository.save(v);
		
		cp.get().setSolde(cp.get().getSolde()+montant);
		compteRepository.save(cp.get());
	}

	@Override
	public void retrait(String codeCpte, double montant) {
		Optional<Compte>  cp = consulterCompte(codeCpte);
		
		double agios = 0;
		if(cp.get() instanceof CompteCourant)
			agios = ((CompteCourant) cp.get()).getAgios();
		
		 if(cp.get().getSolde()+agios < montant)
			 throw new RuntimeException("solde insuffisant");
		 
		Retrait r = new Retrait(new Date(),montant,cp.get());
		operationRepository.save(r);

		cp.get().setSolde(cp.get().getSolde()-montant);
		compteRepository.save(cp.get());
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equalsIgnoreCase(codeCpte2))
			throw new RuntimeException("impossible : compte identique ");
			retrait(codeCpte1, montant);
			verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listeOperation(String coteCpte, int page, int size) {
		
	   	return operationRepository.listeOperaton(coteCpte, PageRequest.of(page, size));
	}

}
