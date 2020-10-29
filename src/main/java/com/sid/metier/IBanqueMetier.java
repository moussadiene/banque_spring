package com.sid.metier;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.sid.entities.Compte;
import com.sid.entities.Operation;

public interface IBanqueMetier {

	public Optional<Compte> consulterCompte(String codeCpte);
	public void verser(String codeCpte,double montant);
	public void retrait(String codeCpte,double montant);
	public void virement(String codeCpte1,String codeCpte2,double montant);
	public Page<Operation> listeOperation(String coteCpte,int page,int size);
}
